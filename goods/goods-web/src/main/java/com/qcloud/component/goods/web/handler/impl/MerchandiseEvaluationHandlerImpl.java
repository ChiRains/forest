package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.goods.UnifiedMerchandiseType;
import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.goods.web.handler.MerchandiseEvaluationHandler;
import com.qcloud.component.goods.web.vo.MerchandiseEvaluationVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseEvaluationVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;

@Component
public class MerchandiseEvaluationHandlerImpl implements MerchandiseEvaluationHandler {

    @Autowired
    private PersonalcenterClient      personalcenterClient;

    @Autowired
    private MerchandiseService        merchandiseService;

    @Autowired
    private UnifiedMerchandiseService unifiedMerchandiseService;

    @Override
    public List<MerchandiseEvaluationVO> toVOList(List<MerchandiseEvaluation> list) {

        List<MerchandiseEvaluationVO> voList = new ArrayList<MerchandiseEvaluationVO>();
        for (MerchandiseEvaluation me : list) {
            MerchandiseEvaluationVO vo = new MerchandiseEvaluationVO();
            vo.setMerchandiseId(me.getMerchandiseId());
            vo.setContent(me.getContent());
            vo.setStar(me.getStar());
            vo.setTime(DateUtil.date2String(me.getTime(), "yyyy-MM-dd HH:mm"));
            vo.setSpecifications(me.getSpecifications());
            QUser user = personalcenterClient.getUser(me.getUserId());
            if (user == null) {
                vo.setUserName("神秘用户");
                vo.setHeadImage("");
            } else {
                vo.setUserName(user.getNickname());
                vo.setHeadImage(user.getHeadImage());
            }
            List<UnifiedMerchandise> merchandiseList = unifiedMerchandiseService.listByMerchandise(me.getMerchandiseId(), UnifiedMerchandiseType.SINGLE.getKey());
            if (merchandiseList.size() == 0) {
                vo.setGoodEvaluation(Integer.valueOf(0));
                vo.setMiddleEvaluation(Integer.valueOf(0));
                vo.setLowEvaluation(Integer.valueOf(0));
            } else {
                vo.setGoodEvaluation(merchandiseList.get(0).getGoodEvaluation());
                vo.setMiddleEvaluation(merchandiseList.get(0).getMiddleEvaluation());
                vo.setLowEvaluation(merchandiseList.get(0).getLowEvaluation());
            }
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public MerchandiseEvaluationVO toVO(MerchandiseEvaluation merchandiseEvaluation) {

        String json = Json.toJson(merchandiseEvaluation);
        return Json.toObject(json, MerchandiseEvaluationVO.class, true);
    }

    @Override
    public List<AdminMerchandiseEvaluationVO> toVOList4Admin(List<MerchandiseEvaluation> list) {

        List<AdminMerchandiseEvaluationVO> voList = new ArrayList<AdminMerchandiseEvaluationVO>();
        for (MerchandiseEvaluation merchandiseEvaluation : list) {
            AdminMerchandiseEvaluationVO vo = new AdminMerchandiseEvaluationVO();
            vo.setId(merchandiseEvaluation.getId());
            vo.setMerchandiseName(merchandiseService.get(merchandiseEvaluation.getMerchandiseId()).getName());
            // vo.setMerchandiseName("笔记本电脑");
            vo.setMerchandiseId(merchandiseEvaluation.getMerchandiseId());
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
            voList.add(vo);
        }
        return voList;
    }
    //
    // @Override
    // public AdminMerchandiseEvaluationVO toVO4Admin(MerchandiseEvaluation merchandiseEvaluation) {
    //
    // String json = Json.toJson(merchandiseEvaluation);
    // return Json.toObject(json, AdminMerchandiseEvaluationVO.class, true);
    // }
    // @Override
    // public List<MerchandiseEvaluationVO> toVOPageList(List<MerchandiseEvaluation> list) {
    //
    // List<MerchandiseEvaluationVO> voList = new ArrayList<MerchandiseEvaluationVO>();
    // for (MerchandiseEvaluation me : list) {
    // MerchandiseEvaluationVO vo = new MerchandiseEvaluationVO();
    // vo.setMerchandiseId(me.getMerchandiseId());
    // vo.setContent(me.getContent());
    // vo.setStar(me.getStar());
    // vo.setTime(DateUtil.date2String(me.getTime(), "yyyy-MM-dd HH:mm"));
    // vo.setSpecifications(me.getSpecifications());
    // User user = userService.get(me.getUserId());
    // if (user == null) {
    // vo.setUserName("神秘用户");
    // vo.setHeadImage("");
    // } else {
    // vo.setUserName(user.getNickname());
    // vo.setHeadImage(user.getHeadImage());
    // }
    // voList.add(vo);
    // }
    // return voList;
    // }
}