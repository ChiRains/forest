package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ActivityDao;
import com.qcloud.project.forest.model.Activity;
import com.qcloud.project.forest.model.query.ActivityQuery;

@Repository
public class ActivityDaoRedisImpl implements ActivityDao {

    // @Resource(name = "redis-forest")
    // private Redis redis;
    @Override
    public boolean add(Activity activity) {

        throw new NotImplementedException();
    }

    @Override
    public Activity get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(Activity activity) {

        throw new NotImplementedException();
    }

    @Override
    public List<Activity> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Activity> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Activity> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Activity> page(ActivityQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<Activity> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<Activity> listBydepartmentId(Long departmentId) {

        throw new NotImplementedException();
    }
}
