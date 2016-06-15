package com.qcloud.component.my.web.handler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QMerchandiseItem;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise.MerchandiseType;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.marketing.MarketingClient;
import com.qcloud.component.marketing.QCoupon;
import com.qcloud.component.my.model.MyCollection;
import com.qcloud.component.my.model.MyShoppingCart;
import com.qcloud.component.my.model.key.TypeEnum.CollectionType;
import com.qcloud.component.my.service.MyCollectionService;
import com.qcloud.component.my.web.handler.MyShoppingCartHandler;
import com.qcloud.component.my.web.vo.CombinationListVO;
import com.qcloud.component.my.web.vo.MyShoppingCartClassifyVO;
import com.qcloud.component.my.web.vo.MyShoppingCartCombinationVO;
import com.qcloud.component.my.web.vo.MyShoppingCartMerchantVO;
import com.qcloud.component.my.web.vo.MyShoppingCartVO;
import com.qcloud.component.my.web.vo.admin.AdminMyShoppingCartVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.StringUtil;

@Component
public class MyShoppingCartHandlerImpl implements MyShoppingCartHandler {

    @Autowired
    private PublicdataClient      publicdataClient;

    @Autowired
    private FileSDKClient         fileSDKClient;

    @Autowired
    private SellercenterClient    sellercenterClient;

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Autowired
    private MyCollectionService   myCollectionService;

    @Autowired
    private MarketingClient       marketingClient;

    @Value("${pirates.mall.topClassify}")
    private String                topClassify;

    @Override
    public List<AdminMyShoppingCartVO> toVOList4Admin(List<MyShoppingCart> list) {

        List<AdminMyShoppingCartVO> voList = new ArrayList<AdminMyShoppingCartVO>();
        for (MyShoppingCart adminMyShoppingCart : list) {
            voList.add(toVO4Admin(adminMyShoppingCart));
        }
        return voList;
    }

    @Override
    public AdminMyShoppingCartVO toVO4Admin(MyShoppingCart myShoppingCart) {

        String json = Json.toJson(myShoppingCart);
        return Json.toObject(json, AdminMyShoppingCartVO.class, true);
    }

    @Override
    public List<MyShoppingCartVO> toVOList(List<MyShoppingCart> list) {

        List<MyShoppingCartVO> voList = new ArrayList<MyShoppingCartVO>();
        for (MyShoppingCart myShoppingCart : list) {
            voList.add(toVO(myShoppingCart));
        }
        return voList;
    }

    @Override
    public MyShoppingCartVO toVO(MyShoppingCart myShoppingCart) {

        String json = Json.toJson(myShoppingCart);
        MyShoppingCartVO vo = Json.toObject(json, MyShoppingCartVO.class, true);
        vo.setTimeStr(DateUtil.date2String(myShoppingCart.getTime()));
        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(myShoppingCart.getUnifiedMerchandiseId());
        // Double vipDiscount = commoditycenterClient.getVipDiscount(myShoppingCart.getUserId(), myShoppingCart.getUnifiedMerchandiseId());
        // //
        // if (vipDiscount >= unifiedMerchandise.getDiscount()) {
        // vo.setDiscount(NumberUtil.scale(unifiedMerchandise.getDiscount(), 2));
        // } else {
        // vo.setDiscount(NumberUtil.scale(vipDiscount, 2));
        // }
        //
        if (StringUtils.isNotEmpty(unifiedMerchandise.getImage())) {
            vo.setImage(fileSDKClient.getFileServerUrl() + StringUtil.nullToEmpty(unifiedMerchandise.getImage()));
        } else {
            vo.setImage(StringUtil.nullToEmpty(unifiedMerchandise.getImage()));
        }
        vo.setName(unifiedMerchandise.getName());
        vo.setPrice(NumberUtil.scale(unifiedMerchandise.getPrice(), 2));
        vo.setSpecifications(unifiedMerchandise.getSpecifications());
        vo.setStock(unifiedMerchandise.getStock());
        MerchandiseType merchandiseType = unifiedMerchandise.getType();
        if (MerchandiseType.SINGLE.equals(merchandiseType)) {
            vo.setMerchandiseType(1);
        } else if (MerchandiseType.COMBINATION.equals(merchandiseType)) {
            vo.setMerchandiseType(2);
        } else if (MerchandiseType.MARKETING.equals(merchandiseType)) {
            vo.setMerchandiseType(2);
        }
        MyCollection myCollection = myCollectionService.getByObject(myShoppingCart.getUnifiedMerchandiseId(), myShoppingCart.getUserId(), CollectionType.MERCHANDISE);
        vo.setCollect(myCollection != null);
        vo.setUnit(unifiedMerchandise.getUnit());
        vo.setSum(NumberUtil.scale(vo.getDiscount() * vo.getNumber(), 2));
        return vo;
    }

