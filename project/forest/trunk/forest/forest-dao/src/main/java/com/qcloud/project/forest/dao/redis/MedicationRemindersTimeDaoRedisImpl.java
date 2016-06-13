package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.MedicationRemindersTimeDao;
import com.qcloud.project.forest.model.MedicationRemindersTime;
import com.qcloud.project.forest.model.query.MedicationRemindersTimeQuery;

@Repository
public class MedicationRemindersTimeDaoRedisImpl implements MedicationRemindersTimeDao {

    // @Resource(name = "redis-forest")
    // private Redis redis;
    @Override
    public boolean add(MedicationRemindersTime medicationRemindersTime) {

        throw new NotImplementedException();
    }

    @Override
    public MedicationRemindersTime get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MedicationRemindersTime medicationRemindersTime) {

        throw new NotImplementedException();
    }

    @Override
    public List<MedicationRemindersTime> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MedicationRemindersTime> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<MedicationRemindersTime> listByExcuteTime(String excuteTime) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MedicationRemindersTime> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MedicationRemindersTime> page(MedicationRemindersTimeQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MedicationRemindersTime> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public Boolean deleteByReminderId(Long medicationRemindersId) {

        throw new NotImplementedException();
    }
}
