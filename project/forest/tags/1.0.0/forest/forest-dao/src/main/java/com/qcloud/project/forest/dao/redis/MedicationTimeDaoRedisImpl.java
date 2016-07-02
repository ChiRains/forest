package com.qcloud.project.forest.dao.redis;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.project.forest.dao.MedicationTimeDao;
import com.qcloud.project.forest.model.MedicationTime;
import com.qcloud.project.forest.model.query.MedicationTimeQuery;

@Repository
public class MedicationTimeDaoRedisImpl implements MedicationTimeDao {

    // @Resource(name = "redis-forest")
    // private Redis redis;
    @Override
    public boolean add(MedicationTime medicationTime) {

        throw new NotImplementedException();
    }

    @Override
    public MedicationTime get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MedicationTime medicationTime) {

        throw new NotImplementedException();
    }

    @Override
    public List<MedicationTime> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MedicationTime> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<MedicationTime> listByUserId(Long userId) {

        throw new NotImplementedException();
    }

    public List<MedicationTime> listByExcuteTime(Date excuteTime) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MedicationTime> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MedicationTime> page(MedicationTimeQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MedicationTime> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<MedicationTime> listByMedicationId(Long medicationId) {

        throw new NotImplementedException();
    }
}
