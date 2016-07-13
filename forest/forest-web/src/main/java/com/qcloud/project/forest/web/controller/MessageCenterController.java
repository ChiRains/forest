package com.qcloud.project.forest.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicservice.MessageClient;
import com.qcloud.component.publicservice.QMessage;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.project.forest.model.key.TypeEnum.MessageClassify;
import com.qcloud.project.forest.model.key.TypeEnum.MessageType;
import com.qcloud.project.forest.web.vo.MessageNumVO;
import com.qcloud.project.forest.web.vo.MessageVO;

@Controller
@RequestMapping(value = MessageCenterController.DIR)
public class MessageCenterController {

    public static final String DIR = "/messageCenter";

    @Autowired
    public MessageClient       messageClient;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getMessageTypeAndNum(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MessageNumVO> messageNumVOs = new ArrayList<MessageNumVO>();
        // 站内消息
        int userPrivateMessageNum = messageClient.countUnreadByReceiver(MessageType.USER.getKey(), MessageClassify.USERPRIVATEMESSAGE.getKey(), user.getId());
        MessageNumVO userPrivateMessageNumVO = new MessageNumVO();
        userPrivateMessageNumVO.setNum(userPrivateMessageNum);
        userPrivateMessageNumVO.setType(MessageClassify.USERPRIVATEMESSAGE.getKey());
        messageNumVOs.add(userPrivateMessageNumVO);
        // 物流信息
        int expressMessageNum = messageClient.countUnreadByReceiver(MessageType.USER.getKey(), MessageClassify.LOGISTICS.getKey(), user.getId());
        MessageNumVO expressMessageNumVO = new MessageNumVO();
        expressMessageNumVO.setNum(expressMessageNum);
        expressMessageNumVO.setType(MessageClassify.LOGISTICS.getKey());
        messageNumVOs.add(expressMessageNumVO);
        // 优惠券
        int couponsMessageNum = messageClient.countUnreadByReceiver(MessageType.USER.getKey(), MessageClassify.COUPONS.getKey(), user.getId());
        MessageNumVO couponsMessageNumVO = new MessageNumVO();
        couponsMessageNumVO.setNum(couponsMessageNum);
        couponsMessageNumVO.setType(MessageClassify.COUPONS.getKey());
        messageNumVOs.add(couponsMessageNumVO);
        // 付款提醒
        int paymentMessageNum = messageClient.countUnreadByReceiver(MessageType.USER.getKey(), MessageClassify.PAYMENT.getKey(), user.getId());
        MessageNumVO paymentMessageNumVO = new MessageNumVO();
        paymentMessageNumVO.setNum(paymentMessageNum);
        paymentMessageNumVO.setType(MessageClassify.PAYMENT.getKey());
        messageNumVOs.add(paymentMessageNumVO);
        // 用药提醒
        int medicationRemindersmentMessageNum = messageClient.countUnreadByReceiver(MessageType.USER.getKey(), MessageClassify.MEDICATIONREMINDERS.getKey(), user.getId());
        MessageNumVO medicationRemindersmentMessageNumVO = new MessageNumVO();
        medicationRemindersmentMessageNumVO.setNum(medicationRemindersmentMessageNum);
        medicationRemindersmentMessageNumVO.setType(MessageClassify.MEDICATIONREMINDERS.getKey());
        messageNumVOs.add(medicationRemindersmentMessageNumVO);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("result", messageNumVOs);
        return frontAjaxView;
    }

    @PiratesApp
    @RequestMapping
    public FrontPagingView getMessageList(HttpServletRequest request, PPage pPage, int type) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<QMessage> messages = messageClient.listContentByReceiver(MessageType.USER.getKey(), type, user.getId(), pPage.getPageStart(), pPage.getPageSize());
        int messageNum = messageClient.countByReceiver(MessageType.USER.getKey(), type, user.getId());
        List<MessageVO> messageVOs = new ArrayList<MessageVO>();
        MessageVO messageVO = null;
        for (QMessage qMessage : messages) {
            messageVO = new MessageVO();
            messageVO.setContent(qMessage.getContent());
            messageVO.setTime(DateUtil.date2String(qMessage.getTime(), "yyyy-MM-dd HH:mm:ss"));
            messageVO.setUrl(qMessage.getTitle());
            messageVOs.add(messageVO);
        }
        FrontPagingView frontPagingView = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), messageNum);
        frontPagingView.setList(messageVOs);
        return frontPagingView;
    }
}
