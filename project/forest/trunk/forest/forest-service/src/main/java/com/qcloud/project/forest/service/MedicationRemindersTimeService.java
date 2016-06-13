package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.MedicationRemindersTime;
import com.qcloud.project.forest.model.query.MedicationRemindersTimeQuery;

public interface MedicationRemindersTimeService {

    public boolean add(MedicationRemindersTime medicationRemindersTime);

    public MedicationRemindersTime get(Long id);

    public boolean delete(Long id);

    public boolean update(MedicationRemindersTime medicationRemindersTime);

    public List<MedicationRemindersTime> listByExcuteTime(String excuteTime);

    public Page<MedicationRemindersTime> page(MedicationRemindersTimeQuery query, int start, int count);

    public List<MedicationRemindersTime> listAll();

    public Boolean deleteByReminderId(Long medicationRemindersId);
}
