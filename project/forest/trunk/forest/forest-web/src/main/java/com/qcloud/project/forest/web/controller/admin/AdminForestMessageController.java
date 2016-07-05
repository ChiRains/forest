package com.qcloud.project.forest.web.controller.admin;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicservice.MessageClient;
import com.qcloud.component.publicservice.model.MessageSource;
import com.qcloud.component.publicservice.service.MessageSourceService;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.project.forest.model.key.TypeEnum.MessageClassify;
import com.qcloud.project.forest.model.key.TypeEnum.MessageType;

@Controller
@RequestMapping(value = AdminForestMessageController.DIR)
public class AdminForestMessageController {

    public final static String   DIR = "admin/forestMessage";

    @Autowired
    private MessageClient        messageClient;

    @Autowired
    private MessageSourceService messageSourceService;

    @Autowired
    private PersonalcenterClient personalcenterClientl;

    @RequestMapping
    public ModelAndView modularList() {

        ModelAndView view = new ModelAndView("/admin/forest-Message-modularlist");
        return view;
    }

    /**
     * 发送站内消息
     * @param content
     * @param title
     * @return
     */
    @RequestMapping
    @Transactional
    public AceAjaxView sendPrivateMessage(HttpServletRequest request, String userId_send, String content, String title) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotEmpty(content, "请填写好发送内容");
        AssertUtil.assertNotEmpty(title, "请填写好标题");
        if (!userId_send.equals("-1")) {
            String[] ary = userId_send.split(",");
            for (String item : ary) {
                messageClient.sendMsg(MessageType.MERCHANT.getKey(), MessageClassify.MERCHANTPRIVATEMESSAGE.getKey(), Long.valueOf(item), title, content);
            }
        } else if (userId_send.equals("-1")) {
            List<QUser> users = personalcenterClientl.userListAll();
            for (QUser qUser : users) {
                messageClient.sendMsg(MessageType.MERCHANT.getKey(), MessageClassify.MERCHANTPRIVATEMESSAGE.getKey(), qUser.getId(), title, content);
            }
        }
        MessageSource messageSource = new MessageSource();
        messageSource.setContent(content);
        messageSource.setTitle(title);
        messageSource.setTime(new Date());
        messageSource.setClassifyId(1);
        messageSource.setMerchantId(merchant.getId());
        messageSource.setType(MessageClassify.MERCHANTPRIVATEMESSAGE.getKey());
        messageSourceService.add(messageSource);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("发送成功");
        aceAjaxView.setUrl("admin/messageSource/list");
        return aceAjaxView;
    }
}
