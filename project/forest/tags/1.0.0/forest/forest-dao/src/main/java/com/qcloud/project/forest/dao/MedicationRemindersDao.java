package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.MedicationReminders;
import com.qcloud.project.forest.model.query.MedicationRemindersQuery;

public interface MedicationRemindersDao extends ISimpleDao<MedicationReminders, Long> {

    public boolean add(MedicationReminders medicationReminders);

    public MedicationReminders get(Long id);

    public boolean delete(Long id);

    public boolean update(MedicationReminders medicationReminders);

    public List<MedicationReminders> list(List<Long> idList);

    public Map<Long, MedicationReminders> map(Set<Long> idSet);

    public Page<MedicationReminders> page(MedicationRemindersQuery query, int start, int size);

    public List<MedicationReminders> listAll();

    public List<MedicationReminders> listByThemeId(Long themeId);
}
