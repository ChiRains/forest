package com.qcloud.component.brokerage.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.dao.AllocationSchemeDao;
import com.qcloud.component.brokerage.model.AllocationScheme;
import com.qcloud.component.brokerage.model.query.AllocationSchemeQuery;

@Repository
public class AllocationSchemeDaoCacheImpl implements AllocationSchemeDao {

    @Autowired
    private AllocationSchemeDao allocationSchemeDaoMysqlImpl;

    // @Autowired
    // private AllocationSchemeDao allocationSchemeDaoRedisImpl;
    @Override
    public boolean add(AllocationScheme allocationScheme) {

        return allocationSchemeDaoMysqlImpl.add(allocationScheme);
    }

    @Override
    public AllocationScheme get(Long id) {

        return allocationSchemeDaoMysqlImpl.get(id);
        // return CacheLoader.get(allocationSchemeDaoRedisImpl, allocationSchemeDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return allocationSchemeDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(AllocationScheme allocationScheme) {

        return allocationSchemeDaoMysqlImpl.update(allocationScheme);
    }

    @Override
    public List<AllocationScheme> list(List<Long> idList) {

        return CacheLoader.list(allocationSchemeDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, AllocationScheme> map(Set<Long> idSet) {

        return CacheLoader.map(allocationSchemeDaoMysqlImpl, idSet);
    }

    @Override
    public Page<AllocationScheme> page(int start, int count) {

        return allocationSchemeDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<AllocationScheme> page(AllocationSchemeQuery query, int start, int count) {

        return allocationSchemeDaoMysqlImpl.page(query, start, count);
    }

    public List<AllocationScheme> listAll() {

        return allocationSchemeDaoMysqlImpl.listAll();
    }

    @Override
    public List<AllocationScheme> listByFormula(long formulaId) {
        return allocationSchemeDaoMysqlImpl.listByFormula(formulaId);
    }
}
