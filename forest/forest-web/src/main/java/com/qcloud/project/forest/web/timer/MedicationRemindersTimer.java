package com.qcloud.project.forest.web.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.publicservice.MessageClient;
import com.qcloud.pirates.core.timer.AbstractTimer;
import com.qcloud.pirates.core.timer.MinutePeriod;
import com.qcloud.pirates.core.timer.Period;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.project.forest.model.MedicationReminders;
import com.qcloud.project.forest.model.MedicationRemindersTheme;
import com.qcloud.project.forest.model.MedicationRemindersTime;
import com.qcloud.project.forest.model.key.TypeEnum.MessageClassify;
import com.qcloud.project.forest.model.key.TypeEnum.MessageType;
import com.qcloud.project.forest.service.MedicationRemindersService;
import com.qcloud.project.forest.service.MedicationRemindersThemeService;
import com.qcloud.project.forest.service.MedicationRemindersTimeService;

@Component
public class MedicationRemindersTimer extends AbstractTimer {

    @Autowired
    private MessageClient                   messageClient;

    @Autowired
    private MedicationRemindersTimeService  medicationRemindersTimeService;

    @Autowired
    private MedicationRemindersThemeService medicationRemindersThemeService;

    @Autowired
    private MedicationRemindersService      medicationRemindersService;

    private Log                             logger = LogFactory.getLog(getClass());

    @Override
    public boolean isEnabled() {

        return true;
    }

    @Override
    public Period getPeriod() {

        MinutePeriod s = new MinutePeriod(1);
        return s;
    }

    @Override
    public void start() {

        List<MedicationRemindersTime> list = medicationRemindersTimeService.listByExcuteTime(DateUtil.date2String(new Date(), "HH:mm") + " " + getDay(new Date()));
        MedicationReminders medicationReminders = null;
        MedicationRemindersTheme medicationRemindersTheme = null;
        for (MedicationRemindersTime medicationRemindersTime : list) {
            medicationReminders = medicationRemindersService.get(medicationRemindersTime.getReminderId());
            medicationRemindersTheme = medicationRemindersThemeService.get(medicationReminders.getThemeId());
            if (medicationRemindersTheme.getEnable() == 1) {
                // 发送消息
                messageClient.sendMsg(MessageType.USER.getKey(), MessageClassify.MEDICATIONREMINDERS.getKey(), medicationReminders.getUserId(), medicationReminders.getMedicineName(), null);
                logger.info(medicationReminders.getUserId() + "--消息发送成功." + medicationReminders.getMedicineName());
            }
        }
        logger.info("---------------------用药提醒,当前系统时间" + DateUtil.date2String(new Date(), "HH:ss:mm ") + "------------------------");
    }

    private String getDay(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
        case 1:
            return "星期日";
        case 2:
            return "星期一";
        case 3:
            return "星期二";
        case 4:
            return "星期三";
        case 5:
            return "星期四";
        case 6:
            return "星期五";
        case 7:
            return "星期六";
        default:
            return null;
        }
    }
}
