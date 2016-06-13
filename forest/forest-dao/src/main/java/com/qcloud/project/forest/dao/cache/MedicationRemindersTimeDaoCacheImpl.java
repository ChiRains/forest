package com.qcloud.project.forest.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.MedicationRemindersTimeDao;
import com.qcloud.project.forest.model.MedicationRemindersTime;
import com.qcloud.project.forest.model.query.MedicationRemindersTimeQuery;

@Repository
public class MedicationRemindersTimeDaoCacheImpl implements MedicationRemindersTimeDao {

    @Autowired
    private MedicationRemindersTimeDao medicationRemindersTimeDaoMysqlImpl;

    @Autowired
    private MedicationRemindersTimeDao medicationRemindersTimeDaoRedisImpl;

    @Override
    public boolean add(MedicationRemindersTime medicationRemindersTime) {

        return medicationRemindersTimeDaoMysqlImpl.add(medicationRemindersTime);
    }

    @Override
    public MedicationRemindersTime get(Long id) {

        return CacheLoader.get(medicationRemindersTimeDaoRedisImpl, medicationRemindersTimeDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return medicationRemindersTimeDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MedicationRemindersTime medicationRemindersTime) {

        return medicationRemindersTimeDaoMysqlImpl.update(medicationRemindersTime);
    }

    @Override
    public List<MedicationRemindersTime> list(List<Long> idList) {

        return CacheLoader.list(medicationRemindersTimeDaoRedisImpl, medicationRemindersTimeDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MedicationRemindersTime> map(Set<Long> idSet) {

        return CacheLoader.map(medicationRemindersTimeDaoRedisImpl, medicationRemindersTimeDaoMysqlImpl, idSet);
    }

    public List<MedicationRemindersTime> listByExcuteTime(String excuteTime) {

        return medicationRemindersTimeDaoMysqlImpl.listByExcuteTime(excuteTime);
    }

    @Override
    public Page<MedicationRemindersTime> page(int start, int count) {

        return medicationRemindersTimeDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MedicationRemindersTime> page(MedicationRemindersTimeQuery query, int start, int count) {

        return medicationRemindersTimeDaoMysqlImpl.page(query, start, count);
    }

    public List<MedicationRemindersTime> listAll() {

        return medicationRemindersTimeDaoMysqlImpl.listAll();
    }

    @Override
    public Boolean deleteByReminderId(Long medicationRemindersId) {

        return medicationRemindersTimeDaoMysqlImpl.deleteByReminderId(medicationRemindersId);
    }
}
