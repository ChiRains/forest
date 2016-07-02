package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.sellercenter.model.StoreMember;
import com.qcloud.component.sellercenter.web.handler.StoreMemberHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminStoreMemberVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class StoreMemberHandlerImpl implements StoreMemberHandler {

    @Override
    public List<AdminStoreMemberVO> toVOList4Admin(List<StoreMember> list) {

        List<AdminStoreMemberVO> voList = new ArrayList<AdminStoreMemberVO>();
        for (StoreMember adminStoreMember : list) {
            voList.add(toVO4Admin(adminStoreMember));
        }
        return voList;
    }

    @Override
    public AdminStoreMemberVO toVO4Admin(StoreMember storeMember) {

        String json = Json.toJson(storeMember);
        return Json.toObject(json, AdminStoreMemberVO.class, true);
    }
}
