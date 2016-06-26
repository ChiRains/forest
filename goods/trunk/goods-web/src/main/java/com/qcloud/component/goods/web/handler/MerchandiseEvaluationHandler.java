package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.web.vo.MerchandiseEvaluationVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseEvaluationVO;

public interface MerchandiseEvaluationHandler {

	List<MerchandiseEvaluationVO> toVOList(List<MerchandiseEvaluation> list);

	MerchandiseEvaluationVO toVO(MerchandiseEvaluation merchandiseEvaluation);

	List<AdminMerchandiseEvaluationVO> toVOList4Admin(List<MerchandiseEvaluation> list);

	AdminMerchandiseEvaluationVO toVO4Admin(MerchandiseEvaluation merchandiseEvaluation);
}
