package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.MedicationRemindersTime;
import com.qcloud.project.forest.model.query.MedicationRemindersTimeQuery;

public interface MedicationRemindersTimeDao extends ISimpleDao<MedicationRemindersTime, Long> {

    public boolean add(MedicationRemindersTime medicationRemindersTime);

    public MedicationRemindersTime get(Long id);

    public boolean delete(Long id);

    public boolean update(MedicationRemindersTime medicationRemindersTime);

    public List<MedicationRemindersTime> list(List<Long> idList);

    public Map<Long, MedicationRemindersTime> map(Set<Long> idSet);

    public Page<MedicationRemindersTime> page(MedicationRemindersTimeQuery query, int start, int size);

    public List<MedicationRemindersTime> listAll();

    public List<MedicationRemindersTime> listByExcuteTime(String excuteTime);

    public Boolean deleteByReminderId(Long medicationRemindersId);
}
