package com.qcloud.component.sellercenter.web.handler;

import com.qcloud.component.sellercenter.model.MerchantEvaluation;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantEvaluationVO;

public interface MerchantEvaluationHandler {

    // List<MerchantEvaluationVO> toVOList(List<MerchantEvaluation> list);
    //
    // MerchantEvaluationVO toVO(MerchantEvaluation merchantEvaluation);

    // List<AdminMerchantEvaluationVO> toVOList4Admin(List<MerchantEvaluation> list);
    //
    // AdminMerchantEvaluationVO toVO4Admin(MerchantEvaluation merchantEvaluation);
    // List<AdminMerchantEvaluationVO> toVOList4Admin(List<MerchandiseEvaluation4Seller> list);
    AdminMerchantEvaluationVO toVO4Admin(MerchantEvaluation merchantEvaluation);
    // Map<Long, QMerchant> toQMerchantMap(List<QMerchant> merchantMembers);
}
