package com.qcloud.project.forest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.MedicationRemindersDao;
import com.qcloud.project.forest.model.MedicationReminders;
import com.qcloud.project.forest.model.query.MedicationRemindersQuery;
import com.qcloud.project.forest.service.MedicationRemindersService;

@Service
public class MedicationRemindersServiceImpl implements MedicationRemindersService {

    @Autowired
    private MedicationRemindersDao medicationRemindersDao;

    @Autowired
    private AutoIdGenerator        autoIdGenerator;

    private static final String    ID_KEY = "forest_medication_reminders";

    @Override
    public boolean add(MedicationReminders medicationReminders) {

        long id = autoIdGenerator.get(ID_KEY);
        medicationReminders.setId(id);
        return medicationRemindersDao.add(medicationReminders);
    }

    @Override
    public MedicationReminders get(Long id) {

        return medicationRemindersDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return medicationRemindersDao.delete(id);
    }

    @Override
    public boolean update(MedicationReminders medicationReminders) {

        return medicationRemindersDao.update(medicationReminders);
    }

    @Override
    public Page<MedicationReminders> page(MedicationRemindersQuery query, int start, int count) {

        return medicationRemindersDao.page(query, start, count);
    }

    public List<MedicationReminders> listAll() {

        return medicationRemindersDao.listAll();
    }

    @Override
    public List<MedicationReminders> listByThemeId(Long themeId) {

        return medicationRemindersDao.listByThemeId(themeId);
    }
}