    @Override
    public List<MyShoppingCartClassifyVO> toVOList4Classify(List<MyShoppingCart> list) {

        Map<Long, MyShoppingCartClassifyVO> map = new HashMap<Long, MyShoppingCartClassifyVO>();
        List<MyShoppingCartClassifyVO> result = new ArrayList<MyShoppingCartClassifyVO>();
        for (MyShoppingCart myShoppingCart : list) {
            MyShoppingCartVO myShoppingCartVO = toVO(myShoppingCart);
            Classify classify = null;
            if (Boolean.valueOf(topClassify)) {
                classify = publicdataClient.getTopClassify(myShoppingCartVO.getMerchantClassifyId());
                AssertUtil.assertNotNull(classify, "一级分类不存在." + myShoppingCartVO.getMerchantClassifyId());
            } else {
                classify = publicdataClient.getClassify(myShoppingCartVO.getMerchantClassifyId());
                AssertUtil.assertNotNull(classify, "分类不存在." + myShoppingCartVO.getMerchantClassifyId());
            }
            MyShoppingCartClassifyVO myShoppingCartClassifyVO = map.get(classify.getId());
            if (myShoppingCartClassifyVO == null) {
                myShoppingCartClassifyVO = new MyShoppingCartClassifyVO();
                myShoppingCartClassifyVO.setClassifyId(classify.getId());
                if (StringUtils.isNotEmpty(classify.getImage())) {
                    myShoppingCartClassifyVO.setImage(fileSDKClient.getFileServerUrl() + StringUtil.nullToEmpty(classify.getImage()));
                }
                myShoppingCartClassifyVO.setName(classify.getName());
                result.add(myShoppingCartClassifyVO);
                map.put(classify.getId(), myShoppingCartClassifyVO);
            }
            myShoppingCartClassifyVO.getMerchandiseList().add(myShoppingCartVO);
        }
        return result;
    }

    @Override
    public List<MyShoppingCartMerchantVO> toVOList4Merchant(List<MyShoppingCart> list) {

        Map<Long, MyShoppingCartMerchantVO> map = new HashMap<Long, MyShoppingCartMerchantVO>();
        List<MyShoppingCartMerchantVO> result = new ArrayList<MyShoppingCartMerchantVO>();
        for (MyShoppingCart myShoppingCart : list) {
            MyShoppingCartVO myShoppingCartVO = toVO(myShoppingCart);
            MyShoppingCartMerchantVO myShoppingCartMerchantVO = map.get(myShoppingCartVO.getMerchantId());
            if (myShoppingCartMerchantVO == null) {
                myShoppingCartMerchantVO = new MyShoppingCartMerchantVO();
                myShoppingCartMerchantVO.setMerchantId(myShoppingCartVO.getMerchantId());
                QMerchant merchant = sellercenterClient.getMerchant(myShoppingCartVO.getMerchantId());
                AssertUtil.assertNotNull(merchant, "商家不存在." + myShoppingCartVO.getMerchantId());
                myShoppingCartMerchantVO.setName(merchant.getName());
                if (StringUtils.isNotEmpty(merchant.getImage())) {
                    myShoppingCartMerchantVO.setImage(fileSDKClient.getFileServerUrl() + StringUtil.nullToEmpty(merchant.getImage()));
                } else {
                    myShoppingCartMerchantVO.setImage(StringUtil.nullToEmpty(merchant.getImage()));
                }
                result.add(myShoppingCartMerchantVO);
                map.put(myShoppingCartVO.getMerchantId(), myShoppingCartMerchantVO);
            }
            myShoppingCartMerchantVO.getMerchandiseList().add(myShoppingCartVO);
            // 购物车优惠劵
            myShoppingCartMerchantVO.setCoupon(false);
            QCoupon qCoupon = marketingClient.getActivityCoupon(myShoppingCartVO.getMerchantId());
            if (qCoupon != null) {
                boolean coupon = marketingClient.canExtract(myShoppingCart.getUserId(), qCoupon.getId());
                myShoppingCartMerchantVO.setCoupon(coupon);
            }
        }
        return result;
    }

