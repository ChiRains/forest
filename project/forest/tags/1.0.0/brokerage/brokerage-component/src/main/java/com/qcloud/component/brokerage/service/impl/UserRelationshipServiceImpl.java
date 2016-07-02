package com.qcloud.component.brokerage.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.brokerage.dao.UserRelationshipDao;
import com.qcloud.component.brokerage.model.UserRelationship;
import com.qcloud.component.brokerage.model.key.TypeEnum.UserAllocationType;
import com.qcloud.component.brokerage.model.query.UserRelationshipQuery;
import com.qcloud.component.brokerage.service.UserRelationshipService;
import com.qcloud.pirates.data.Page;

@Service
public class UserRelationshipServiceImpl implements UserRelationshipService {

    @Autowired
    private UserRelationshipDao userRelationshipDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "distribution_user_relationship";

    @Override
    public boolean add(UserRelationship userRelationship) {

        long id = autoIdGenerator.get(ID_KEY);
        userRelationship.setId(id);
        return userRelationshipDao.add(userRelationship);
    }

    @Override
    public UserRelationship get(Long id) {

        return userRelationshipDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return userRelationshipDao.delete(id);
    }

    @Override
    public boolean update(UserRelationship userRelationship) {

        return userRelationshipDao.update(userRelationship);
    }

    @Override
    public Page<UserRelationship> page(UserRelationshipQuery query, int start, int count) {

        return userRelationshipDao.page(query, start, count);
    }

    public List<UserRelationship> listAll() {

        return userRelationshipDao.listAll();
    }

    @Override
    public UserRelationship getByUserId(Long userId) {

        return userRelationshipDao.getByUserId(userId);
    }

    @Override
    public List<UserRelationship> listChildren(long userId, UserAllocationType type) {

        return userRelationshipDao.listChildren(userId, type);
    }

    @Override
    public int countChildren(long recommedId, UserAllocationType type) {

        return userRelationshipDao.countChildren(recommedId, type);
    }

    @Override
    public Page<UserRelationship> pageRecommed(UserRelationshipQuery query, int start, int count) {

        return userRelationshipDao.pageRecommed(query, start, count);
    }

    @Override
    public List<UserRelationship> listChildren(long recommedId, UserAllocationType type, int level) {

        if (level < 1) {
            return new ArrayList<UserRelationship>();
        }
        List<UserRelationship> list = new ArrayList<UserRelationship>();
        List<UserRelationship> l = listChildren(recommedId, type);
        if (CollectionUtils.isNotEmpty(l)) {
            list.addAll(l);
            for (UserRelationship userRelationship : l) {
                List<UserRelationship> cl = listChildren(userRelationship.getUserId(), type, level - 1);
                if (CollectionUtils.isNotEmpty(l)) {
                    list.addAll(cl);
                }
            }
        }
        return list;
    }

    @Override
    public Page<UserRelationship> pageOneChildren(long recommedId, int start, int count) {

        return userRelationshipDao.pageOneChildren(recommedId, start, count);
    }

    @Override
    public Page<UserRelationship> pageTwoChildren(long recommedId, int start, int count) {

        return userRelationshipDao.pageTwoChildren(recommedId, start, count);
    }

    @Override
    public Page<UserRelationship> pageThreeChildren(long recommedId, int start, int count) {

        return userRelationshipDao.pageThreeChildren(recommedId, start, count);
    }

    @Override
    public int getCountByOneChildren(long recommedId) {

        return userRelationshipDao.getCountByOneChildren(recommedId);
    }

    @Override
    public int getCountByTwoChildren(long recommedId) {

        return userRelationshipDao.getCountByTwoChildren(recommedId);
    }

    @Override
    public int getCountByThreeChildren(long recommedId) {

        return userRelationshipDao.getCountByThreeChildren(recommedId);
    }
}
