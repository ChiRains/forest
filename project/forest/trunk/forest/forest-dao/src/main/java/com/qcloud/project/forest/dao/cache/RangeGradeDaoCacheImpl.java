package com.qcloud.project.forest.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.RangeGradeDao;
import com.qcloud.project.forest.model.RangeGrade;
import com.qcloud.project.forest.model.query.RangeGradeQuery;

@Repository
public class RangeGradeDaoCacheImpl implements RangeGradeDao {

    @Autowired
    private RangeGradeDao rangeGradeDaoMysqlImpl;

    @Autowired
    private RangeGradeDao rangeGradeDaoRedisImpl;

    @Override
    public boolean add(RangeGrade rangeGrade) {

        return rangeGradeDaoMysqlImpl.add(rangeGrade);
    }

    @Override
    public RangeGrade get(Long id) {

        return rangeGradeDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return rangeGradeDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(RangeGrade rangeGrade) {

        return rangeGradeDaoMysqlImpl.update(rangeGrade);
    }

    @Override
    public List<RangeGrade> list(List<Long> idList) {

        return CacheLoader.list(rangeGradeDaoRedisImpl, rangeGradeDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, RangeGrade> map(Set<Long> idSet) {

        return CacheLoader.map(rangeGradeDaoRedisImpl, rangeGradeDaoMysqlImpl, idSet);
    }

    @Override
    public Page<RangeGrade> page(int start, int count) {

        return rangeGradeDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<RangeGrade> page(RangeGradeQuery query, int start, int count) {

        return rangeGradeDaoMysqlImpl.page(query, start, count);
    }

    public List<RangeGrade> listAll() {

        return rangeGradeDaoMysqlImpl.listAll();
    }

    @Override
    public List<RangeGrade> listByRange(long rangeId) {

        return rangeGradeDaoMysqlImpl.listByRange(rangeId);
    }
}
