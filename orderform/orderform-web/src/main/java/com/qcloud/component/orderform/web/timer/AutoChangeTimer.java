package com.qcloud.component.orderform.web.timer;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.engine.AutoChangeService;
import com.qcloud.component.piratesship.service.ModuleConfigService;
import com.qcloud.pirates.core.env.ProjectInfo;
import com.qcloud.pirates.core.timer.AbstractTimer;
import com.qcloud.pirates.core.timer.PerMinutePeriod;
import com.qcloud.pirates.core.timer.Period;
import com.qcloud.pirates.core.timer.SecondPeriod;
import com.qcloud.pirates.util.DateUtil;

@Component
public class AutoChangeTimer extends AbstractTimer {

    private Log                 logger = LogFactory.getLog(getClass());

    @Autowired
    private ModuleConfigService moduleConfigService;

    @Autowired
    private AutoChangeService   autoChangeService;

    @Override
    public boolean isEnabled() {

        return true;
    }

    @Override
    public Period getPeriod() {

        return ProjectInfo.isDev() ? new SecondPeriod(1200) : new PerMinutePeriod(20);
    }

    @Override
    public void start() {

        // if (moduleConfigService.enable("orderform")) {
        logger.info("使订单组件状态自动跳转定时器 start" + DateUtil.date2String(new Date()));
        autoChangeService.autoChange();
        logger.info("使订单组件状态自动跳转定时器 end" + DateUtil.date2String(new Date()));
        // }
    }
}
