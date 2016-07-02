package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.sellercenter.model.Store;
import com.qcloud.component.sellercenter.web.handler.StoreHandler;
import com.qcloud.component.sellercenter.web.vo.StoreVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminStoreVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class StoreHandlerImpl implements StoreHandler {

    @Autowired
    private FileSDKClient fileSDKClient;

    @Override
    public List<StoreVO> toVOList(List<Store> list) {

        List<StoreVO> voList = new ArrayList<StoreVO>();
        for (Store store : list) {
            voList.add(toVO(store));
        }
        return voList;
    }

    @Override
    public StoreVO toVO(Store store) {

        String json = Json.toJson(store);
        return Json.toObject(json, StoreVO.class, true);
    }

    @Override
    public List<AdminStoreVO> toVOList4Admin(List<Store> list) {

        List<AdminStoreVO> voList = new ArrayList<AdminStoreVO>();
        for (Store adminStore : list) {
            voList.add(toVO4Admin(adminStore));
        }
        return voList;
    }

    @Override
    public AdminStoreVO toVO4Admin(Store store) {

        String json = Json.toJson(store);
        AdminStoreVO vo = Json.toObject(json, AdminStoreVO.class, true);
        String logoUid = fileSDKClient.urlToUid(store.getLogo());
        vo.setLogoUid(logoUid);
        vo.setLogo(fileSDKClient.getFileServerUrl() + vo.getLogo());
        return vo;
    }
}
