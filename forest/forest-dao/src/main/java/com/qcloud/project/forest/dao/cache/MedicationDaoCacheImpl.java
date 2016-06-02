package com.qcloud.project.forest.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.MedicationDao;
import com.qcloud.project.forest.model.Medication;
import com.qcloud.project.forest.model.query.MedicationQuery;

@Repository
public class MedicationDaoCacheImpl implements MedicationDao {

    @Autowired
    private MedicationDao medicationDaoMysqlImpl;

    @Autowired
    private MedicationDao medicationDaoRedisImpl;

    @Override
    public boolean add(Medication medication) {

        return medicationDaoMysqlImpl.add(medication);
    }

    @Override
    public Medication get(Long id) {

        return medicationDaoMysqlImpl.get(id);
        // return CacheLoader.get(medicationDaoRedisImpl, medicationDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return medicationDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(Medication medication) {

        return medicationDaoMysqlImpl.update(medication);
    }

    @Override
    public List<Medication> list(List<Long> idList) {

        return CacheLoader.list(medicationDaoRedisImpl, medicationDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, Medication> map(Set<Long> idSet) {

        return CacheLoader.map(medicationDaoRedisImpl, medicationDaoMysqlImpl, idSet);
    }

    @Override
    public Page<Medication> page(int start, int count) {

        return medicationDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<Medication> page(MedicationQuery query, int start, int count) {

        return medicationDaoMysqlImpl.page(query, start, count);
    }

    public List<Medication> listAll() {

        return medicationDaoMysqlImpl.listAll();
    }
}
