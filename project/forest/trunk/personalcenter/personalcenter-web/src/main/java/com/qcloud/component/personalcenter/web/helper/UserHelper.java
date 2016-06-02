package com.qcloud.component.personalcenter.web.helper;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.service.UserService;
import com.qcloud.component.token.TokenClient;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;

@Component
public class UserHelper {

    @Autowired
    private TokenClient          tokenClient;

    @Autowired
    private UserService          userService;

    @Autowired
    private PersonalcenterClient personalcenterClient;

    public User getUser(HttpServletRequest request) {

        String token = PageParameterUtil.getParameterValues(request, PiratesParameterKey.LOGIN_TOKEN);
        AssertUtil.assertNotEmpty(token, "获取用户登录信息失败.");
        String idStr = tokenClient.get(token);
        AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
        User user = userService.get(Long.parseLong(idStr));
        AssertUtil.assertNotNull(user, "用户不存在.");
        return user;
    }

    QUser getUserModel(HttpServletRequest request) {

        String token = PageParameterUtil.getParameterValues(request, PiratesParameterKey.LOGIN_TOKEN);
        AssertUtil.assertNotEmpty(token, "获取用户登录信息失败.");
        String idStr = tokenClient.get(token);
        AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
        QUser user = personalcenterClient.getUser(Long.parseLong(idStr));
        AssertUtil.assertNotNull(user, "用户不存在.");
        return user;
    }

    QUser isLogin(HttpServletRequest request) {

        String token = PageParameterUtil.getParameterValues(request, PiratesParameterKey.LOGIN_TOKEN);
        if (token != null) {
            String idStr = tokenClient.get(token);
            if (null != idStr && !"".equals(idStr)) {
                QUser user = personalcenterClient.getUser(Long.parseLong(idStr));
                return user;
            }
        }
        return null;
    }
}
