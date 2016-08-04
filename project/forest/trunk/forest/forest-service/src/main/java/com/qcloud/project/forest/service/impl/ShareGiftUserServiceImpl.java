package com.qcloud.project.forest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ShareGiftUserDao;
import com.qcloud.project.forest.model.ShareGiftUser;
import com.qcloud.project.forest.model.query.ShareGiftUserQuery;
import com.qcloud.project.forest.service.ShareGiftUserService;

@Service
public class ShareGiftUserServiceImpl implements ShareGiftUserService {

    @Autowired
    private ShareGiftUserDao    shareGiftUserDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "forest_share_gift_user";

    @Override
    public boolean add(ShareGiftUser shareGiftUser) {

        long id = autoIdGenerator.get(ID_KEY);
        shareGiftUser.setId(id);
        return shareGiftUserDao.add(shareGiftUser);
    }

    @Override
    public ShareGiftUser get(Long id) {

        return shareGiftUserDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return shareGiftUserDao.delete(id);
    }

    @Override
    public boolean update(ShareGiftUser shareGiftUser) {

        return shareGiftUserDao.update(shareGiftUser);
    }

    @Override
    public Page<ShareGiftUser> page(ShareGiftUserQuery query, int start, int count) {

        return shareGiftUserDao.page(query, start, count);
    }

    public List<ShareGiftUser> listAll() {

        return shareGiftUserDao.listAll();
    }

    @Override
    public List<ShareGiftUser> listByCode(String code) {

        return shareGiftUserDao.listByCode(code);
    }

    @Override
    public int countByCode(String code) {

        return shareGiftUserDao.countByCode(code);
    }

    @Override
    public int countCouponByCode(String code) {

        return shareGiftUserDao.countCouponByCode(code);
    }
}
