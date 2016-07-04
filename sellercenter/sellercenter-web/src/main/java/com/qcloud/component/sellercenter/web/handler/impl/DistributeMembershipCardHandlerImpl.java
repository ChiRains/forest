package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.organization.OrganizationClient;
import com.qcloud.component.organization.QClerk;
import com.qcloud.component.sellercenter.model.DistributeMembershipCard;
import com.qcloud.component.sellercenter.model.key.TypeEnum.DistributeMembershipCardStateType;
import com.qcloud.component.sellercenter.web.handler.DistributeMembershipCardHandler;
import com.qcloud.component.sellercenter.web.vo.DistributeMembershipCardVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminDistributeMembershipCardVO;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;

@Component
public class DistributeMembershipCardHandlerImpl implements DistributeMembershipCardHandler {

    @Autowired
    OrganizationClient organizationClient;

    @Override
    public List<DistributeMembershipCardVO> toVOList(List<DistributeMembershipCard> list) {

        List<DistributeMembershipCardVO> voList = new ArrayList<DistributeMembershipCardVO>();
        for (DistributeMembershipCard distributeMembershipCard : list) {
            voList.add(toVO(distributeMembershipCard));
        }
        return voList;
    }

    @Override
    public DistributeMembershipCardVO toVO(DistributeMembershipCard distributeMembershipCard) {

        String json = Json.toJson(distributeMembershipCard);
        return Json.toObject(json, DistributeMembershipCardVO.class, true);
    }

    @Override
    public List<AdminDistributeMembershipCardVO> toVOList4Admin(List<DistributeMembershipCard> list) {

        List<AdminDistributeMembershipCardVO> voList = new ArrayList<AdminDistributeMembershipCardVO>();
        for (DistributeMembershipCard adminDistributeMembershipCard : list) {
            voList.add(toVO4Admin(adminDistributeMembershipCard));
        }
        return voList;
    }

    @Override
    public AdminDistributeMembershipCardVO toVO4Admin(DistributeMembershipCard distributeMembershipCard) {

        AdminDistributeMembershipCardVO vo = new AdminDistributeMembershipCardVO();
        //
        vo.setCardNumber(distributeMembershipCard.getCardNumber());
        vo.setMemberMobile("");
        if (DistributeMembershipCardStateType.SENDED.getKey() == distributeMembershipCard.getState() && distributeMembershipCard.getMemberId() > 0) {
            QClerk clerk = organizationClient.getClerk(distributeMembershipCard.getMemberId());
            vo.setMemberMobile(clerk == null ? "" : clerk.getMobile());
        }
        vo.setMerchantName(distributeMembershipCard.getMerchantName());
        if (DistributeMembershipCardStateType.SEND.getKey() == distributeMembershipCard.getState()) {
            vo.setStateStr(DistributeMembershipCardStateType.SEND.getName());
            vo.setTimeStr("");
        } else {
            vo.setStateStr(DistributeMembershipCardStateType.SENDED.getName());
            vo.setTimeStr(DateUtil.date2String(distributeMembershipCard.getTime()));
        }
        //
        return vo;
    }
}
