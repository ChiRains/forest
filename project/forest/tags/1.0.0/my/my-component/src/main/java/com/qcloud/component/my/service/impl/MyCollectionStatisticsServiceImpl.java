package com.qcloud.component.my.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.dao.MyCollectionStatisticsDao;
import com.qcloud.component.my.model.MyCollectionStatistics;
import com.qcloud.component.my.model.query.MyCollectionStatisticsQuery;
import com.qcloud.component.my.service.MyCollectionStatisticsService;
import com.qcloud.pirates.data.Page;

@Service
public class MyCollectionStatisticsServiceImpl implements MyCollectionStatisticsService {

    @Autowired
    private MyCollectionStatisticsDao myCollectionStatisticsDao;

    @Autowired
    private AutoIdGenerator           autoIdGenerator;

    private static final String       ID_KEY = "personalcenter_my_collection_statistics";

    @Override
    public boolean add(MyCollectionStatistics myCollectionStatistics) {

        long id = autoIdGenerator.get(ID_KEY);
        myCollectionStatistics.setId(id);
        return myCollectionStatisticsDao.add(myCollectionStatistics);
    }

    @Override
    public MyCollectionStatistics get(Long id) {

        return myCollectionStatisticsDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return myCollectionStatisticsDao.delete(id);
    }

    @Override
    public boolean update(MyCollectionStatistics myCollectionStatistics) {

        return myCollectionStatisticsDao.update(myCollectionStatistics);
    }

    @Override
    public Page<MyCollectionStatistics> page(MyCollectionStatisticsQuery query, int start, int count) {

        return myCollectionStatisticsDao.page(query, start, count);
    }

    public List<MyCollectionStatistics> listAll() {

        return myCollectionStatisticsDao.listAll();
    }

    @Override
    public MyCollectionStatistics get(Long userId, long classifyId, int type) {

        return myCollectionStatisticsDao.get(userId, classifyId, type);
    }

    @Override
    public List<MyCollectionStatistics> listByUserAndType(long userId, int type) {

        return myCollectionStatisticsDao.listByUserAndType(userId, type);
    }
}
