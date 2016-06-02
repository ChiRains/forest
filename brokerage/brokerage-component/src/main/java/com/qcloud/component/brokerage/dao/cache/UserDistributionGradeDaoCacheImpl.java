package com.qcloud.component.brokerage.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.dao.UserDistributionGradeDao;
import com.qcloud.component.brokerage.model.UserDistributionGrade;
import com.qcloud.component.brokerage.model.query.UserDistributionGradeQuery;

@Repository
public class UserDistributionGradeDaoCacheImpl implements UserDistributionGradeDao {

    @Autowired
    private UserDistributionGradeDao userDistributionGradeDaoMysqlImpl;

    // @Autowired
    // private UserDistributionGradeDao userDistributionGradeDaoRedisImpl;
    @Override
    public boolean add(UserDistributionGrade userDistributionGrade) {

        return userDistributionGradeDaoMysqlImpl.add(userDistributionGrade);
    }

    @Override
    public UserDistributionGrade get(Long id) {

        return userDistributionGradeDaoMysqlImpl.get(id);
        // return CacheLoader.get(userDistributionGradeDaoRedisImpl, userDistributionGradeDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return userDistributionGradeDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(UserDistributionGrade userDistributionGrade) {

        return userDistributionGradeDaoMysqlImpl.update(userDistributionGrade);
    }

    @Override
    public List<UserDistributionGrade> list(List<Long> idList) {

        return CacheLoader.list(userDistributionGradeDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, UserDistributionGrade> map(Set<Long> idSet) {

        return CacheLoader.map(userDistributionGradeDaoMysqlImpl, idSet);
    }

    @Override
    public Page<UserDistributionGrade> page(int start, int count) {

        return userDistributionGradeDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<UserDistributionGrade> page(UserDistributionGradeQuery query, int start, int count) {

        return userDistributionGradeDaoMysqlImpl.page(query, start, count);
    }

    public List<UserDistributionGrade> listAll() {

        return userDistributionGradeDaoMysqlImpl.listAll();
    }

    @Override
    public UserDistributionGrade getByUser(long userId) {

        return userDistributionGradeDaoMysqlImpl.getByUser(userId);
    }
}
