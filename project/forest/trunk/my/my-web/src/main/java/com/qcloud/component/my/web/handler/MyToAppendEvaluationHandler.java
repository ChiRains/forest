package com.qcloud.component.my.web.handler;

import java.util.List;

import com.qcloud.component.my.model.MyToAppendEvaluation;
import com.qcloud.component.my.web.vo.MyToAppendEvaluationVO;

public interface MyToAppendEvaluationHandler {

	List<MyToAppendEvaluationVO> toVOList(List<MyToAppendEvaluation> list);

	MyToAppendEvaluationVO toVO(MyToAppendEvaluation myToAppendEvaluation);
}
