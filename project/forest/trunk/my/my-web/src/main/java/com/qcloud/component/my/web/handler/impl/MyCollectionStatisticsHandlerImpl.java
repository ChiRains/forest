package com.qcloud.component.my.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.my.model.MyCollectionStatistics;
import com.qcloud.component.my.web.handler.MyCollectionStatisticsHandler;
import com.qcloud.component.my.web.vo.MyCollectionStatisticsVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.core.json.Json;

@Component
public class MyCollectionStatisticsHandlerImpl implements MyCollectionStatisticsHandler {

    @Autowired
    PublicdataClient publicdataClient;

    @Override
    public List<MyCollectionStatisticsVO> toVOList(List<MyCollectionStatistics> list) {

        int total = 0;
        List<MyCollectionStatisticsVO> voList = new ArrayList<MyCollectionStatisticsVO>();
        MyCollectionStatisticsVO vo = new MyCollectionStatisticsVO();
        vo.setClassifyId(-1L);
        vo.setClassifyName("所有分类");
        voList.add(vo);
        for (MyCollectionStatistics myCollectionStatistics : list) {
            voList.add(toVO(myCollectionStatistics));
            total += myCollectionStatistics.getNumber();
        }
        vo.setNumber(total);
        return voList;
    }

    @Override
    public MyCollectionStatisticsVO toVO(MyCollectionStatistics myCollectionStatistics) {

        String json = Json.toJson(myCollectionStatistics);
        MyCollectionStatisticsVO vo = Json.toObject(json, MyCollectionStatisticsVO.class, true);
        Classify classify = publicdataClient.getClassify(myCollectionStatistics.getClassifyId());
        if (classify != null) {
            vo.setClassifyName(classify.getName());
        }
        return vo;
    }
}
