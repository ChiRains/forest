package com.qcloud.component.my.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.my.web.handler.MyToAppendEvaluationHandler;
import com.qcloud.component.my.model.MyToAppendEvaluation;
import com.qcloud.component.my.web.vo.MyToAppendEvaluationVO;

@Component
public class MyToAppendEvaluationHandlerImpl implements MyToAppendEvaluationHandler {

    @Override
    public List<MyToAppendEvaluationVO> toVOList(List<MyToAppendEvaluation> list) {

        List<MyToAppendEvaluationVO> voList = new ArrayList<MyToAppendEvaluationVO>();
        for (MyToAppendEvaluation myToAppendEvaluation : list) {
            voList.add(toVO(myToAppendEvaluation));
        }
        return voList;
    }

    @Override
    public MyToAppendEvaluationVO toVO(MyToAppendEvaluation myToAppendEvaluation) {

        String json = Json.toJson(myToAppendEvaluation);
        return Json.toObject(json, MyToAppendEvaluationVO.class, true);
    }
}
