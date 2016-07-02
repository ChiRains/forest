package com.qcloud.project.forest.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.project.forest.dao.MedicationDao;
import com.qcloud.project.forest.dao.MedicationTimeDao;
import com.qcloud.project.forest.model.Medication;
import com.qcloud.project.forest.model.MedicationTime;
import com.qcloud.project.forest.service.MedicationService;
import com.qcloud.project.forest.model.key.TypeEnum.PeriodType;
import com.qcloud.project.forest.model.query.MedicationQuery;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationDao       medicationDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    @Autowired
    private MedicationTimeDao   medicationTimeDao;

    private static final String ID_KEY = "forest_medication";

    @Override
    public boolean add(Medication medication) {

        long id = autoIdGenerator.get(ID_KEY);
        medication.setId(id);
        return medicationDao.add(medication);
    }

    @Override
    public Medication get(Long id) {

        return medicationDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return medicationDao.delete(id);
    }

    @Override
    public boolean update(Medication medication) {

        return medicationDao.update(medication);
    }

    @Override
    public Page<Medication> page(MedicationQuery query, int start, int count) {

        return medicationDao.page(query, start, count);
    }

    public List<Medication> listAll() {

        return medicationDao.listAll();
    }

    @Override
    public Date getExcuteTime(PeriodType periodType, String takeTime) {

        Date curTime = new Date();
        String dateStr = DateUtil.date2String(curTime, DateUtil.DATE_FORMAT_STRING);
        // 2015-10-08 + 08:00 + ":00"
        Date excuteTime = DateUtil.str2Date(dateStr + " " + takeTime + ":00", DateUtil.FORMAT_STRING);
        if (periodType == PeriodType.DAY) {
            if (excuteTime.before(curTime)) {
                excuteTime = DateUtils.addDays(excuteTime, 1);
            }
        } else if (periodType == PeriodType.WEEK) {
            if (excuteTime.before(curTime)) {
                excuteTime = DateUtils.addWeeks(excuteTime, 1);
            }
        } else if (periodType == PeriodType.MONTH) {
            if (excuteTime.before(curTime)) {
                excuteTime = DateUtils.addMonths(excuteTime, 1);
            }
        }
        return excuteTime;
    }
}
