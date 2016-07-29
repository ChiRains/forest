package com.qcloud.component.marketing.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.marketing.dao.FullReducesDao;
import com.qcloud.component.marketing.model.FullReduces;
import com.qcloud.component.marketing.model.query.FullReducesQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class FullReducesDaoCacheImpl implements FullReducesDao {

    @Autowired
    private FullReducesDao fullReducesDaoMysqlImpl;

    @Autowired
    private FullReducesDao fullReducesDaoRedisImpl;

    @Override
    public boolean add(FullReduces fullReduces) {

        return fullReducesDaoMysqlImpl.add(fullReduces);
    }

    @Override
    public FullReduces get(Long id) {

        return fullReducesDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return fullReducesDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(FullReduces fullReduces) {

        return fullReducesDaoMysqlImpl.update(fullReduces);
    }

    @Override
    public List<FullReduces> list(List<Long> idList) {

        return CacheLoader.list(fullReducesDaoRedisImpl, fullReducesDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, FullReduces> map(Set<Long> idSet) {

        return CacheLoader.map(fullReducesDaoRedisImpl, fullReducesDaoMysqlImpl, idSet);
    }

    @Override
    public Page<FullReduces> page(int start, int count) {

        return fullReducesDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<FullReduces> page(FullReducesQuery query, int start, int count) {

        return fullReducesDaoMysqlImpl.page(query, start, count);
    }

    public List<FullReduces> listAll() {

        return fullReducesDaoMysqlImpl.listAll();
    }

    @Override
    public List<FullReduces> listCurrentReduces() {

        return fullReducesDaoMysqlImpl.listCurrentReduces();
    }
}
