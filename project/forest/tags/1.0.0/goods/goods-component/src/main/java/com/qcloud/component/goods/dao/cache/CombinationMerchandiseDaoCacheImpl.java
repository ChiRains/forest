package com.qcloud.component.goods.dao.cache;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.CombinationMerchandiseDao;
import com.qcloud.component.goods.model.CombinationMerchandise;
import com.qcloud.component.goods.model.query.CombinationMerchandiseQuery;

@Repository
public class CombinationMerchandiseDaoCacheImpl implements CombinationMerchandiseDao {

    @Autowired
    private CombinationMerchandiseDao combinationMerchandiseDaoMysqlImpl;

    // @Autowired
    // private CombinationMerchandiseDao combinationMerchandiseDaoRedisImpl;
    @Override
    public boolean add(CombinationMerchandise combinationMerchandise) {

        return combinationMerchandiseDaoMysqlImpl.add(combinationMerchandise);
    }

    @Override
    public CombinationMerchandise get(Long id) {

        return combinationMerchandiseDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return combinationMerchandiseDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(CombinationMerchandise combinationMerchandise) {

        return combinationMerchandiseDaoMysqlImpl.update(combinationMerchandise);
    }

    @Override
    public List<CombinationMerchandise> list(List<Long> idList) {

        return CacheLoader.list(combinationMerchandiseDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, CombinationMerchandise> map(Set<Long> idSet) {

        return CacheLoader.map(combinationMerchandiseDaoMysqlImpl, idSet);
    }

    @Override
    public Page<CombinationMerchandise> page(int start, int count) {

        return combinationMerchandiseDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<CombinationMerchandise> page(CombinationMerchandiseQuery query, int start, int count) {

        return combinationMerchandiseDaoMysqlImpl.page(query, start, count);
    }

    public List<CombinationMerchandise> listAll() {

        return combinationMerchandiseDaoMysqlImpl.listAll();
    }

    @Override
    public CombinationMerchandise getByUnifiedMerchandise(Long unifiedMerchandiseId) {

        return combinationMerchandiseDaoMysqlImpl.getByUnifiedMerchandise(unifiedMerchandiseId);
    }

    @Override
    public List<CombinationMerchandise> list(Map where) {

        return combinationMerchandiseDaoMysqlImpl.list(where);
    }

    @Override
    public CombinationMerchandise get(Map where) {

        return combinationMerchandiseDaoMysqlImpl.get(where);
    }

    @Override
    public Page<CombinationMerchandise> page(Map where, int start, int size) {

        return combinationMerchandiseDaoMysqlImpl.page(where, start, size);
    }

    @Override
    public boolean update(CombinationMerchandise combinationMerchandise, Date lastUpdateTime) {

        return combinationMerchandiseDaoMysqlImpl.update(combinationMerchandise, lastUpdateTime);
    }
}
