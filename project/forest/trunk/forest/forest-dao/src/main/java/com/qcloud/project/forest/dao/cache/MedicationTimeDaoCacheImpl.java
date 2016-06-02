package com.qcloud.project.forest.dao.cache;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.MedicationTimeDao;
import com.qcloud.project.forest.model.MedicationTime;
import com.qcloud.project.forest.model.query.MedicationTimeQuery;

@Repository
public class MedicationTimeDaoCacheImpl implements MedicationTimeDao {

    @Autowired
    private MedicationTimeDao medicationTimeDaoMysqlImpl;

    @Autowired
    private MedicationTimeDao medicationTimeDaoRedisImpl;

    @Override
    public boolean add(MedicationTime medicationTime) {

        return medicationTimeDaoMysqlImpl.add(medicationTime);
    }

    @Override
    public MedicationTime get(Long id) {

        return CacheLoader.get(medicationTimeDaoRedisImpl, medicationTimeDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return medicationTimeDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MedicationTime medicationTime) {

        return medicationTimeDaoMysqlImpl.update(medicationTime);
    }

    @Override
    public List<MedicationTime> list(List<Long> idList) {

        return CacheLoader.list(medicationTimeDaoRedisImpl, medicationTimeDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MedicationTime> map(Set<Long> idSet) {

        return CacheLoader.map(medicationTimeDaoRedisImpl, medicationTimeDaoMysqlImpl, idSet);
    }

    public List<MedicationTime> listByUserId(Long userId) {

        return medicationTimeDaoMysqlImpl.listByUserId(userId);
    }

    public List<MedicationTime> listByExcuteTime(Date excuteTime) {

        return medicationTimeDaoMysqlImpl.listByExcuteTime(excuteTime);
    }

    @Override
    public Page<MedicationTime> page(int start, int count) {

        return medicationTimeDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MedicationTime> page(MedicationTimeQuery query, int start, int count) {

        return medicationTimeDaoMysqlImpl.page(query, start, count);
    }

    public List<MedicationTime> listAll() {

        return medicationTimeDaoMysqlImpl.listAll();
    }

    @Override
    public List<MedicationTime> listByMedicationId(Long medicationId) {

        return medicationTimeDaoMysqlImpl.listByMedicationId(medicationId);
    }
}
