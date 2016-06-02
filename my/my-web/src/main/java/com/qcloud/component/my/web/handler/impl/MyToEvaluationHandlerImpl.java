package com.qcloud.component.my.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.my.model.MyToEvaluation;
import com.qcloud.component.my.web.handler.MyToEvaluationHandler;
import com.qcloud.component.my.web.vo.MyToEvaluationVO;

@Component
public class MyToEvaluationHandlerImpl implements MyToEvaluationHandler {

    @Autowired
    private FileSDKClient fileSDKClient;

    @Override
    public List<MyToEvaluationVO> toVOList(List<MyToEvaluation> list) {

        List<MyToEvaluationVO> voList = new ArrayList<MyToEvaluationVO>();
        for (MyToEvaluation myToEvaluation : list) {
            voList.add(toVO(myToEvaluation));
        }
        return voList;
    }

    @Override
    public MyToEvaluationVO toVO(MyToEvaluation myToEvaluation) {

        MyToEvaluationVO myToEvaluationVO = new MyToEvaluationVO();
        myToEvaluationVO.setDiscount(myToEvaluation.getDiscount());
        myToEvaluationVO.setId(myToEvaluation.getId());
        myToEvaluationVO.setName(myToEvaluation.getName());
        myToEvaluationVO.setImage(fileSDKClient.getFileServerUrl() + myToEvaluation.getImage());
        myToEvaluationVO.setOrderNumber(myToEvaluation.getOrderNumber());
        return myToEvaluationVO;
    }
}
