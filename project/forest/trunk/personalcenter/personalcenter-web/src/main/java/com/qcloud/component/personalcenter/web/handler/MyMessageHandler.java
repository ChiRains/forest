package com.qcloud.component.personalcenter.web.handler;

import java.util.List;

import com.qcloud.component.personalcenter.web.vo.MyMessageVO;
import com.qcloud.component.publicservice.QMessage;

public interface MyMessageHandler {

    List<MyMessageVO> toVOList(List<QMessage> list);

    MyMessageVO toVO(QMessage QMessage);
}
