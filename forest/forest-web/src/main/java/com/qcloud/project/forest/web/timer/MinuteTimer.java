package com.qcloud.project.forest.web.timer;

import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.timer.AbstractTimer;
import com.qcloud.pirates.core.timer.Period;
import com.qcloud.pirates.core.timer.MinutePeriod;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.project.forest.model.Medication;
import com.qcloud.project.forest.model.MedicationTime;
import com.qcloud.project.forest.model.key.TypeEnum.MessageType;
import com.qcloud.project.forest.model.key.TypeEnum.PeriodType;
import com.qcloud.project.forest.service.MedicationService;
import com.qcloud.project.forest.service.MedicationTimeService;
import com.qcloud.component.publicservice.MessageClient;

@Component
public class MinuteTimer extends AbstractTimer {

    @Autowired
    private MedicationService     medicationService;

    @Autowired
    private MedicationTimeService medicationTimeService;

    @Autowired
    private MessageClient         messageClient;

    Log                           logger = LogFactory.getLog(getClass());

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

        Date currTime = new Date();
        List<MedicationTime> list = medicationTimeService.listByExcuteTime(currTime);
        for (MedicationTime medicationTime : list) {
            Medication medication = medicationService.get(medicationTime.getMedicationId());
            // 发送消息
            messageClient.sendMsg(MessageType.MEDICATION.getKey(), -1, medication.getUserId(), medication.getTheme() + "--" + medication.getMedicine(), medication.getDesc());
            logger.info(medication.getUserId() + "--消息发送成功." + medication.getTheme());
            PeriodType periodType = null;
            // 更新执行时间
            for (PeriodType pt : PeriodType.values()) {
                if (pt.getKey() == medicationTime.getPeriodType()) {
                    periodType = pt;
                }
            }
            AssertUtil.assertNotNull(periodType, "时间类型错误!");
            Date excuteTime = medicationService.getExcuteTime(periodType, medicationTime.getTakeTime());
            medicationTime.setExcuteTime(excuteTime);
            medicationTimeService.update(medicationTime);
            logger.info(medication.getUserId() + "--更新下一个时间." + DateUtil.date2String(excuteTime, DateUtil.FORMAT_STRING));
        }
        logger.info("---------------------用药提醒,当前系统时间" + DateUtil.date2String(new Date()) + "------------------------");
    }
}
