package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;
import com.qcloud.component.commoditycenter.model.MerchandiseEvaluation;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseEvaluationVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminMerchandiseEvaluationVO;

public interface MerchandiseEvaluationHandler {

    List<MerchandiseEvaluationVO> toVOList(List<MerchandiseEvaluation> list);

    MerchandiseEvaluationVO toVO(MerchandiseEvaluation merchandiseEvaluation);

    List<AdminMerchandiseEvaluationVO> toVOList4Admin(List<MerchandiseEvaluation> list);
    //
    // AdminMerchandiseEvaluationVO toVO4Admin(MerchandiseEvaluation merchandiseEvaluation);
    //
    // List<MerchandiseEvaluationVO> toVOPageList(List<MerchandiseEvaluation> data);
}
