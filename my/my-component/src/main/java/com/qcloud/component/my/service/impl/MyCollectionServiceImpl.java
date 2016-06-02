package com.qcloud.component.my.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.dao.MyCollectionDao;
import com.qcloud.component.my.model.MyCollection;
import com.qcloud.component.my.model.MyCollectionObjStatistics;
import com.qcloud.component.my.model.MyCollectionStatistics;
import com.qcloud.component.my.model.key.TypeEnum.CollectionType;
import com.qcloud.component.my.model.query.MyCollectionQuery;
import com.qcloud.component.my.service.MyCollectionObjStatisticsService;
import com.qcloud.component.my.service.MyCollectionService;
import com.qcloud.component.my.service.MyCollectionStatisticsService;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class MyCollectionServiceImpl implements MyCollectionService {

    @Autowired
    private MyCollectionDao                  myCollectionDao;

    @Autowired
    private AutoIdGenerator                  autoIdGenerator;

    @Autowired
    private MyCollectionStatisticsService    myCollectionStatisticsService;

    @Autowired
    private MyCollectionObjStatisticsService myCollectionObjStatisticsService;

    private static final String              ID_KEY = "personalcenter_my_collection";

    @Transactional
    public boolean add(MyCollection myCollection) {

        long id = autoIdGenerator.get(ID_KEY);
        myCollection.setId(id);
        boolean result = myCollectionDao.add(myCollection);
        AssertUtil.assertTrue(result, "添加收藏失败." + myCollection.getObjId());
        MyCollectionStatistics myCollectionStatistics = myCollectionStatisticsService.get(myCollection.getUserId(), myCollection.getClassifyId(), myCollection.getType());
        if (myCollectionStatistics == null) {
            myCollectionStatistics = new MyCollectionStatistics();
            myCollectionStatistics.setNumber(1);
            myCollectionStatistics.setUserId(myCollection.getUserId());
            myCollectionStatistics.setClassifyId(myCollection.getClassifyId());
            myCollectionStatistics.setType(myCollection.getType());
            myCollectionStatisticsService.add(myCollectionStatistics);
        } else {
            myCollectionStatistics.setNumber(myCollectionStatistics.getNumber() + 1);
            myCollectionStatisticsService.update(myCollectionStatistics);
        }
        MyCollectionObjStatistics myCollectionObjStatistics = myCollectionObjStatisticsService.get(myCollection.getObjId(), myCollection.getType());
        if (myCollectionObjStatistics == null) {
            myCollectionObjStatistics = new MyCollectionObjStatistics();
            myCollectionObjStatistics.setNumber(1);
            myCollectionObjStatistics.setObjId(myCollection.getObjId());
            myCollectionObjStatistics.setType(myCollection.getType());
            myCollectionObjStatisticsService.add(myCollectionObjStatistics);
        } else {
            myCollectionObjStatistics.setNumber(myCollectionObjStatistics.getNumber() + 1);
            myCollectionObjStatisticsService.update(myCollectionObjStatistics);
        }
        return result;
    }

    @Override
    public MyCollection get(Long id) {

        return myCollectionDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return myCollectionDao.delete(id);
    }

    @Override
    public boolean update(MyCollection myCollection) {

        return myCollectionDao.update(myCollection);
    }

    @Override
    public Page<MyCollection> page(MyCollectionQuery query, int start, int count) {

        return myCollectionDao.page(query, start, count);
    }

    public List<MyCollection> listAll() {

        return myCollectionDao.listAll();
    }

    @Override
    public List<MyCollection> list(Long userId, CollectionType type, Long classifyId, int start, int count) {

        return myCollectionDao.list(userId, type, classifyId, start, count);
    }

    @Override
    public MyCollection get(Long id, Long userId) {

        return myCollectionDao.get(id, userId);
    }

    @Override
    public MyCollection getByObject(Long objId, Long userId, CollectionType type) {

        return myCollectionDao.getByObject(objId, userId, type);
    }

    @Override
    public boolean delete(Long id, Long userId) {

        MyCollection myCollection = get(id, userId);
        if (myCollection == null) {
            return false;
        }
        MyCollectionStatistics myCollectionStatistics = myCollectionStatisticsService.get(myCollection.getUserId(), myCollection.getClassifyId(), myCollection.getType());
        if (myCollectionStatistics != null) {
            myCollectionStatistics.setNumber(myCollectionStatistics.getNumber() - 1);
            if (myCollectionStatistics.getNumber() == 0) {
                myCollectionStatisticsService.delete(myCollectionStatistics.getId());
            } else {
                myCollectionStatisticsService.update(myCollectionStatistics);
            }
        }
        MyCollectionObjStatistics myCollectionObjStatistics = myCollectionObjStatisticsService.get(myCollection.getObjId(), myCollection.getType());
        if (myCollectionObjStatistics != null) {
            myCollectionObjStatistics.setNumber(myCollectionObjStatistics.getNumber() - 1);
            if (myCollectionObjStatistics.getNumber() == 0) {
                myCollectionObjStatisticsService.delete(myCollectionObjStatistics.getId());
            } else {
                myCollectionObjStatisticsService.update(myCollectionObjStatistics);
            }
        }
        return myCollectionDao.delete(id, userId);
    }

    @Override
    public int count(Long objId, CollectionType type) {

        MyCollectionObjStatistics myCollectionObjStatistics = myCollectionObjStatisticsService.get(objId, type.getKey());
        if (myCollectionObjStatistics == null) {
            return 0;
        } else {
            return myCollectionObjStatistics.getNumber();
        }
    }
}
