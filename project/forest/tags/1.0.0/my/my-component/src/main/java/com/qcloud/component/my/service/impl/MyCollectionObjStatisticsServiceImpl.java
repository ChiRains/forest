package com.qcloud.component.my.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.dao.MyCollectionObjStatisticsDao;
import com.qcloud.component.my.model.MyCollectionObjStatistics;
import com.qcloud.component.my.model.query.MyCollectionObjStatisticsQuery;
import com.qcloud.component.my.service.MyCollectionObjStatisticsService;
import com.qcloud.pirates.data.Page;

@Service
public class MyCollectionObjStatisticsServiceImpl implements MyCollectionObjStatisticsService {

    @Autowired
    private MyCollectionObjStatisticsDao myCollectionObjStatisticsDao;

    @Autowired
    private AutoIdGenerator              autoIdGenerator;

    private static final String          ID_KEY = "personalcenter_my_collection_obj_statistics";

    @Override
    public boolean add(MyCollectionObjStatistics myCollectionObjStatistics) {

        long id = autoIdGenerator.get(ID_KEY);
        myCollectionObjStatistics.setId(id);
        return myCollectionObjStatisticsDao.add(myCollectionObjStatistics);
    }

    @Override
    public MyCollectionObjStatistics get(Long id) {

        return myCollectionObjStatisticsDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return myCollectionObjStatisticsDao.delete(id);
    }

    @Override
    public boolean update(MyCollectionObjStatistics myCollectionObjStatistics) {

        return myCollectionObjStatisticsDao.update(myCollectionObjStatistics);
    }

    @Override
    public Page<MyCollectionObjStatistics> page(MyCollectionObjStatisticsQuery query, int start, int count) {

        return myCollectionObjStatisticsDao.page(query, start, count);
    }

    public List<MyCollectionObjStatistics> listAll() {

        return myCollectionObjStatisticsDao.listAll();
    }

    @Override
    public MyCollectionObjStatistics get(long objId, int type) {

        return myCollectionObjStatisticsDao.get(objId, type);
    }
}
