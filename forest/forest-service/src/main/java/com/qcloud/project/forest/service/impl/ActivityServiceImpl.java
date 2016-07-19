package com.qcloud.project.forest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ActivityDao;
import com.qcloud.project.forest.model.Activity;
import com.qcloud.project.forest.model.query.ActivityQuery;
import com.qcloud.project.forest.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao         activityDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "forest_activity";

    @Override
    public boolean add(Activity activity) {

        long id = autoIdGenerator.get(ID_KEY);
        activity.setId(id);
        return activityDao.add(activity);
    }

    @Override
    public Activity get(Long id) {

        return activityDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return activityDao.delete(id);
    }

    @Override
    public boolean update(Activity activity) {

        return activityDao.update(activity);
    }

    @Override
    public Page<Activity> page(ActivityQuery query, int start, int count) {

        return activityDao.page(query, start, count);
    }

    public List<Activity> listAll() {

        return activityDao.listAll();
    }

    @Override
    public List<Activity> listBydepartmentId(Long departmentId) {

        return activityDao.listBydepartmentId(departmentId);
    }
}