    @Override
    public List<MyShoppingCartCombinationVO> toVOList4Combination(List<MyShoppingCart> list) {

        Map<Long, MyShoppingCartCombinationVO> map = new HashMap<Long, MyShoppingCartCombinationVO>();
        List<MyShoppingCartCombinationVO> result = new ArrayList<MyShoppingCartCombinationVO>();
        for (MyShoppingCart myShoppingCart : list) {
            MyShoppingCartVO myShoppingCartVO = toVO(myShoppingCart);
            MyShoppingCartCombinationVO myShoppingCartMerchantVO = map.get(myShoppingCartVO.getMerchantId());
            if (myShoppingCartMerchantVO == null) {
                myShoppingCartMerchantVO = new MyShoppingCartCombinationVO();
                myShoppingCartMerchantVO.setMerchantId(myShoppingCartVO.getMerchantId());
                QMerchant merchant = sellercenterClient.getMerchant(myShoppingCartVO.getMerchantId());
                AssertUtil.assertNotNull(merchant, "商家不存在." + myShoppingCartVO.getMerchantId());
                myShoppingCartMerchantVO.setName(merchant.getName());
                if (StringUtils.isNotEmpty(merchant.getImage())) {
                    myShoppingCartMerchantVO.setImage(fileSDKClient.getFileServerUrl() + StringUtil.nullToEmpty(merchant.getImage()));
                } else {
                    myShoppingCartMerchantVO.setImage(StringUtil.nullToEmpty(merchant.getImage()));
                }
                result.add(myShoppingCartMerchantVO);
                map.put(myShoppingCartVO.getMerchantId(), myShoppingCartMerchantVO);
            }
            QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(myShoppingCartVO.getUnifiedMerchandiseId());
            // 单品
            if (unifiedMerchandise.getType().equals(MerchandiseType.SINGLE)) {
                myShoppingCartMerchantVO.getMerchandiseList().add(myShoppingCartVO);
            } else if (unifiedMerchandise.getType().equals(MerchandiseType.COMBINATION)) {// 组合商品
                CombinationListVO combination = new CombinationListVO();
                combination.setName(unifiedMerchandise.getName());
                combination.setSum(unifiedMerchandise.getDiscount());
                combination.setUnifiedMerchandiseId(unifiedMerchandise.getId());
                String desc = unifiedMerchandise.getName();
                //
                List<MyShoppingCartVO> comMerchandiseList = new ArrayList<MyShoppingCartVO>();
                for (QMerchandiseItem item : unifiedMerchandise.getList()) {
                    QUnifiedMerchandise merchandise = commoditycenterClient.getUnifiedMerchandise(item.getUnifiedMerchandiseId());
                    MyShoppingCartVO vo = new MyShoppingCartVO();
                    vo.setSpecifications(item.getSpecifications());
                    vo.setDiscount(merchandise.getDiscount());
                    vo.setPrice(merchandise.getPrice());
                    vo.setUnifiedMerchandiseId(merchandise.getId());
                    vo.setImage(merchandise.getImage());
                    vo.setName(merchandise.getName());
                    vo.setUnit(merchandise.getUnit());
                    vo.setNumber(item.getNumber());
                    vo.setStock(merchandise.getStock());
                    vo.setTimeStr(myShoppingCartVO.getTimeStr());
                    vo.setMerchantId(merchandise.getMerchantId());
                    vo.setMerchantClassifyId(item.getMerchantClassifyId());
                    vo.setMerchandiseType(2);
                    desc += merchandise.getName();
                    comMerchandiseList.add(vo);
                }
                combination.setDesc(desc);
                combination.setMerchandiseList(comMerchandiseList);
                myShoppingCartMerchantVO.getCombinationList().add(combination);
            }
            // 购物车优惠劵
            myShoppingCartMerchantVO.setCoupon(false);
            QCoupon qCoupon = marketingClient.getActivityCoupon(myShoppingCartVO.getMerchantId());
            if (qCoupon != null) {
                boolean coupon = marketingClient.canExtract(myShoppingCart.getUserId(), qCoupon.getId());
                myShoppingCartMerchantVO.setCoupon(coupon);
            }
        }
        return result;
    }
}
