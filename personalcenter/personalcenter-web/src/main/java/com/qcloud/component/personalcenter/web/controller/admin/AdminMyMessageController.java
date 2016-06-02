package com.qcloud.component.personalcenter.web.controller.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.UserMessageType;
import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.service.UserService;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.util.AssertUtil;

@Controller
@RequestMapping(value = "/" + AdminMyMessageController.DIR)
public class AdminMyMessageController {

    public static final String DIR = "admin/myMessage";

    @Autowired
    PersonalcenterClient       personalcenterClient;

    @Autowired
    private UserService        userService;

    @RequestMapping
    public ModelAndView toSend(Long userId, String name) {

        ModelAndView model = new ModelAndView("/admin/personalcenter-User-sendMessage");
        model.addObject("userId", userId);
        model.addObject("name", name);
        return model;
    }

    @RequestMapping
    public ModelAndView toSelect(String userId_send) {

        List<User> list = userService.listAll();
        Iterator<User> it = list.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if (StringUtils.isEmpty(user.getMobile())) {
                it.remove();
            }
        }
        ModelAndView model = new ModelAndView("/admin/personalcenter-User-selectMember");
        model.addObject("userList", list);
        if (!userId_send.equals("0") && userId_send != "") {
            String[] userId = userId_send.split(",");
            List<User> checkedList = new ArrayList<User>();
            for (int i = 0; i < userId.length; i++) {
                Long id = Long.parseLong(userId[i]);
                User user = userService.get(id);
                AssertUtil.assertNotNull(user, "消息接收人不存在." + id);
                checkedList.add(user);
            }
            model.addObject("result", checkedList);
        }
        return model;
    }

    @RequestMapping
    public AceAjaxView send(String userId_send, String title, String content) {

        String[] userId = userId_send.split(",");
        for (int i = 0; i < userId.length; i++) {
            Long id = Long.parseLong(userId[i]);
            User user = userService.get(id);
            AssertUtil.assertNotNull(user, "消息接收人不存在." + id);
            personalcenterClient.sendMsg(id, UserMessageType.INSIDE_LETTER, title, content);
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("发送消息成功");
        return aceAjaxView;
    }
}
