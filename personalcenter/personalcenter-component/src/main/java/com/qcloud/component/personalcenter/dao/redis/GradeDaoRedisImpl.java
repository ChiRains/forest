package com.qcloud.component.personalcenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.personalcenter.dao.GradeDao;
import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.model.query.GradeQuery;

@Repository
public class GradeDaoRedisImpl implements GradeDao {

    @Resource(name = "redis-personalcenter")
    private Redis redis;

    @Override
    public boolean add(Grade grade) {

        throw new NotImplementedException();
    }

    @Override
    public Grade get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(Grade grade) {

        throw new NotImplementedException();
    }

    @Override
    public List<Grade> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Grade> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Grade> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Grade> page(GradeQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<Grade> listAll() {

        throw new NotImplementedException();
    }
}
