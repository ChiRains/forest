package com.qcloud.project.forest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.MedicationRemindersTimeDao;
import com.qcloud.project.forest.model.MedicationRemindersTime;
import com.qcloud.project.forest.model.query.MedicationRemindersTimeQuery;
import com.qcloud.project.forest.service.MedicationRemindersTimeService;

@Service
public class MedicationRemindersTimeServiceImpl implements MedicationRemindersTimeService {

    @Autowired
    private MedicationRemindersTimeDao medicationRemindersTimeDao;

    @Autowired
    private AutoIdGenerator            autoIdGenerator;

    private static final String        ID_KEY = "forest_medication_reminders_time";

    @Override
    public boolean add(MedicationRemindersTime medicationRemindersTime) {

        long id = autoIdGenerator.get(ID_KEY);
        medicationRemindersTime.setId(id);
        return medicationRemindersTimeDao.add(medicationRemindersTime);
    }

    @Override
    public MedicationRemindersTime get(Long id) {

        return medicationRemindersTimeDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return medicationRemindersTimeDao.delete(id);
    }

    @Override
    public boolean update(MedicationRemindersTime medicationRemindersTime) {

        return medicationRemindersTimeDao.update(medicationRemindersTime);
    }

    public List<MedicationRemindersTime> listByExcuteTime(String excuteTime) {

        return medicationRemindersTimeDao.listByExcuteTime(excuteTime);
    }

    @Override
    public Page<MedicationRemindersTime> page(MedicationRemindersTimeQuery query, int start, int count) {

        return medicationRemindersTimeDao.page(query, start, count);
    }

    public List<MedicationRemindersTime> listAll() {

        return medicationRemindersTimeDao.listAll();
    }

    @Override
    public Boolean deleteByReminderId(Long medicationRemindersId) {

        return medicationRemindersTimeDao.deleteByReminderId(medicationRemindersId);
    }
}
