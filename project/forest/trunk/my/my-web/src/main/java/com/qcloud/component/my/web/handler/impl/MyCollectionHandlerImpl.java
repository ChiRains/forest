package com.qcloud.component.my.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.my.model.MyCollection;
import com.qcloud.component.my.web.handler.ActivityForMy;
import com.qcloud.component.my.web.handler.MyCollectionHandler;
import com.qcloud.component.my.web.vo.CollectionMerchant;
import com.qcloud.component.my.web.vo.MerchantHotMerchandise;
import com.qcloud.component.my.web.vo.MyMerchandiseCollectionVO;
import com.qcloud.component.my.web.vo.MyMerchantCollectionVO;
import com.qcloud.component.my.web.vo.MyStoreCollectionVO;
import com.qcloud.component.my.web.vo.admin.AdminMyCollectionVO;
import com.qcloud.component.organization.OrganizationClient;
import com.qcloud.component.organization.QDepartment;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.StringUtil;

@Component
public class MyCollectionHandlerImpl implements MyCollectionHandler {

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Autowired
    private SellercenterClient    sellercenterClient;

    @Autowired
    private OrganizationClient    organizationClient;

    @Autowired
    private FileSDKClient         fileSDKClient;

    @Autowired
    private ActivityForMy         activityForMy;

    @Override
    public List<AdminMyCollectionVO> toVOList4Admin(List<MyCollection> list) {

        List<AdminMyCollectionVO> voList = new ArrayList<AdminMyCollectionVO>();
        for (MyCollection adminMyCollection : list) {
            voList.add(toVO4Admin(adminMyCollection));
        }
        return voList;
    }

    @Override
    public AdminMyCollectionVO toVO4Admin(MyCollection myCollection) {

        String json = Json.toJson(myCollection);
        return Json.toObject(json, AdminMyCollectionVO.class, true);
    }

    @Override
    public List<MyMerchandiseCollectionVO> toMerchandiseMyCollectionVOList(List<MyCollection> list) {

        List<MyMerchandiseCollectionVO> voList = new ArrayList<MyMerchandiseCollectionVO>();
        for (MyCollection myCollection : list) {
            voList.add(toMerchandiseMyCollectionVO(myCollection));
        }
        return voList;
    }

    @Override
    public MyMerchandiseCollectionVO toMerchandiseMyCollectionVO(MyCollection myCollection) {

        String json = Json.toJson(myCollection);
        MyMerchandiseCollectionVO vo = Json.toObject(json, MyMerchandiseCollectionVO.class, true);
        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(myCollection.getObjId());
        vo.setTimeStr(DateUtil.date2String(myCollection.getTime()));
        vo.setUnifiedMerchandiseId(myCollection.getObjId());
        vo.setDiscount(unifiedMerchandise.getDiscount());
        vo.setImage(fileSDKClient.getFileServerUrl() + unifiedMerchandise.getImage());
        vo.setMallClassifyId(unifiedMerchandise.getMallClassifyId());
        vo.setName(unifiedMerchandise.getName());
        vo.setPrice(unifiedMerchandise.getPrice());
        vo.setSalesVolume(unifiedMerchandise.getSalesVolume());
        vo.setSpecifications(unifiedMerchandise.getSpecifications());
        vo.setStock(unifiedMerchandise.getStock());
        // 好评率
        long sum = unifiedMerchandise.getGoodEvaluation() + unifiedMerchandise.getMiddleEvaluation() + unifiedMerchandise.getLowEvaluation();
        int goodEvaluationRate = 0;
        if (sum != 0) {
            goodEvaluationRate = new Double(unifiedMerchandise.getGoodEvaluation() * 100 / sum).intValue();
        }
        vo.setGoodEvaluationRate(goodEvaluationRate);
        vo.setLabel(unifiedMerchandise.getLabel());
        QMerchant merchant = sellercenterClient.getMerchant(unifiedMerchandise.getMerchantId());
        vo.setMerchantName(merchant.getName());
        return vo;
    }

