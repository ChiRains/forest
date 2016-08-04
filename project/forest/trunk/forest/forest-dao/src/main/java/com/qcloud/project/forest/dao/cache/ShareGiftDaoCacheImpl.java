package com.qcloud.project.forest.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ShareGiftDao;
import com.qcloud.project.forest.model.ShareGift;
import com.qcloud.project.forest.model.query.ShareGiftQuery;

@Repository
public class ShareGiftDaoCacheImpl implements ShareGiftDao {

    @Autowired
    private ShareGiftDao shareGiftDaoMysqlImpl;

    @Autowired
    private ShareGiftDao shareGiftDaoRedisImpl;

    @Override
    public boolean add(ShareGift shareGift) {

        return shareGiftDaoMysqlImpl.add(shareGift);
    }

    @Override
    public ShareGift get(Long id) {

        return CacheLoader.get(shareGiftDaoRedisImpl, shareGiftDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return shareGiftDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(ShareGift shareGift) {

        return shareGiftDaoMysqlImpl.update(shareGift);
    }

    @Override
    public List<ShareGift> list(List<Long> idList) {

        return CacheLoader.list(shareGiftDaoRedisImpl, shareGiftDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, ShareGift> map(Set<Long> idSet) {

        return CacheLoader.map(shareGiftDaoRedisImpl, shareGiftDaoMysqlImpl, idSet);
    }

    @Override
    public Page<ShareGift> page(int start, int count) {

        return shareGiftDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<ShareGift> page(ShareGiftQuery query, int start, int count) {

        return shareGiftDaoMysqlImpl.page(query, start, count);
    }

    public List<ShareGift> listAll() {

        return shareGiftDaoMysqlImpl.listAll();
    }

    @Override
    public ShareGift getByUserId(Long userId) {

        return shareGiftDaoMysqlImpl.getByUserId(userId);
    }
}
