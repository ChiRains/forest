package com.qcloud.component.personalcenter.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.personalcenter.UserMessageType;
import com.qcloud.component.personalcenter.model.key.TypeEnum;
import com.qcloud.component.personalcenter.web.handler.MyMessageHandler;
import com.qcloud.component.personalcenter.web.vo.MyMessageVO;
import com.qcloud.component.piratesship.web.form.ListForm;
import com.qcloud.component.publicservice.MessageClient;
import com.qcloud.component.publicservice.QMessage;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = MyMessageController.DIR)
public class MyMessageController {

    public static final String DIR = "/myMessage";

    @Autowired
    private MessageClient      messageClient;

    @Autowired
    private MyMessageHandler   myMessageHandler;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<QMessage> list = messageClient.listByReceiver(TypeEnum.USER_MESSAGE_CODE, UserMessageType.INSIDE_LETTER.getKey(), user.getId(), pPage.getPageStart(), pPage.getPageSize());
        List<MyMessageVO> voList = myMessageHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的消息成功.");
        view.addObject("list", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView countAll(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        int number = messageClient.countByReceiver(TypeEnum.USER_MESSAGE_CODE, UserMessageType.INSIDE_LETTER.getKey(), user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的消息条数成功.");
        view.addObject("total", number);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView countUnread(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        int number = messageClient.countUnreadByReceiver(TypeEnum.USER_MESSAGE_CODE, UserMessageType.INSIDE_LETTER.getKey(), user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的消息未读条数成功.");
        view.addObject("unreadTotal", number);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getNewMsgNumber(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        int number = messageClient.getNewMsgNumber(TypeEnum.USER_MESSAGE_CODE, user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取新短消息成功.");
        view.addObject("result", number);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView resetNewMsgNumber(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        messageClient.resetNewMsgNumber(TypeEnum.USER_MESSAGE_CODE, user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("重置新消息条数为0成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView get(HttpServletRequest request, Long id) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        QMessage message = messageClient.get(TypeEnum.USER_MESSAGE_CODE, user.getId(), id);
        AssertUtil.assertNotNull(message, "消息不存在." + id);
        MyMessageVO myMessageVO = myMessageHandler.toVO(message);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的一条消息成功.");
        view.addObject("message", myMessageVO);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView read(HttpServletRequest request, Long id) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        messageClient.read(TypeEnum.USER_MESSAGE_CODE, user.getId(), id);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("设置一条消息为已读成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView delete(HttpServletRequest request, Long id) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        messageClient.delete(TypeEnum.USER_MESSAGE_CODE, user.getId(), id);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("删除一条消息成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView deleteList(HttpServletRequest request, ListForm form) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        for (Long id : form.getLongList()) {
            messageClient.delete(TypeEnum.USER_MESSAGE_CODE, user.getId(), id);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("删除消息成功.");
        return view;
    }
}
