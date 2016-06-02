package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.sellercenter.web.handler.MerchantSpecClassifyHandler;
import com.qcloud.component.sellercenter.model.MerchantMerchandiseClassify;
import com.qcloud.component.sellercenter.model.MerchantSpecClassify;
import com.qcloud.component.sellercenter.web.vo.MerchantMerchandiseClassifyVO;
import com.qcloud.component.sellercenter.web.vo.MerchantSpecClassifyVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantSpecClassifyVO;

@Component
public class MerchantSpecClassifyHandlerImpl implements MerchantSpecClassifyHandler {

    @Autowired
    private PublicdataClient publicdataClient;

    @Override
    public List<MerchantSpecClassifyVO> toVOList(List<MerchantSpecClassify> list) {

        List<MerchantSpecClassifyVO> voList = new ArrayList<MerchantSpecClassifyVO>();
        for (MerchantSpecClassify merchantSpecClassify : list) {
            voList.add(toVO(merchantSpecClassify));
        }
        return voList;
    }

    @Override
    public MerchantSpecClassifyVO toVO(MerchantSpecClassify merchantSpecClassify) {

        String json = Json.toJson(merchantSpecClassify);
        return Json.toObject(json, MerchantSpecClassifyVO.class, true);
    }

    @Override
    public List<AdminMerchantSpecClassifyVO> toVOList4Admin(List<MerchantSpecClassify> list) {

        List<AdminMerchantSpecClassifyVO> voList = new ArrayList<AdminMerchantSpecClassifyVO>();
        for (MerchantSpecClassify adminMerchantSpecClassify : list) {
            voList.add(toVO4Admin(adminMerchantSpecClassify));
        }
        return voList;
    }

    @Override
    public AdminMerchantSpecClassifyVO toVO4Admin(MerchantSpecClassify merchantSpecClassify) {

        String json = Json.toJson(merchantSpecClassify);
        return Json.toObject(json, AdminMerchantSpecClassifyVO.class, true);
    }

    @Override
    public List<AdminMerchantSpecClassifyVO> classifyToVoList(List<MerchantSpecClassify> list, List<Classify> classifyList) {

        List<AdminMerchantSpecClassifyVO> voList = new ArrayList<AdminMerchantSpecClassifyVO>();
        for (Classify classify : classifyList) {
            AdminMerchantSpecClassifyVO vo = new AdminMerchantSpecClassifyVO();
            for (MerchantSpecClassify merchantSpecClassify : list) {
                Classify c = publicdataClient.getClassify(merchantSpecClassify.getClassifyId());
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
}
