package com.qcloud.project.forest.web.timer;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.timer.AbstractTimer;
import com.qcloud.pirates.core.timer.Period;
import com.qcloud.pirates.core.timer.MinutePeriod;
import com.qcloud.pirates.core.timer.SecondPeriod;
import com.qcloud.pirates.util.DateUtil;

@Component
public class DemoTimer extends AbstractTimer {

	Log logger = LogFactory.getLog(getClass());

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Period getPeriod() {
	    SecondPeriod s = new SecondPeriod(2);
		return s;
	}

	@Override
	public void start() {
		logger.info("定时器例子,当前系统时间" + DateUtil.date2String(new Date()));	
	}
}
