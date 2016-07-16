package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QMerchandise;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.web.handler.PartsMerchandiseHandler;
import com.qcloud.project.forest.model.PartsMerchandise;
import com.qcloud.project.forest.web.vo.PartsMerchandiseVO;
import com.qcloud.project.forest.web.vo.admin.AdminPartsMerchandiseVO;

@Component
public class PartsMerchandiseHandlerImpl implements PartsMerchandiseHandler {

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Override
    public List<PartsMerchandiseVO> toVOList(List<PartsMerchandise> list) {

        List<PartsMerchandiseVO> voList = new ArrayList<PartsMerchandiseVO>();
        for (PartsMerchandise partsMerchandise : list) {
            voList.add(toVO(partsMerchandise));
        }
        return voList;
    }

    @Override
    public PartsMerchandiseVO toVO(PartsMerchandise partsMerchandise) {

        String json = Json.toJson(partsMerchandise);
        PartsMerchandiseVO vo = Json.toObject(json, PartsMerchandiseVO.class, true);
        QMerchandise qMerchandise = commoditycenterClient.getMerchandise(vo.getMerchandiseId());
        vo.setqMerchandise(qMerchandise);
        return vo;
    }

    @Override
    public List<AdminPartsMerchandiseVO> toVOList4Admin(List<PartsMerchandise> list) {

        List<AdminPartsMerchandiseVO> voList = new ArrayList<AdminPartsMerchandiseVO>();
        for (PartsMerchandise adminPartsMerchandise : list) {
            voList.add(toVO4Admin(adminPartsMerchandise));
        }
        return voList;
    }

    @Override
    public AdminPartsMerchandiseVO toVO4Admin(PartsMerchandise partsMerchandise) {

        String json = Json.toJson(partsMerchandise);
        return Json.toObject(json, AdminPartsMerchandiseVO.class, true);
    }
}
