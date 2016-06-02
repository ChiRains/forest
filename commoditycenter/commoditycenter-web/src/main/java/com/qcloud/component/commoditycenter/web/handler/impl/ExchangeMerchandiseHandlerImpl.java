package com.qcloud.component.commoditycenter.web.handler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise.MerchandiseType;
import com.qcloud.component.commoditycenter.model.Merchandise;
import com.qcloud.component.commoditycenter.model.MerchandiseEvaluation;
import com.qcloud.component.commoditycenter.model.MerchandiseImage;
import com.qcloud.component.commoditycenter.model.MerchandiseItem;
import com.qcloud.component.commoditycenter.model.MerchandiseMarketing;
import com.qcloud.component.commoditycenter.model.MerchandiseSpecifications;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.StarLevelType;
import com.qcloud.component.commoditycenter.service.MerchandiseItemService;
import com.qcloud.component.commoditycenter.web.handler.ExchangeMerchandiseHandler;
import com.qcloud.component.commoditycenter.web.vo.ExchangeMerchandiseListVO;
import com.qcloud.component.commoditycenter.web.vo.ExchangeMerchandiseVO;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseEvaluationVO;
import com.qcloud.component.commoditycenter.web.vo.MerchantMerchandiseVO;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;

@Component
public class ExchangeMerchandiseHandlerImpl implements ExchangeMerchandiseHandler {

    @Autowired
    CommoditycenterClient  commoditycenterClient;

    @Autowired
    MerchandiseItemService merchandiseItemService;

    @Autowired
    FileSDKClient          fileSDKClient;

    @Override
    public List<ExchangeMerchandiseListVO> toVOList(List<MerchandiseMarketing> list) {

        List<ExchangeMerchandiseListVO> voList = new ArrayList<ExchangeMerchandiseListVO>();
        for (MerchandiseMarketing merchandiseMarketing : list) {
            QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandiseMarketing.getUnifiedMerchandiseId());
            AssertUtil.assertNotNull(unifiedMerchandise, "统一商品ID不存在");
            ExchangeMerchandiseListVO exchangeMerchandiseListVO = new ExchangeMerchandiseListVO();
            exchangeMerchandiseListVO.setCurrency(merchandiseMarketing.getDiscount());
            if (StringUtils.isNotEmpty(unifiedMerchandise.getImage())) {
                exchangeMerchandiseListVO.setImage(fileSDKClient.getFileServerUrl() + unifiedMerchandise.getImage());
            } else {
                exchangeMerchandiseListVO.setImage(StringUtil.nullToEmpty(unifiedMerchandise.getImage()));
            }
            exchangeMerchandiseListVO.setName(unifiedMerchandise.getName());
            exchangeMerchandiseListVO.setPrice(merchandiseMarketing.getPrice());
            exchangeMerchandiseListVO.setStock(merchandiseMarketing.getStock());
            exchangeMerchandiseListVO.setUnifiedMerchandiseId(merchandiseMarketing.getUnifiedMerchandiseId());
            voList.add(exchangeMerchandiseListVO);
        }
        return voList;
    }     
}
