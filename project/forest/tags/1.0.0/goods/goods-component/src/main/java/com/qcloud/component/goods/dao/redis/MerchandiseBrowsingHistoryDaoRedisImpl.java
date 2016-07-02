package com.qcloud.component.goods.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.goods.dao.MerchandiseBrowsingHistoryDao;
import com.qcloud.component.goods.model.MerchandiseBrowsingHistory;
import com.qcloud.component.goods.model.query.MerchandiseBrowsingHistoryQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MerchandiseBrowsingHistoryDaoRedisImpl implements MerchandiseBrowsingHistoryDao {

    // @Resource(name = "redis-commoditycenter")
    // private Redis redis;
    @Override
    public boolean add(MerchandiseBrowsingHistory merchandiseBrowsingHistory) {

        throw new NotImplementedException();
    }

    @Override
    public MerchandiseBrowsingHistory get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MerchandiseBrowsingHistory merchandiseBrowsingHistory) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseBrowsingHistory> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseBrowsingHistory> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseBrowsingHistory> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseBrowsingHistory> page(MerchandiseBrowsingHistoryQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseBrowsingHistory> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseBrowsingHistory> listByUser(long userId, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public int countByUserId(long userId) {

        throw new NotImplementedException();
    }
}
