package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.publicdata.SexType;
import com.qcloud.component.sellercenter.model.Member;
import com.qcloud.component.sellercenter.web.handler.MemberHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMember4MerchantVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMemberVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class MemberHandlerImpl implements MemberHandler {

    @Override
    public List<AdminMemberVO> toVOList4Admin(List<Member> list) {

        List<AdminMemberVO> voList = new ArrayList<AdminMemberVO>();
        for (Member adminMember : list) {
            voList.add(toVO4Admin(adminMember));
        }
        return voList;
    }

    @Override
    public AdminMemberVO toVO4Admin(Member member) {

        String json = Json.toJson(member);
        AdminMemberVO vo = Json.toObject(json, AdminMemberVO.class, true);
        String sexStr = SexType.UNKNOW.getValue();
        if (SexType.MALE.getKey() == member.getSex()) {
            sexStr = SexType.MALE.getValue();
            System.out.println(sexStr);
        } else if (SexType.FEMALE.getKey() == member.getSex()) {
            sexStr = SexType.FEMALE.getValue();
        }
        vo.setSexStr(sexStr);
        return vo;
    }

    @Override
    public List<AdminMember4MerchantVO> toVOList4Merchant(List<Member> list, List<Long> keyList) {

        List<AdminMember4MerchantVO> voList = new ArrayList<AdminMember4MerchantVO>();
        for (Member member : list) {
            AdminMember4MerchantVO vo = new AdminMember4MerchantVO();
            vo.setMemberId(member.getId());
            vo.setName(member.getName());
            vo.setChecked("");
            for (Long key : keyList) {
                if (member.getId() == key) {
                    vo.setChecked("checked");
                    break;
                }
            }
            voList.add(vo);
        }
        return voList;
    }
}
