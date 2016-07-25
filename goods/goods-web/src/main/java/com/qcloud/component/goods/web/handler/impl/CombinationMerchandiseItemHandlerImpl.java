package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum.CombinationMerchandiseType;
import com.qcloud.component.goods.service.MerchandiseSpecificationsService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.goods.web.handler.CombinationMerchandiseItemHandler;
import com.qcloud.component.goods.web.vo.CombinationMerchandiseItemVO;
import com.qcloud.component.goods.web.vo.admin.AdminCombinationMerchandiseItemVO;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.StringUtil;

@Component
public class CombinationMerchandiseItemHandlerImpl implements CombinationMerchandiseItemHandler {

    @Autowired
    private UnifiedMerchandiseService        unifiedMerchandiseService;

    @Autowired
    private FileSDKClient                    fileSDKClient;

    @Autowired
    private MerchandiseSpecificationsService merchandiseSpecificationsService;

    @Override
    public List<CombinationMerchandiseItemVO> toVOList(List<CombinationMerchandiseItem> list) {

        List<CombinationMerchandiseItemVO> voList = new ArrayList<CombinationMerchandiseItemVO>();
        for (CombinationMerchandiseItem combinationMerchandiseItem : list) {
            voList.add(toVO(combinationMerchandiseItem));
        }
        return voList;
    }

    @Override
    public CombinationMerchandiseItemVO toVO(CombinationMerchandiseItem combinationMerchandiseItem) {

        CombinationMerchandiseItemVO combinationMerchandiseItemVO = new CombinationMerchandiseItemVO();
        UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(combinationMerchandiseItem.getRelaUnifiedMerchandiseId());
        //
        if (combinationMerchandiseItem.getType() == CombinationMerchandiseType.FREE.getKey()) {
            combinationMerchandiseItemVO.setDiscount(combinationMerchandiseItem.getDiscount());
        } else {
            combinationMerchandiseItemVO.setDiscount(unifiedMerchandise.getDiscount());
        }
        combinationMerchandiseItemVO.setMerchantId(unifiedMerchandise.getMerchantId());
        combinationMerchandiseItemVO.setName(unifiedMerchandise.getName());
        if (StringUtils.isNotEmpty(unifiedMerchandise.getImage())) {
            combinationMerchandiseItemVO.setImage(fileSDKClient.getFileServerUrl() + unifiedMerchandise.getImage());
        } else {
            combinationMerchandiseItemVO.setImage(StringUtil.nullToEmpty(unifiedMerchandise.getImage()));
        }
        combinationMerchandiseItemVO.setNumber(combinationMerchandiseItem.getNumber());
        //
        List<MerchandiseSpecifications> msList = merchandiseSpecificationsService.listByUnifiedMerchandise(unifiedMerchandise.getId());
        StringBuffer sb = new StringBuffer();
        for (MerchandiseSpecifications merchandiseSpecifications : msList) {
            sb.append(merchandiseSpecifications.getValue()).append(" ");
        }
        combinationMerchandiseItemVO.setSpecifications(sb.toString());
        combinationMerchandiseItemVO.setUnifiedMerchandiseId(unifiedMerchandise.getId());
        return combinationMerchandiseItemVO;
    }

    @Override
    public List<AdminCombinationMerchandiseItemVO> toVOList4Admin(List<CombinationMerchandiseItem> list) {

        List<AdminCombinationMerchandiseItemVO> voList = new ArrayList<AdminCombinationMerchandiseItemVO>();
        for (CombinationMerchandiseItem adminCombinationMerchandiseItem : list) {
            voList.add(toVO4Admin(adminCombinationMerchandiseItem));
        }
        return voList;
    }

    @Override
    public AdminCombinationMerchandiseItemVO toVO4Admin(CombinationMerchandiseItem combinationMerchandiseItem) {

        String json = Json.toJson(combinationMerchandiseItem);
        return Json.toObject(json, AdminCombinationMerchandiseItemVO.class, true);
    }
}
