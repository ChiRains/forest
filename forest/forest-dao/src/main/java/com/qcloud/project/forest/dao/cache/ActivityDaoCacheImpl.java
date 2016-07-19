package com.qcloud.project.forest.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ActivityDao;
import com.qcloud.project.forest.model.Activity;
import com.qcloud.project.forest.model.query.ActivityQuery;

@Repository
public class ActivityDaoCacheImpl implements ActivityDao {

    @Autowired
    private ActivityDao activityDaoMysqlImpl;

    @Autowired
    private ActivityDao activityDaoRedisImpl;

    @Override
    public boolean add(Activity activity) {

        return activityDaoMysqlImpl.add(activity);
    }

    @Override
    public Activity get(Long id) {

        return CacheLoader.get(activityDaoRedisImpl, activityDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return activityDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(Activity activity) {

        return activityDaoMysqlImpl.update(activity);
    }

    @Override
    public List<Activity> list(List<Long> idList) {

        return CacheLoader.list(activityDaoRedisImpl, activityDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, Activity> map(Set<Long> idSet) {

        return CacheLoader.map(activityDaoRedisImpl, activityDaoMysqlImpl, idSet);
    }

    @Override
    public Page<Activity> page(int start, int count) {

        return activityDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<Activity> page(ActivityQuery query, int start, int count) {

        return activityDaoMysqlImpl.page(query, start, count);
    }

    public List<Activity> listAll() {

        return activityDaoMysqlImpl.listAll();
    }

    @Override
    public List<Activity> listBydepartmentId(Long departmentId) {

        return activityDaoMysqlImpl.listBydepartmentId(departmentId);
    }
}
