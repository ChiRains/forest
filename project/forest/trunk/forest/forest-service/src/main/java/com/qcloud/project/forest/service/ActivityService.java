package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.Activity;
import com.qcloud.project.forest.model.query.ActivityQuery;

public interface ActivityService {

    public boolean add(Activity activity);

    public Activity get(Long id);

    public boolean delete(Long id);

    public boolean update(Activity activity);

    public Page<Activity> page(ActivityQuery query, int start, int count);

    public List<Activity> listAll();

    public List<Activity> listBydepartmentId(Long departmentId);
}