    @Override
    public List<MyMerchantCollectionVO> toMerchantMyCollectionVOList(List<MyCollection> list) {

        List<MyMerchantCollectionVO> voList = new ArrayList<MyMerchantCollectionVO>();
        for (MyCollection myCollection : list) {
            voList.add(toMerchantMyCollectionVO(myCollection));
        }
        return voList;
    }

    @Override
    public MyMerchantCollectionVO toMerchantMyCollectionVO(MyCollection myCollection) {

        String json = Json.toJson(myCollection);
        MyMerchantCollectionVO vo = Json.toObject(json, MyMerchantCollectionVO.class, true);
        vo.setMerchantId(myCollection.getObjId());
        QMerchant merchant = sellercenterClient.getMerchant(myCollection.getObjId());
        CollectionMerchant collectionMerchant = new CollectionMerchant();
        collectionMerchant.setClassifyId(-1L);
        collectionMerchant.setName(merchant.getName());
        collectionMerchant.setMerchantId(merchant.getId());
        if (StringUtils.isNotEmpty(merchant.getImage())) {
            collectionMerchant.setImage(fileSDKClient.getFileServerUrl() + merchant.getImage());
        } else {
            collectionMerchant.setImage(StringUtil.nullToEmpty(merchant.getImage()));
        }
        List<QUnifiedMerchandise> merchandiseList = commoditycenterClient.randomUnifiedMerchandise(merchant.getId(), 3);
        List<MerchantHotMerchandise> hotMerchandises = new ArrayList<MerchantHotMerchandise>();
        for (QUnifiedMerchandise qUnifiedMerchandise : merchandiseList) {
            MerchantHotMerchandise merchantHotMerchandise = new MerchantHotMerchandise();
            merchantHotMerchandise.setDiscount(qUnifiedMerchandise.getDiscount());
            merchantHotMerchandise.setImage(fileSDKClient.getFileServerUrl() + qUnifiedMerchandise.getImage());
            merchantHotMerchandise.setMerchandiseId(qUnifiedMerchandise.getList().get(0).getMerchandiseId());
            merchantHotMerchandise.setPrice(qUnifiedMerchandise.getPrice());
            merchantHotMerchandise.setUnifiedMerchandiseId(qUnifiedMerchandise.getId());
            hotMerchandises.add(merchantHotMerchandise);
        }
        collectionMerchant.setHotMerchandises(hotMerchandises);
        vo.setTimeStr(DateUtil.date2String(myCollection.getTime()));
        vo.setCollectionMerchant(collectionMerchant);
        return vo;
    }

    @Override
    public List<MyStoreCollectionVO> toStoreMyCollectionVOList(List<MyCollection> list) {

        List<MyStoreCollectionVO> voList = new ArrayList<MyStoreCollectionVO>();
        for (MyCollection myCollection : list) {
            voList.add(toStoreMyCollectionVO(myCollection));
        }
        return voList;
    }

    @Override
    public MyStoreCollectionVO toStoreMyCollectionVO(MyCollection myCollection) {

        String json = Json.toJson(myCollection);
        MyStoreCollectionVO myStoreCollectionVO = Json.toObject(json, MyStoreCollectionVO.class, true);
        QDepartment department = organizationClient.getDepartment(myCollection.getObjId());
        myStoreCollectionVO.setAddress(department.getAddress());
        myStoreCollectionVO.setName(department.getName());
        myStoreCollectionVO.setPhone(department.getPhone());
        myStoreCollectionVO.setLatitude(department.getLatitude());
        myStoreCollectionVO.setLongitude(department.getLongitude());
        myStoreCollectionVO.setImages(organizationClient.listDepartmentImages(department.getId()));
        myStoreCollectionVO.setActivities(activityForMy.getName(department.getId()));
        myStoreCollectionVO.setStoreId(department.getId());
        myStoreCollectionVO.setShopHour(department.getShopHour());
        return myStoreCollectionVO;
    }
}
