package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.sellercenter.model.MerchantMerchandiseClassify;
import com.qcloud.component.sellercenter.web.handler.MerchantMerchandiseClassifyHandler;
import com.qcloud.component.sellercenter.web.vo.MerchantMerchandiseClassifyVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantMerchandiseClassifyVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class MerchantMerchandiseClassifyHandlerImpl implements MerchantMerchandiseClassifyHandler {

    @Autowired
    private PublicdataClient publicdataClient;

    @Override
    public List<MerchantMerchandiseClassifyVO> classifyToVoList(List<MerchantMerchandiseClassify> list, List<Classify> classifyList) {

        List<MerchantMerchandiseClassifyVO> voList = new ArrayList<MerchantMerchandiseClassifyVO>();
        for (Classify classify : classifyList) {
            MerchantMerchandiseClassifyVO vo = new MerchantMerchandiseClassifyVO();
            for (MerchantMerchandiseClassify merchantMerchandiseClassify : list) {
                Classify c = publicdataClient.getClassify(merchantMerchandiseClassify.getClassifyId());
                if (c != null) if (classify.getBsid().startsWith(c.getBsid())) {
                    vo.setMessage("checked='checked'");
                }
            }
            vo.setParentId(classify.getParentId());
            vo.setId(classify.getId());
            vo.setName(classify.getName());
            vo.setBsid(classify.getBsid());
            vo.setType(classify.getType());
            vo.setRemark(classify.getRemark());
            vo.setImage(classify.getImage());
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public List<AdminMerchantMerchandiseClassifyVO> toVOList4Admin(List<MerchantMerchandiseClassify> list) {

        List<AdminMerchantMerchandiseClassifyVO> voList = new ArrayList<AdminMerchantMerchandiseClassifyVO>();
        for (MerchantMerchandiseClassify adminMerchantMerchandiseClassify : list) {
            voList.add(toVO4Admin(adminMerchantMerchandiseClassify));
        }
        return voList;
    }

    @Override
    public AdminMerchantMerchandiseClassifyVO toVO4Admin(MerchantMerchandiseClassify merchantMerchandiseClassify) {

        String json = Json.toJson(merchantMerchandiseClassify);
        return Json.toObject(json, AdminMerchantMerchandiseClassifyVO.class, true);
    }
}
