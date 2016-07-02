package com.qcloud.component.marketing.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.marketing.model.Slide;
import com.qcloud.component.marketing.service.SlideService;
import com.qcloud.component.marketing.web.handler.SlideHandler;
import com.qcloud.component.marketing.web.vo.SlideVO;
import com.qcloud.component.marketing.web.vo.admin.AdminSlideVO;
import com.qcloud.component.publicdata.IntKeyValue;
import com.qcloud.pirates.core.json.Json;

@Component
public class SlideHandlerImpl implements SlideHandler {

    @Autowired
    private SlideService  slideService;

    @Autowired
    private FileSDKClient fileSDKClient;

    @Override
    public List<SlideVO> toVOList(List<Slide> list) {

        List<SlideVO> voList = new ArrayList<SlideVO>();
        for (Slide slide : list) {
            voList.add(toVO(slide));
        }
        return voList;
    }

    @Override
    public SlideVO toVO(Slide slide) {

        String json = Json.toJson(slide);
        SlideVO vo = Json.toObject(json, SlideVO.class, true);
        vo.setImage(fileSDKClient.getFileServerUrl() + slide.getImage());
        return vo;
    }

    @Override
    public List<AdminSlideVO> toVOList4Admin(List<Slide> list) {

        List<AdminSlideVO> voList = new ArrayList<AdminSlideVO>();
        for (Slide adminSlide : list) {
            voList.add(toVO4Admin(adminSlide));
        }
        return voList;
    }

    @Override
    public AdminSlideVO toVO4Admin(Slide slide) {

        String json = Json.toJson(slide);
        AdminSlideVO vo = Json.toObject(json, AdminSlideVO.class, true);
        List<IntKeyValue> list = slideService.getSenceTypes();
        for (IntKeyValue intKeyValue : list) {
            if (intKeyValue.getKey() == slide.getSence()) {
                vo.setSenceStr(intKeyValue.getValue());
            }
        }
        String uid = fileSDKClient.urlToUid(slide.getImage());
        vo.setImage(fileSDKClient.getFileServerUrl() + slide.getImage());
        vo.setImageUid(uid);
        return vo;
    }
}
