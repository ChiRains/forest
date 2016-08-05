package com.qcloud.component.brokerage.web.timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.brokerage.engine.BrokerageEngineService;
import com.qcloud.pirates.core.env.ProjectInfo;
import com.qcloud.pirates.core.timer.AbstractTimer;
import com.qcloud.pirates.core.timer.MinutePeriod;
import com.qcloud.pirates.core.timer.Period;
import com.qcloud.pirates.core.timer.SecondPeriod;

@Component
public class AllocationTimer extends AbstractTimer {

    private Log                    logger = LogFactory.getLog(getClass());

    @Autowired
    private BrokerageEngineService brokerageEngineService;

    @Override
    public boolean isEnabled() {

        return true;
    }

    @Override
    public Period getPeriod() {

        return ProjectInfo.isDev() ? new SecondPeriod(1200) : new MinutePeriod(20);
    }

    @Override
    public void start() {

        logger.info("开始:分配佣金.");
        // brokerageEngineService.calculate();
        logger.info("结束:分配佣金.");
    }
}
