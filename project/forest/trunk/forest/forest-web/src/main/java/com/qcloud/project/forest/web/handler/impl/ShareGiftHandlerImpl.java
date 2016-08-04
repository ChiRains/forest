package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.model.ShareGift;
import com.qcloud.project.forest.web.handler.ShareGiftHandler;
import com.qcloud.project.forest.web.vo.ShareGiftVO;

@Component
public class ShareGiftHandlerImpl implements ShareGiftHandler {

    @Override
    public List<ShareGiftVO> toVOList(List<ShareGift> list) {

        List<ShareGiftVO> voList = new ArrayList<ShareGiftVO>();
        for (ShareGift shareGift : list) {
            voList.add(toVO(shareGift));
        }
        return voList;
    }

    @Override
    public ShareGiftVO toVO(ShareGift shareGift) {

        String json = Json.toJson(shareGift);
        return Json.toObject(json, ShareGiftVO.class, true);
    }
}
