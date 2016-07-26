package com.qcloud.component.my.web.handler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.UnifiedMerchandiseType;
import com.qcloud.component.marketing.MarketingClient;
import com.qcloud.component.marketing.QCoupon;
import com.qcloud.component.my.exception.MyException;
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
        vo.setDiscount(NumberUtil.scale(unifiedMerchandise.getDiscount(), 2));
        vo.setPrice(NumberUtil.scale(unifiedMerchandise.getPrice(), 2));
        vo.setSpecifications(unifiedMerchandise.getSpecifications());
        vo.setStock(unifiedMerchandise.getStock());
        vo.setMerchandiseType(unifiedMerchandise.getType().getKey());
        MyCollection myCollection = myCollectionService.getByObject(myShoppingCart.getUnifiedMerchandiseId(), myShoppingCart.getUserId(), CollectionType.MERCHANDISE);
        vo.setCollect(myCollection != null);
        vo.setUnit(unifiedMerchandise.getUnit());
        vo.setSum(NumberUtil.scale(vo.getDiscount() * vo.getNumber(), 2));
        vo.setGroup(myShoppingCart.getGroup());
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
            if (unifiedMerchandise.getType().equals(UnifiedMerchandiseType.SINGLE)) {
                myShoppingCartMerchantVO.getMerchandiseList().add(myShoppingCartVO);
            } else if (unifiedMerchandise.getType().equals(UnifiedMerchandiseType.COMBINATION)) {// 组合商品
                CombinationListVO combination = new CombinationListVO();
                combination.setName(unifiedMerchandise.getName());
                combination.setSum(NumberUtil.scale(unifiedMerchandise.getDiscount(), 2));
                combination.setNumber(myShoppingCart.getNumber());
                combination.setStock(unifiedMerchandise.getStock());
                combination.setUnifiedMerchandiseId(unifiedMerchandise.getId());
                String desc = unifiedMerchandise.getName();
                //
                List<MyShoppingCartVO> comMerchandiseList = new ArrayList<MyShoppingCartVO>();
                for (QUnifiedMerchandise merchandise : unifiedMerchandise.getList()) {
                    MyShoppingCartVO vo = new MyShoppingCartVO();
                    vo.setSpecifications(merchandise.getSpecifications());
                    vo.setDiscount(NumberUtil.scale(merchandise.getDiscount(), 2));
                    vo.setPrice(NumberUtil.scale(merchandise.getPrice(), 2));
                    vo.setUnifiedMerchandiseId(merchandise.getId());
                    if (StringUtils.isNotEmpty(merchandise.getImage())) {
                        vo.setImage(fileSDKClient.getFileServerUrl() + StringUtil.nullToEmpty(merchandise.getImage()));
                    } else {
                        vo.setImage(StringUtil.nullToEmpty(merchandise.getImage()));
                    }
                    vo.setDiscount(merchandise.getDiscount());
                    vo.setName(merchandise.getName());
                    vo.setUnit(merchandise.getUnit());
                    vo.setNumber(merchandise.getNumber());
                    vo.setStock(merchandise.getStock());
                    vo.setTimeStr(myShoppingCartVO.getTimeStr());
                    vo.setMerchantId(merchandise.getMerchantId());
                    vo.setMerchantClassifyId(merchandise.getMerchantClassifyId());
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

    @Override
    public List<MyShoppingCartCombinationVO> toVOList4Group(List<MyShoppingCart> list) {

        Map<String, List<MyShoppingCart>> groupShoppingCartList = new HashMap<String, List<MyShoppingCart>>();
        for (MyShoppingCart myShoppingCart : list) {
            List<MyShoppingCart> cartList = groupShoppingCartList.get(myShoppingCart.getGroup());
            if (cartList == null) {
                cartList = new ArrayList<MyShoppingCart>();
                cartList.add(myShoppingCart);
            } else {
                cartList.add(myShoppingCart);
            }
            groupShoppingCartList.put(myShoppingCart.getGroup(), cartList);
        }
        Map<Long, MyShoppingCartCombinationVO> map = new HashMap<Long, MyShoppingCartCombinationVO>();
        Map<String, List<CombinationListVO>> groupCombinationList = new HashMap<String, List<CombinationListVO>>();
        List<MyShoppingCartCombinationVO> result = new ArrayList<MyShoppingCartCombinationVO>();
        //
        for (String group : groupShoppingCartList.keySet()) {
            List<MyShoppingCart> cartList = groupShoppingCartList.get(group);
            for (MyShoppingCart myShoppingCart : cartList) {
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
                // 判断
                if (myShoppingCartVO.getCombinationMerchandiseId() == -1) {// 单品或者固定搭配
                    QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(myShoppingCartVO.getUnifiedMerchandiseId());
                    if (unifiedMerchandise.getType().equals(UnifiedMerchandiseType.SINGLE)) {// 单品
                        myShoppingCartMerchantVO.getMerchandiseList().add(myShoppingCartVO);
                    } else if (unifiedMerchandise.getType().equals(UnifiedMerchandiseType.COMBINATION)) {// 组合商品
                        CombinationListVO combination = new CombinationListVO();
                        combination.setSum(NumberUtil.scale(unifiedMerchandise.getDiscount(), 2));
                        combination.setName(unifiedMerchandise.getName());
                        combination.setNumber(myShoppingCart.getNumber());
                        combination.setStock(unifiedMerchandise.getStock());
                        combination.setUnifiedMerchandiseId(unifiedMerchandise.getId());
                        combination.setGroup(group);
                        combination.setType(1);
                        String desc = unifiedMerchandise.getName();
                        //
                        List<MyShoppingCartVO> comMerchandiseList = new ArrayList<MyShoppingCartVO>();
                        for (QUnifiedMerchandise merchandise : unifiedMerchandise.getList()) {
                            MyShoppingCartVO vo = new MyShoppingCartVO();
                            vo.setSpecifications(merchandise.getSpecifications());
                            vo.setDiscount(NumberUtil.scale(merchandise.getDiscount(), 2));
                            vo.setPrice(NumberUtil.scale(merchandise.getPrice(), 2));
                            vo.setUnifiedMerchandiseId(merchandise.getId());
                            if (StringUtils.isNotEmpty(merchandise.getImage())) {
                                vo.setImage(fileSDKClient.getFileServerUrl() + StringUtil.nullToEmpty(merchandise.getImage()));
                            } else {
                                vo.setImage(StringUtil.nullToEmpty(merchandise.getImage()));
                            }
                            vo.setName(merchandise.getName());
                            vo.setUnit(merchandise.getUnit());
                            vo.setNumber(merchandise.getNumber());
                            vo.setStock(merchandise.getStock());
                            vo.setTimeStr(myShoppingCartVO.getTimeStr());
                            vo.setMerchantId(merchandise.getMerchantId());
                            vo.setMerchantClassifyId(merchandise.getMerchantClassifyId());
                            vo.setMerchandiseType(2);
                            vo.setGroup(group);
                            desc += merchandise.getName();
                            comMerchandiseList.add(vo);
                        }
                        combination.setDesc(desc);
                        combination.setMerchandiseList(comMerchandiseList);
                        myShoppingCartMerchantVO.getCombinationList().add(combination);
                    }
                } else {// 自由搭配 // TODO
                    QUnifiedMerchandise combinationMerchandise = commoditycenterClient.getUnifiedMerchandise(myShoppingCartVO.getCombinationMerchandiseId());
                    if (!groupCombinationList.containsKey(group)) {
                        double sum = 0.0;
                        CombinationListVO combination = new CombinationListVO();
                        combination.setName(combinationMerchandise.getName());
                        combination.setStock(combinationMerchandise.getStock());
                        combination.setUnifiedMerchandiseId(myShoppingCartVO.getCombinationMerchandiseId());
                        combination.setGroup(group);
                        combination.setType(2);
                        String desc = "自由搭配：";
                        int combinationNumber = 1;
                        //
                        List<MyShoppingCartVO> comMerchandiseList = new ArrayList<MyShoppingCartVO>();
                        for (MyShoppingCart combinationCart : cartList) {
                            QUnifiedMerchandise merchandise = getFreeMerchandise(myShoppingCartVO.getCombinationMerchandiseId(), combinationCart.getUnifiedMerchandiseId());
                            MyShoppingCartVO vo = new MyShoppingCartVO();
                            vo.setSpecifications(merchandise.getSpecifications());
                            vo.setDiscount(NumberUtil.scale(merchandise.getDiscount(), 2));
                            vo.setPrice(NumberUtil.scale(merchandise.getPrice(), 2));
                            vo.setUnifiedMerchandiseId(merchandise.getId());
                            if (StringUtils.isNotEmpty(merchandise.getImage())) {
                                vo.setImage(fileSDKClient.getFileServerUrl() + StringUtil.nullToEmpty(merchandise.getImage()));
                            } else {
                                vo.setImage(StringUtil.nullToEmpty(merchandise.getImage()));
                            }
                            vo.setName(merchandise.getName());
                            vo.setUnit(merchandise.getUnit());
                            vo.setNumber(merchandise.getNumber());
                            combinationNumber = combinationCart.getNumber() / merchandise.getNumber();
                            vo.setStock(merchandise.getStock());
                            vo.setTimeStr(myShoppingCartVO.getTimeStr());
                            vo.setMerchantId(merchandise.getMerchantId());
                            vo.setMerchantClassifyId(merchandise.getMerchantClassifyId());
                            vo.setMerchandiseType(2);
                            desc += merchandise.getName() + "  ";
                            sum += merchandise.getDiscount() * merchandise.getNumber();
                            vo.setGroup(group);
                            comMerchandiseList.add(vo);
                        }
                        combination.setNumber(combinationNumber);
                        if (comMerchandiseList.size() == combinationMerchandise.getList().size()) {// 自由搭配全买了,取自由搭配的价格
                            combination.setSum(NumberUtil.scale(combinationMerchandise.getDiscount(), 2));
                        } else {
                            combination.setSum(NumberUtil.scale(sum, 2));// 没有全部买则取单品的总价
                        }
                        combination.setDesc(desc);
                        combination.setMerchandiseList(comMerchandiseList);
                        myShoppingCartMerchantVO.getCombinationList().add(combination);
                        groupCombinationList.put(group, myShoppingCartMerchantVO.getCombinationList());
                    }
                }
                // 购物车优惠劵
                myShoppingCartMerchantVO.setCoupon(false);
                QCoupon qCoupon = marketingClient.getActivityCoupon(myShoppingCartVO.getMerchantId());
                if (qCoupon != null) {
                    boolean coupon = marketingClient.canExtract(myShoppingCart.getUserId(), qCoupon.getId());
                    myShoppingCartMerchantVO.setCoupon(coupon);
                }
            }
        }
        return result;
    }

    public QUnifiedMerchandise getFreeMerchandise(Long combinationMerchandiseId, Long freeMerchandiseId) {

        QUnifiedMerchandise combinationMerchandise = commoditycenterClient.getUnifiedMerchandise(combinationMerchandiseId);
        for (QUnifiedMerchandise merchandise : combinationMerchandise.getList()) {
            if (freeMerchandiseId.longValue() == merchandise.getId().longValue()) {
                return merchandise;
            }
        }
        throw new MyException("商品不存在.");
    }
}
