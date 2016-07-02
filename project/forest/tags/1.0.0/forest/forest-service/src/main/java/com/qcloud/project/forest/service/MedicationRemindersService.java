package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.MedicationReminders;
import com.qcloud.project.forest.model.query.MedicationRemindersQuery;

public interface MedicationRemindersService {

    public boolean add(MedicationReminders medicationReminders);

    public MedicationReminders get(Long id);

    public boolean delete(Long id);

    public boolean update(MedicationReminders medicationReminders);

    public List<MedicationReminders> listByThemeId(Long themeId);

    public Page<MedicationReminders> page(MedicationRemindersQuery query, int start, int count);

    public List<MedicationReminders> listAll();
}
