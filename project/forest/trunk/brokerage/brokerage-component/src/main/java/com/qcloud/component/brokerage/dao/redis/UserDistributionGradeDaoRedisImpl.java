package com.qcloud.component.brokerage.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.brokerage.dao.UserDistributionGradeDao;
import com.qcloud.component.brokerage.model.UserDistributionGrade;
import com.qcloud.component.brokerage.model.query.UserDistributionGradeQuery;

@Repository
public class UserDistributionGradeDaoRedisImpl implements UserDistributionGradeDao {

    // @Resource(name = "redis-brokerage")
    // private Redis redis;
    @Override
    public boolean add(UserDistributionGrade userDistributionGrade) {

        throw new NotImplementedException();
    }

    @Override
    public UserDistributionGrade get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(UserDistributionGrade userDistributionGrade) {

        throw new NotImplementedException();
    }

    @Override
    public List<UserDistributionGrade> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, UserDistributionGrade> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<UserDistributionGrade> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<UserDistributionGrade> page(UserDistributionGradeQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<UserDistributionGrade> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public UserDistributionGrade getByUser(long userId) {

        throw new NotImplementedException();
    }
}
