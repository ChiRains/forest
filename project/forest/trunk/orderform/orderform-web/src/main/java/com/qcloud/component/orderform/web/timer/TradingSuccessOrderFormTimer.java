//package com.qcloud.component.orderform.web.timer;
//
//import java.util.Date;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.qcloud.component.orderform.engine.OrderStateService;
//import com.qcloud.component.piratesship.service.ModuleConfigService;
//import com.qcloud.pirates.core.env.ProjectInfo;
//import com.qcloud.pirates.core.timer.AbstractTimer;
//import com.qcloud.pirates.core.timer.PerDayPeriod;
//import com.qcloud.pirates.core.timer.Period;
//import com.qcloud.pirates.core.timer.SecondPeriod;
//import com.qcloud.pirates.util.DateUtil;
//
//@Component
//public class TradingSuccessOrderFormTimer extends AbstractTimer {
//
//    @Autowired
//    private OrderStateService   orderStateService;
//
//    @Autowired
//    private ModuleConfigService moduleConfigService;
//
//    @Override
//    public boolean isEnabled() {
//
//        return true;
//    }
//
//    @Override
//    public Period getPeriod() {
//
//        return ProjectInfo.isDev() ? new SecondPeriod(10) : new PerDayPeriod(1, 1);
//    }
//
//    @Override
//    public void start() {
//
//        if (moduleConfigService.enable("orderform")) {
//            logger.info("订单交易成功定时器 start" + DateUtil.date2String(new Date()));
//            orderStateService.trade();
//            logger.info("订单交易成功定时器 end" + DateUtil.date2String(new Date()));
//        }
//    }
//}
