package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.sellercenter.model.Sexpress;
import com.qcloud.component.sellercenter.web.handler.SexpressHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminSexpressVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class SexpressHandlerImpl implements SexpressHandler {

    @Autowired
    private FileSDKClient fileSDKClient;

    @Override
    public List<AdminSexpressVO> toVOList4Admin(List<Sexpress> list) {

        List<AdminSexpressVO> voList = new ArrayList<AdminSexpressVO>();
        for (Sexpress adminSexpress : list) {
            voList.add(toVO4Admin(adminSexpress));
        }
        return voList;
    }

    @Override
    public AdminSexpressVO toVO4Admin(Sexpress sexpress) {

        String json = Json.toJson(sexpress);
        AdminSexpressVO vo = Json.toObject(json, AdminSexpressVO.class, true);
        vo.setLogoUid(fileSDKClient.urlToUid(vo.getLogo()));
        return vo;
    }
}
