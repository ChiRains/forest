package com.qcloud.component.commoditycenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.commoditycenter.dao.MerchandiseVipDiscountDao;
import com.qcloud.component.commoditycenter.model.MerchandiseVipDiscount;
import com.qcloud.component.commoditycenter.model.query.MerchandiseVipDiscountQuery;

@Repository
public class MerchandiseVipDiscountDaoCacheImpl implements MerchandiseVipDiscountDao {

    @Autowired
    private MerchandiseVipDiscountDao merchandiseVipDiscountDaoMysqlImpl;

    // @Autowired
    // private MerchandiseVipDiscountDao merchandiseVipDiscountDaoRedisImpl;
    @Override
    public boolean add(MerchandiseVipDiscount merchandiseVipDiscount) {

        return merchandiseVipDiscountDaoMysqlImpl.add(merchandiseVipDiscount);
    }

    @Override
    public MerchandiseVipDiscount get(Long id) {

        return merchandiseVipDiscountDaoMysqlImpl.get(id);
        // return CacheLoader.get(merchandiseVipDiscountDaoRedisImpl, merchandiseVipDiscountDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseVipDiscountDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MerchandiseVipDiscount merchandiseVipDiscount) {

        return merchandiseVipDiscountDaoMysqlImpl.update(merchandiseVipDiscount);
    }

    @Override
    public List<MerchandiseVipDiscount> list(List<Long> idList) {

        return CacheLoader.list(merchandiseVipDiscountDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MerchandiseVipDiscount> map(Set<Long> idSet) {

        return CacheLoader.map(merchandiseVipDiscountDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MerchandiseVipDiscount> page(int start, int count) {

        return merchandiseVipDiscountDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MerchandiseVipDiscount> page(MerchandiseVipDiscountQuery query, int start, int count) {

        return merchandiseVipDiscountDaoMysqlImpl.page(query, start, count);
    }

    public List<MerchandiseVipDiscount> listAll() {

        return merchandiseVipDiscountDaoMysqlImpl.listAll();
    }

    @Override
    public Double statMin(Long merchandiseItemId) {

        return merchandiseVipDiscountDaoMysqlImpl.statMin(merchandiseItemId);
    }

    @Override
    public Double statMax(Long merchandiseItemId) {

        return merchandiseVipDiscountDaoMysqlImpl.statMax(merchandiseItemId);
    }

    @Override
    public MerchandiseVipDiscount get(Long userId, Long merchandiseItemId) {

        return merchandiseVipDiscountDaoMysqlImpl.get(userId, merchandiseItemId);
    }

    @Override
    public List<MerchandiseVipDiscount> listByUser(long userId, long classifyId, String classifyBSID, int start, int count) {

        return merchandiseVipDiscountDaoMysqlImpl.listByUser(userId, classifyId, classifyBSID, start, count);
    }

    @Override
    public boolean deleteByUser(long userId) {

        return merchandiseVipDiscountDaoMysqlImpl.deleteByUser(userId);
    }

    @Override
    public int countByUser(long userId, long classifyId, String classifyBSID) {

        return merchandiseVipDiscountDaoMysqlImpl.countByUser(userId, classifyId, classifyBSID);
    }
}
