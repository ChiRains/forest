package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.BpCalculationDao;
import com.qcloud.project.forest.model.BpCalculation;
import com.qcloud.project.forest.model.query.BpCalculationQuery;

@Repository
public class BpCalculationDaoRedisImpl implements BpCalculationDao {

    // @Resource(name = "redis-forest")
    // private Redis redis;
    @Override
    public boolean add(BpCalculation bpCalculation) {

        throw new NotImplementedException();
    }

    @Override
    public BpCalculation get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(BpCalculation bpCalculation) {

        throw new NotImplementedException();
    }

    @Override
    public List<BpCalculation> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, BpCalculation> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<BpCalculation> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<BpCalculation> page(BpCalculationQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<BpCalculation> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public BpCalculation getByRange(int dbp, int sbp) {

        throw new NotImplementedException();
    }
}
