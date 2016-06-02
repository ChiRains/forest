package com.qcloud.component.personalcenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qcloud.component.personalcenter.web.handler.MyMessageHandler;
import com.qcloud.component.personalcenter.web.vo.MyMessageVO;
import com.qcloud.component.publicservice.QMessage;
import com.qcloud.pirates.util.DateUtil;

@Component
public class MyMessageHandlerImpl implements MyMessageHandler {

    @Override
    public List<MyMessageVO> toVOList(List<QMessage> list) {

        List<MyMessageVO> voList = new ArrayList<MyMessageVO>();
        for (QMessage message : list) {
            voList.add(toVO(message));
        }
        return voList;
    }

    @Override
    public MyMessageVO toVO(QMessage message) {

        MyMessageVO vo = new MyMessageVO();
        vo.setId(message.getId());
        vo.setTitle(message.getTitle());
        vo.setContent(message.getContent());
        vo.setTime(message.getTime());
        vo.setTimeStr(DateUtil.date2String(message.getTime()));
        vo.setRead(message.isRead());
        return vo;
    }
}
