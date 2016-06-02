package com.qcloud.component.personalcenter.service;

import com.qcloud.component.personalcenter.model.RegistrationGiftConfig;

public interface RegistrationGiftConfigService {
    
    RegistrationGiftConfig get();
    
    void setPoint(RegistrationGiftConfig registrationGiftConfig);

    void setCurrency(RegistrationGiftConfig registrationGiftConfig);

    void setBrokerage(RegistrationGiftConfig registrationGiftConfig);
}
