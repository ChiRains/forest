package com.qcloud.project.forest.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.MedicationRemindersDao;
import com.qcloud.project.forest.model.MedicationReminders;
import com.qcloud.project.forest.model.query.MedicationRemindersQuery;

@Repository
public class MedicationRemindersDaoCacheImpl implements MedicationRemindersDao {

    @Autowired
    private MedicationRemindersDao medicationRemindersDaoMysqlImpl;

    @Autowired
    private MedicationRemindersDao medicationRemindersDaoRedisImpl;

    @Override
    public boolean add(MedicationReminders medicationReminders) {

        return medicationRemindersDaoMysqlImpl.add(medicationReminders);
    }

    @Override
    public MedicationReminders get(Long id) {

        return medicationRemindersDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return medicationRemindersDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MedicationReminders medicationReminders) {

        return medicationRemindersDaoMysqlImpl.update(medicationReminders);
    }

    @Override
    public List<MedicationReminders> list(List<Long> idList) {

        return CacheLoader.list(medicationRemindersDaoRedisImpl, medicationRemindersDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MedicationReminders> map(Set<Long> idSet) {

        return CacheLoader.map(medicationRemindersDaoRedisImpl, medicationRemindersDaoMysqlImpl, idSet);
    }

    public List<MedicationReminders> listByThemeId(Long themeId) {

        return medicationRemindersDaoMysqlImpl.listByThemeId(themeId);
    }

    @Override
    public Page<MedicationReminders> page(int start, int count) {

        return medicationRemindersDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MedicationReminders> page(MedicationRemindersQuery query, int start, int count) {

        return medicationRemindersDaoMysqlImpl.page(query, start, count);
    }

    public List<MedicationReminders> listAll() {

        return medicationRemindersDaoMysqlImpl.listAll();
    }
}
