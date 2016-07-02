package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.MedicationRemindersDao;
import com.qcloud.project.forest.model.MedicationReminders;
import com.qcloud.project.forest.model.query.MedicationRemindersQuery;

@Repository
public class MedicationRemindersDaoRedisImpl implements MedicationRemindersDao {

    // @Resource(name = "redis-forest")
    // private Redis redis;
    @Override
    public boolean add(MedicationReminders medicationReminders) {

        throw new NotImplementedException();
    }

    @Override
    public MedicationReminders get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MedicationReminders medicationReminders) {

        throw new NotImplementedException();
    }

    @Override
    public List<MedicationReminders> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MedicationReminders> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<MedicationReminders> listByNormal(Long themeId) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MedicationReminders> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MedicationReminders> page(MedicationRemindersQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MedicationReminders> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<MedicationReminders> listByThemeId(Long themeId) {

        throw new NotImplementedException();
    }
}
