package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.Activity;
import com.qcloud.project.forest.model.query.ActivityQuery;

public interface ActivityDao extends ISimpleDao<Activity, Long> {

    public boolean add(Activity activity);

    public Activity get(Long id);

    public boolean delete(Long id);

    public boolean update(Activity activity);

    public List<Activity> list(List<Long> idList);

    public Map<Long, Activity> map(Set<Long> idSet);

    public Page<Activity> page(ActivityQuery query, int start, int size);

    public List<Activity> listAll();

    public List<Activity> listBydepartmentId(Long departmentId);
}
