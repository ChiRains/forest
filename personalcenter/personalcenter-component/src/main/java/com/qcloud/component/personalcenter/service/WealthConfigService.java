package com.qcloud.component.personalcenter.service;

import com.qcloud.component.personalcenter.model.WealthConfig;

public interface WealthConfigService {

    WealthConfig get();

    boolean set(WealthConfig config);
}
