package com.qcloud.component.marketing.service;

import com.qcloud.component.marketing.model.RegistrationGiftConfig;

public interface RegistrationGiftConfigService {
    
    RegistrationGiftConfig get();
    
    void setPoint(RegistrationGiftConfig registrationGiftConfig);

    void setCurrency(RegistrationGiftConfig registrationGiftConfig);

    void setBrokerage(RegistrationGiftConfig registrationGiftConfig);
}
