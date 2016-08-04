package com.qcloud.project.forest.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ShareGiftUserDao;
import com.qcloud.project.forest.model.ShareGiftUser;
import com.qcloud.project.forest.model.query.ShareGiftUserQuery;

@Repository
public class ShareGiftUserDaoCacheImpl implements ShareGiftUserDao {

    @Autowired
    private ShareGiftUserDao shareGiftUserDaoMysqlImpl;

    @Autowired
    private ShareGiftUserDao shareGiftUserDaoRedisImpl;

    @Override
    public boolean add(ShareGiftUser shareGiftUser) {

        return shareGiftUserDaoMysqlImpl.add(shareGiftUser);
    }

    @Override
    public ShareGiftUser get(Long id) {

        return CacheLoader.get(shareGiftUserDaoRedisImpl, shareGiftUserDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return shareGiftUserDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(ShareGiftUser shareGiftUser) {

        return shareGiftUserDaoMysqlImpl.update(shareGiftUser);
    }

    @Override
    public List<ShareGiftUser> list(List<Long> idList) {

        return CacheLoader.list(shareGiftUserDaoRedisImpl, shareGiftUserDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, ShareGiftUser> map(Set<Long> idSet) {

        return CacheLoader.map(shareGiftUserDaoRedisImpl, shareGiftUserDaoMysqlImpl, idSet);
    }

    @Override
    public Page<ShareGiftUser> page(int start, int count) {

        return shareGiftUserDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<ShareGiftUser> page(ShareGiftUserQuery query, int start, int count) {

        return shareGiftUserDaoMysqlImpl.page(query, start, count);
    }

    public List<ShareGiftUser> listAll() {

        return shareGiftUserDaoMysqlImpl.listAll();
    }

    @Override
    public List<ShareGiftUser> listByCode(String code) {

        return shareGiftUserDaoMysqlImpl.listByCode(code);
    }

    @Override
    public int countByCode(String code) {

        return shareGiftUserDaoMysqlImpl.countByCode(code);
    }

    @Override
    public int countCouponByCode(String code) {

        return shareGiftUserDaoMysqlImpl.countCouponByCode(code);
    }
}
