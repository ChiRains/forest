package com.qcloud.component.personalcenter.service;

import com.qcloud.component.personalcenter.model.MySignInRuleConfig;

public interface MySignInRuleConfigService {

    MySignInRuleConfig get();

    boolean set(MySignInRuleConfig config);
}
