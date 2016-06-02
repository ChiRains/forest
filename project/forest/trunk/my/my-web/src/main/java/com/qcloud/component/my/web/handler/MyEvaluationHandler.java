package com.qcloud.component.my.web.handler;

import java.util.List;
import com.qcloud.component.my.model.MyEvaluation;
import com.qcloud.component.my.web.vo.MyEvaluationVO;
import com.qcloud.component.my.web.vo.admin.AdminMyEvaluationVO;

public interface MyEvaluationHandler {

	List<MyEvaluationVO> toVOList(List<MyEvaluation> list);

	MyEvaluationVO toVO(MyEvaluation myEvaluation);

	List<AdminMyEvaluationVO> toVOList4Admin(List<MyEvaluation> list);

	AdminMyEvaluationVO toVO4Admin(MyEvaluation myEvaluation);
}
