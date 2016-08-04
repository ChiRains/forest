package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.model.ShareGiftUser;
import com.qcloud.project.forest.web.handler.ShareGiftUserHandler;
import com.qcloud.project.forest.web.vo.ShareGiftUserVO;

@Component
public class ShareGiftUserHandlerImpl implements ShareGiftUserHandler {

    @Autowired
    private PersonalcenterClient personalcenterClient;

    @Override
    public List<ShareGiftUserVO> toVOList(List<ShareGiftUser> list) {

        List<ShareGiftUserVO> voList = new ArrayList<ShareGiftUserVO>();
        for (ShareGiftUser shareGiftUser : list) {
            voList.add(toVO(shareGiftUser));
        }
        return voList;
    }

    @Override
    public ShareGiftUserVO toVO(ShareGiftUser shareGiftUser) {

        String json = Json.toJson(shareGiftUser);
        ShareGiftUserVO shareGiftUserVO = Json.toObject(json, ShareGiftUserVO.class, true);
        QUser user = personalcenterClient.getUser(shareGiftUser.getUserId());
        shareGiftUserVO.setUserName(user.getName());
        return shareGiftUserVO;
    }
}
