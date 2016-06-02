package com.qcloud.component.personalcenter.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.model.MySignInStatistics;
import com.qcloud.component.personalcenter.model.query.MySignInStatisticsQuery;

public interface MySignInStatisticsService {

    public boolean add(MySignInStatistics mySignInStatistics);

    public MySignInStatistics get(Long id);

    MySignInStatistics getByUser(Long userId);

    public boolean delete(Long id);

    public boolean update(MySignInStatistics mySignInStatistics);

    public Page<MySignInStatistics> page(MySignInStatisticsQuery query, int start, int count);

    public List<MySignInStatistics> listAll();
}
