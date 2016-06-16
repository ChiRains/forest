package com.qcloud.component.marketing.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.marketing.model.RegistrationGiftConfig;
import com.qcloud.component.marketing.service.RegistrationGiftConfigService;
import com.qcloud.component.marketing.service.RegistrationGiftService;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.UserNotifyModel;
import com.qcloud.component.personalcenter.WealthType;
import com.qcloud.pirates.core.notify.ServiceNotify;

@Component
public class RegistrationGiftNotify implements ServiceNotify {

    @Autowired
    private RegistrationGiftService       registrationGiftService;

    @Autowired
    private RegistrationGiftConfigService registrationGiftConfigService;

    @Autowired
    private PersonalcenterClient          personalcenterClient;

    @Override
    public void doNotify(Object[] arg0) {

        if (arg0[0] instanceof UserNotifyModel) {
            UserNotifyModel user = (UserNotifyModel) arg0[0];
            // 注册赠送
            RegistrationGiftConfig config = registrationGiftConfigService.get();
            if (config.getBrokerage() != 0) {
                personalcenterClient.calculateMyWealth(user.getId(), WealthType.COMSUMPTION_CURRENCY, config.getBrokerage(), false, "注册送礼");
            }
            if (config.getCurrency() != 0) {
                personalcenterClient.calculateMyWealth(user.getId(), WealthType.COMMISSION, config.getCurrency(), false, "注册送礼");
            }
            if (config.getPoint() != 0) {
                personalcenterClient.calculateMyWealth(user.getId(), WealthType.INTEGRAL, config.getPoint(), false, "注册送礼");
            }
            registrationGiftService.sendGift(user.getId());
        }
    }

    @Override
    public boolean isBefore() {

        return false;
    }
}
