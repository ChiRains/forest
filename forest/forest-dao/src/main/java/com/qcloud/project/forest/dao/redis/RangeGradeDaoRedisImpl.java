package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.RangeGradeDao;
import com.qcloud.project.forest.model.RangeGrade;
import com.qcloud.project.forest.model.query.RangeGradeQuery;

@Repository
public class RangeGradeDaoRedisImpl implements RangeGradeDao {

    // @Resource(name = "redis-forest")
    // private Redis redis;
    @Override
    public boolean add(RangeGrade rangeGrade) {

        throw new NotImplementedException();
    }

    @Override
    public RangeGrade get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(RangeGrade rangeGrade) {

        throw new NotImplementedException();
    }

    @Override
    public List<RangeGrade> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, RangeGrade> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<RangeGrade> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<RangeGrade> page(RangeGradeQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<RangeGrade> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<RangeGrade> listByRange(long rangeId) {

        throw new NotImplementedException();
    }
}
