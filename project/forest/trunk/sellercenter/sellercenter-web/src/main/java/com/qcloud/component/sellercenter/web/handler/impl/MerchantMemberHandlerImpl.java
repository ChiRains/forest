package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.sellercenter.model.MerchantMember;
import com.qcloud.component.sellercenter.web.handler.MerchantMemberHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantMemberVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class MerchantMemberHandlerImpl implements MerchantMemberHandler {

    @Override
    public List<AdminMerchantMemberVO> toVOList4Admin(List<MerchantMember> list) {

        List<AdminMerchantMemberVO> voList = new ArrayList<AdminMerchantMemberVO>();
        for (MerchantMember adminMerchantMember : list) {
            voList.add(toVO4Admin(adminMerchantMember));
        }
        return voList;
    }

    @Override
    public AdminMerchantMemberVO toVO4Admin(MerchantMember merchantMember) {

        String json = Json.toJson(merchantMember);
        return Json.toObject(json, AdminMerchantMemberVO.class, true);
    }
}
