package com.qcloud.component.my.service;

import java.util.List;
import com.qcloud.component.my.model.MyCollectionObjStatistics;
import com.qcloud.component.my.model.query.MyCollectionObjStatisticsQuery;
import com.qcloud.pirates.data.Page;

public interface MyCollectionObjStatisticsService {

    public boolean add(MyCollectionObjStatistics myCollectionObjStatistics);

    public MyCollectionObjStatistics get(Long id);

    public MyCollectionObjStatistics get(long objId, int type);

    public boolean delete(Long id);

    public boolean update(MyCollectionObjStatistics myCollectionObjStatistics);

    public Page<MyCollectionObjStatistics> page(MyCollectionObjStatisticsQuery query, int start, int count);

    public List<MyCollectionObjStatistics> listAll();
}
