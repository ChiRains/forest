package com.qcloud.component.marketing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.marketing.model.RegistrationGiftConfig;
import com.qcloud.component.marketing.service.RegistrationGiftConfigService;
import com.qcloud.component.parameter.ParamType;
import com.qcloud.component.parameter.ParameterClient;

@Service
public class RegistrationGiftConfigServiceImpl implements RegistrationGiftConfigService {

    @Autowired
    private ParameterClient     parameterClientImpl;

    private final static String point_config     = "personalcenter-registration-gift-point";

    private final static String currency_config  = "personalcenter-registration-gift-currency";

    private final static String brokerage_config = "personalcenter-registration-gift-brokerage";

    @Override
    public RegistrationGiftConfig get() {

        RegistrationGiftConfig config = new RegistrationGiftConfig();
        
        config.setBrokerage((Integer)parameterClientImpl.get(brokerage_config));
        config.setPoint((Integer)parameterClientImpl.get(point_config));
        config.setCurrency((Integer)parameterClientImpl.get(currency_config));
        return config;
    }

    @Override
    public void setPoint(RegistrationGiftConfig registrationGiftConfig) {

        parameterClientImpl.reg(point_config, String.valueOf(registrationGiftConfig.getPoint()), ParamType.INTEGER);
    }
    @Override
    public void setCurrency(RegistrationGiftConfig registrationGiftConfig) {

        parameterClientImpl.reg(currency_config, String.valueOf(registrationGiftConfig.getCurrency()), ParamType.INTEGER);
    }
    @Override
    public void setBrokerage(RegistrationGiftConfig registrationGiftConfig) {

        parameterClientImpl.reg(brokerage_config, String.valueOf(registrationGiftConfig.getBrokerage()), ParamType.INTEGER);
    }

}
