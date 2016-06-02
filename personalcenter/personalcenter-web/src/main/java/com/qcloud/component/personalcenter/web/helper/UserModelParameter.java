package com.qcloud.component.personalcenter.web.helper;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.pirates.web.page.PageParameter;

@Component
public class UserModelParameter implements PageParameter {

    @Autowired
    private UserHelper userHelper;

    @Override
    public String getKey() {

        return PersonalcenterClient.USER_LOGIN_PARAMETER_KEY;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getValue(HttpServletRequest request) {

        return (T) userHelper.getUserModel(request);
    }
}
