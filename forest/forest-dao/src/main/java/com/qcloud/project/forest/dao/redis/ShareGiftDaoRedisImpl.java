package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ShareGiftDao;
import com.qcloud.project.forest.model.ShareGift;
import com.qcloud.project.forest.model.query.ShareGiftQuery;

@Repository
public class ShareGiftDaoRedisImpl implements ShareGiftDao {

    // @Resource(name = "redis-forest")
    // private Redis redis;
    @Override
    public boolean add(ShareGift shareGift) {

        throw new NotImplementedException();
    }

    @Override
    public ShareGift get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(ShareGift shareGift) {

        throw new NotImplementedException();
    }

    @Override
    public List<ShareGift> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ShareGift> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ShareGift> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ShareGift> page(ShareGiftQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<ShareGift> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public ShareGift getByUserId(Long userId) {

        throw new NotImplementedException();
    }
}
