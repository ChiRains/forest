package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ShareGiftUserDao;
import com.qcloud.project.forest.model.ShareGiftUser;
import com.qcloud.project.forest.model.query.ShareGiftUserQuery;

@Repository
public class ShareGiftUserDaoRedisImpl implements ShareGiftUserDao {

    // @Resource(name = "redis-forest")
    // private Redis redis;
    @Override
    public boolean add(ShareGiftUser shareGiftUser) {

        throw new NotImplementedException();
    }

    @Override
    public ShareGiftUser get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(ShareGiftUser shareGiftUser) {

        throw new NotImplementedException();
    }

    @Override
    public List<ShareGiftUser> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ShareGiftUser> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ShareGiftUser> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ShareGiftUser> page(ShareGiftUserQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<ShareGiftUser> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<ShareGiftUser> listByCode(String code) {

        throw new NotImplementedException();
    }

    @Override
    public int countByCode(String code) {

        throw new NotImplementedException();
    }

    @Override
    public int countCouponByCode(String code) {

        throw new NotImplementedException();
    }
}
