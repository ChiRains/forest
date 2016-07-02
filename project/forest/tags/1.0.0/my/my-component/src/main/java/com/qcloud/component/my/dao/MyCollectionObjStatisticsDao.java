package com.qcloud.component.my.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.my.model.MyCollectionObjStatistics;
import com.qcloud.component.my.model.query.MyCollectionObjStatisticsQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface MyCollectionObjStatisticsDao extends ISimpleDao<MyCollectionObjStatistics, Long> {

    public boolean add(MyCollectionObjStatistics myCollectionObjStatistics);

    public MyCollectionObjStatistics get(Long id);

    public boolean delete(Long id);

    public boolean update(MyCollectionObjStatistics myCollectionObjStatistics);

    public List<MyCollectionObjStatistics> list(List<Long> idList);

    public Map<Long, MyCollectionObjStatistics> map(Set<Long> idSet);

    public Page<MyCollectionObjStatistics> page(MyCollectionObjStatisticsQuery query, int start, int size);

    public List<MyCollectionObjStatistics> listAll();

    MyCollectionObjStatistics get(long objId, int type);
}
