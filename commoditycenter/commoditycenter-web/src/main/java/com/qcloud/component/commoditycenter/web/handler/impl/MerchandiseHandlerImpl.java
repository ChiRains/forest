package com.qcloud.component.commoditycenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.commoditycenter.model.Merchandise;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseHandler;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseVO;
import com.qcloud.component.commoditycenter.web.vo.SimpleMerchandiseVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminMerchandiseVO;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.core.json.Json;

@Component
public class MerchandiseHandlerImpl implements MerchandiseHandler {

    @Autowired
    private PublicdataClient   publicdataClient;

    // @Autowired
    // private MerchandiseItemService merchandiseItemService;
    @Autowired
    private FileSDKClient      fileSDKClient;

    @Autowired
    private SellercenterClient sellercenterClient;

    // @Autowired
    // private CommoditycenterClient commoditycenterClient;
    @Override
    public List<MerchandiseVO> toVOList(List<Merchandise> list) {

        List<MerchandiseVO> voList = new ArrayList<MerchandiseVO>();
        for (Merchandise merchandise : list) {
            voList.add(toVO(merchandise));
        }
        return voList;
    }

    @Override
    public MerchandiseVO toVO(Merchandise merchandise) {

        String json = Json.toJson(merchandise);
        // MerchandiseItem merchandiseItem = merchandiseItemService.getBySpecifications(merchandise.getId(), -1L);
        MerchandiseVO vo = Json.toObject(json, MerchandiseVO.class, true);
        // vo.setPrice(merchandiseItem.getPrice());
        // vo.setDiscount(merchandiseItem.getDiscount());
        // vo.setSalesVolume(commoditycenterClient.getSalesVolume(merchandise.getId()));
        // if (StringUtils.isNotEmpty(vo.getImage())) {
        // vo.setImage(fileSDKClient.getFileServerUrl() + vo.getImage());
        // }
        return vo;
    }

    @Override
    public List<AdminMerchandiseVO> toVOList4Admin(List<Merchandise> list) {

        List<AdminMerchandiseVO> voList = new ArrayList<AdminMerchandiseVO>();
        for (Merchandise adminMerchandise : list) {
            voList.add(toVO4Admin(adminMerchandise));
        }
        return voList;
    }

    @Override
    public AdminMerchandiseVO toVO4Admin(Merchandise merchandise) {

        String json = Json.toJson(merchandise);
        AdminMerchandiseVO vo = Json.toObject(json, AdminMerchandiseVO.class, true);
        Classify mallClassify = publicdataClient.getClassify(merchandise.getMallClassifyId());
        vo.setMallClassifyStr(mallClassify == null ? "" : mallClassify.getName());
        Classify merchantClassify = publicdataClient.getClassify(merchandise.getMerchantClassifyId());
        vo.setMerchantClassifyStr(merchantClassify == null ? "" : merchantClassify.getName());
        Classify specClassify = publicdataClient.getClassify(merchandise.getSpecClassifyId());
        vo.setSpecClassifyStr(specClassify == null ? "默认规格" : specClassify.getName());
        vo.setImageUid(fileSDKClient.urlToUid(vo.getImage()));
        vo.setImage(fileSDKClient.getFileServerUrl() + vo.getImage());
        QMerchant merchant = sellercenterClient.getMerchant(vo.getMerchantId());
        vo.setMerchantAdmin(merchant.getMerchantAdmin());
        vo.setMerchantCode(merchant.getCode());
        vo.setMerchantName(merchant.getName());
        return vo;
    }

    @Override
    public SimpleMerchandiseVO toSimpleVO(QUnifiedMerchandise merchandise) {

        SimpleMerchandiseVO vo = new SimpleMerchandiseVO();
        vo.setDiscount(merchandise.getDiscount());
        vo.setImage(fileSDKClient.getFileServerUrl() + merchandise.getImage());
        vo.setName(merchandise.getName());
        vo.setUnifiedMerchandiseId(merchandise.getId());
        return vo;
    }

    @Override
    public List<SimpleMerchandiseVO> toSimpleVOList(List<QUnifiedMerchandise> list) {

        List<SimpleMerchandiseVO> voList = new ArrayList<SimpleMerchandiseVO>();
        for (QUnifiedMerchandise qUnifiedMerchandise : list) {
            voList.add(toSimpleVO(qUnifiedMerchandise));
        }
        return voList;
    }
}
