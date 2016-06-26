package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.model.MerchandiseItem;
import com.qcloud.component.goods.service.MerchandiseItemService;
import com.qcloud.component.goods.web.handler.CombinationMerchandiseItemHandler;
import com.qcloud.component.goods.web.vo.CombinationMerchandiseItemVO;
import com.qcloud.component.goods.web.vo.admin.AdminCombinationMerchandiseItemVO;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.StringUtil;

@Component
public class CombinationMerchandiseItemHandlerImpl implements CombinationMerchandiseItemHandler {

    @Autowired
    private MerchandiseItemService merchandiseItemService;

    @Autowired
    private CommoditycenterClient  commoditycenterClient;

    @Autowired
    private FileSDKClient          fileSDKClient;

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
        MerchandiseItem merchandiseItem = merchandiseItemService.get(combinationMerchandiseItem.getMerchandiseItemId());
        //
        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandiseItem.getUnifiedMerchandiseId());
        //
        combinationMerchandiseItemVO.setDiscount(merchandiseItem.getDiscount());
        combinationMerchandiseItemVO.setMerchantId(merchandiseItem.getMerchantId());
        combinationMerchandiseItemVO.setName(merchandiseItem.getName());
        if (StringUtils.isNotEmpty(unifiedMerchandise.getList().get(0).getImage())) {
            combinationMerchandiseItemVO.setImage(fileSDKClient.getFileServerUrl() + unifiedMerchandise.getList().get(0).getImage());
        } else {
            combinationMerchandiseItemVO.setImage(StringUtil.nullToEmpty(unifiedMerchandise.getList().get(0).getImage()));
        }
        combinationMerchandiseItemVO.setNumber(combinationMerchandiseItem.getNum());
        combinationMerchandiseItemVO.setSpecifications(unifiedMerchandise.getList().get(0).getSpecifications());
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
