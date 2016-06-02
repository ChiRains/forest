package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.publicdata.SexType;
import com.qcloud.component.sellercenter.model.MerchantSort;
import com.qcloud.component.sellercenter.web.handler.MerchantSortHandler;
import com.qcloud.component.sellercenter.web.vo.MerchantSortVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMemberVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantSortVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class MerchantSortHandlerImpl implements MerchantSortHandler {

    @Autowired
    private FileSDKClient fileSDKClient;

    @Override
    public List<MerchantSortVO> toVOList(List<MerchantSort> list) {

        List<MerchantSortVO> voList = new ArrayList<MerchantSortVO>();
        for (MerchantSort merchantSort : list) {
            voList.add(toVO(merchantSort));
        }
        return voList;
    }

    @Override
    public MerchantSortVO toVO(MerchantSort merchantSort) {

        String json = Json.toJson(merchantSort);
        MerchantSortVO vo = Json.toObject(json, MerchantSortVO.class, true);
        if (StringUtils.isNotEmpty(vo.getLogo())) {
            vo.setLogo(fileSDKClient.getFileServerUrl() + vo.getLogo());
        }
        return vo;
    }

    @Override
    public List<AdminMerchantSortVO> toVOList4Admin(List<MerchantSort> list) {

        List<AdminMerchantSortVO> voList = new ArrayList<AdminMerchantSortVO>();
        for (MerchantSort adminMerchantSort : list) {
            voList.add(toVO4Admin(adminMerchantSort));
        }
        return voList;
    }

    @Override
    public AdminMerchantSortVO toVO4Admin(MerchantSort merchantSort) {

        String json = Json.toJson(merchantSort);
        AdminMerchantSortVO vo = Json.toObject(json, AdminMerchantSortVO.class, true);
        vo.setLogoUID(fileSDKClient.urlToUid(vo.getLogo()));
        return vo;
    }
}
