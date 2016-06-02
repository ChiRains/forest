package com.qcloud.component.my.web.handler;

import java.util.List;
import com.qcloud.component.my.model.MyToEvaluation;
import com.qcloud.component.my.web.vo.MyToEvaluationVO;

public interface MyToEvaluationHandler {

    List<MyToEvaluationVO> toVOList(List<MyToEvaluation> list);

    MyToEvaluationVO toVO(MyToEvaluation myToEvaluation);
}
