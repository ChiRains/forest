package com.qcloud.component.sellercenter.web.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QMerchandiseEvaluation;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.sellercenter.model.MerchantEvaluation;
import com.qcloud.component.sellercenter.web.handler.MerchantEvaluationHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantEvaluationVO;
import com.qcloud.pirates.util.AssertUtil;

@Component
public class MerchantEvaluationHandlerImpl implements MerchantEvaluationHandler {

    @Autowired
    private PersonalcenterClient  personalcenterClient;

    @Autowired(required = false)
    private CommoditycenterClient commoditycenterClient;

    // @Override
    // public List<AdminMerchantEvaluationVO> toVOList4Admin(List<MerchandiseEvaluation4Seller> list) {
    //
    // List<AdminMerchantEvaluationVO> voList = new ArrayList<AdminMerchantEvaluationVO>();
    // for (MerchandiseEvaluation4Seller merchandiseEvaluation4Seller : list) {
    // voList.add(toVO4Admin(merchandiseEvaluation4Seller));
    // }
    // return voList;
    // }
    @Override
    public AdminMerchantEvaluationVO toVO4Admin(MerchantEvaluation merchantEvaluation) {

        AdminMerchantEvaluationVO vo = new AdminMerchantEvaluationVO();
        vo.setId(merchantEvaluation.getEvaluationId());
        // vo.setMerchandiseName(merchandiseService.get(merchandiseEvaluation.getMerchandiseId()).getName());
        AssertUtil.assertNotNull(commoditycenterClient, "商城接口实例尚未实例化.");
        QMerchandiseEvaluation merchandiseEvaluation = commoditycenterClient.getMerchandiseEvaluation(merchantEvaluation.getEvaluationId(), merchantEvaluation.getMerchandiseId());
        vo.setMerchandiseName(merchandiseEvaluation.getMerchandiseName());
        vo.setMerchandiseId(merchantEvaluation.getMerchandiseId());
        vo.setContent(merchandiseEvaluation.getContent());
        vo.setStar(merchandiseEvaluation.getStar());
        vo.setTime(merchandiseEvaluation.getTime());
        vo.setStatus(merchandiseEvaluation.getStatus());
        vo.setSpecifications(merchandiseEvaluation.getSpecifications());
        QUser user = personalcenterClient.getUser(merchandiseEvaluation.getUserId());
        vo.setUserName(user != null ? user.getNickname() : "");
        int fullStarLen = merchandiseEvaluation.getStar() / 10;
        for (int i = 0; i < fullStarLen; i++) {
            vo.getStars().add(Integer.valueOf(0));
        }
        if (merchandiseEvaluation.getStar() % 10 != 0) {
            vo.getStars().add(Integer.valueOf(1));
        }
        return vo;
    }
    // @Override
    // public Map<Long, QMerchant> toQMerchantMap(List<QMerchant> merchantMembers) {
    //
    // Map<Long, QMerchant> map = new HashMap<Long, QMerchant>();
    // for (QMerchant qMerchant : merchantMembers) {
    // map.put(qMerchant.getId(), qMerchant);
    // }
    // return map;
    // }
}
