package com.qcloud.component.personalcenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.parameter.ParamType;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.personalcenter.model.WealthConfig;
import com.qcloud.component.personalcenter.service.WealthConfigService;

@Service
public class WealthConfigServiceImpl implements WealthConfigService {

    @Autowired
    private ParameterClient     parameterClientImpl;

    private final static String point_Config    = "point-pointConfig";

    private final static String currency_Config = "currency-currencyConfig";

    @Override
    public WealthConfig get() {

        Integer integral = parameterClientImpl.get(point_Config);
        Integer currency = parameterClientImpl.get(currency_Config);
        WealthConfig wealthConfig = new WealthConfig();
        wealthConfig.setIntegral(integral == null ? 0 : integral);
        wealthConfig.setCurrency(currency == null ? 0 : currency);
        return wealthConfig;
    }

    @Override
    public boolean set(WealthConfig config) {

        parameterClientImpl.reg(point_Config, String.valueOf(config.getIntegral()), ParamType.INTEGER);
        parameterClientImpl.reg(currency_Config, String.valueOf(config.getCurrency()), ParamType.INTEGER);
        return false;
    }
}
