package com.qcloud.component.my.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.my.model.MyCollectionStatistics;
import com.qcloud.component.my.model.query.MyCollectionStatisticsQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface MyCollectionStatisticsDao extends ISimpleDao<MyCollectionStatistics, Long> {

    public boolean add(MyCollectionStatistics myCollectionStatistics);

    public MyCollectionStatistics get(Long id);

    MyCollectionStatistics get(Long userId, long classifyId, int type);

    public boolean delete(Long id);

    public boolean update(MyCollectionStatistics myCollectionStatistics);

    public List<MyCollectionStatistics> list(List<Long> idList);

    public Map<Long, MyCollectionStatistics> map(Set<Long> idSet);

    public Page<MyCollectionStatistics> page(MyCollectionStatisticsQuery query, int start, int size);

    public List<MyCollectionStatistics> listAll();

    List<MyCollectionStatistics> listByUserAndType(long userId, int type);
}
