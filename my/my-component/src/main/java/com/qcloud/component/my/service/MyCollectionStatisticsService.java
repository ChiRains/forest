package com.qcloud.component.my.service;

import java.util.List;
import com.qcloud.component.my.model.MyCollectionStatistics;
import com.qcloud.component.my.model.query.MyCollectionStatisticsQuery;
import com.qcloud.pirates.data.Page;

public interface MyCollectionStatisticsService {

    public boolean add(MyCollectionStatistics myCollectionStatistics);

    public MyCollectionStatistics get(Long id);

    public MyCollectionStatistics get(Long userId, long classifyId, int type);

    public boolean delete(Long id);

    public boolean update(MyCollectionStatistics myCollectionStatistics);

    public Page<MyCollectionStatistics> page(MyCollectionStatisticsQuery query, int start, int count);

    public List<MyCollectionStatistics> listAll();

    List<MyCollectionStatistics> listByUserAndType(long userId, int type);
}
