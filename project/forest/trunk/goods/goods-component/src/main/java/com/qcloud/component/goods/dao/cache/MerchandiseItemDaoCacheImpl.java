package com.qcloud.component.goods.dao.cache;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseItemDao;
import com.qcloud.component.goods.model.MerchandiseItem;
import com.qcloud.component.goods.model.key.TypeEnum.OrderType;
import com.qcloud.component.goods.model.key.TypeEnum.QueryItemType;
import com.qcloud.component.goods.model.query.MerchandiseItemQuery;

@Repository
public class MerchandiseItemDaoCacheImpl implements MerchandiseItemDao {

    @Autowired
    private MerchandiseItemDao merchandiseItemDaoMysqlImpl;

    // @Autowired
    // private MerchandiseItemDao merchandiseItemDaoRedisImpl;
    @Override
    public boolean add(MerchandiseItem merchandiseItem) {

        return merchandiseItemDaoMysqlImpl.add(merchandiseItem);
    }

    @Override
    public MerchandiseItem get(Long id) {

        return merchandiseItemDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseItemDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MerchandiseItem merchandiseItem) {

        return merchandiseItemDaoMysqlImpl.update(merchandiseItem);
    }

    @Override
    public List<MerchandiseItem> list(List<Long> idList) {

        return CacheLoader.list(merchandiseItemDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MerchandiseItem> map(Set<Long> idSet) {

        return CacheLoader.map(merchandiseItemDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MerchandiseItem> page(int start, int count) {

        return merchandiseItemDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MerchandiseItem> page(MerchandiseItemQuery query, int start, int count) {

        return merchandiseItemDaoMysqlImpl.page(query, start, count);
    }

    public List<MerchandiseItem> listAll() {

        return merchandiseItemDaoMysqlImpl.listAll();
    }

    @Override
    public MerchandiseItem getByUnifiedMerchandise(Long unifiedMerchandiseId) {

        return merchandiseItemDaoMysqlImpl.getByUnifiedMerchandise(unifiedMerchandiseId);
    }

    @Override
    public List<MerchandiseItem> list(Map where) {

        return merchandiseItemDaoMysqlImpl.list(where);
    }

    @Override
    public MerchandiseItem get(Map where) {

        return merchandiseItemDaoMysqlImpl.get(where);
    }

    @Override
    public Page<MerchandiseItem> page(Map where, int start, int size) {

        return merchandiseItemDaoMysqlImpl.page(where, start, size);
    }

    @Override
    public List<MerchandiseItem> listByMerchandise(Long merchandiseId) {

        return merchandiseItemDaoMysqlImpl.listByMerchandise(merchandiseId);
    }

    @Override
    public Page<MerchandiseItem> page4Price(String name, Long merchantId, Long merchantClassifyId, int start, int size, OrderType orderType, QueryItemType queryItemType) {

        return merchandiseItemDaoMysqlImpl.page4Price(name, merchantId, merchantClassifyId, start, size, orderType, queryItemType);
    }

    @Override
    public Page<MerchandiseItem> page4Date(String name, Long merchantId, Long merchantClassifyId, int start, int size, OrderType orderType, QueryItemType queryItemType) {

        return merchandiseItemDaoMysqlImpl.page4Date(name, merchantId, merchantClassifyId, start, size, orderType, queryItemType);
    }

    @Override
    public MerchandiseItem getBySpecifications(Long merchandiseId, Long merchandiseSpecificationsId) {

        return merchandiseItemDaoMysqlImpl.getBySpecifications(merchandiseId, merchandiseSpecificationsId);
    }

    @Override
    public boolean update(MerchandiseItem merchandiseItem, Date lastUpadteTime) {

        return merchandiseItemDaoMysqlImpl.update(merchandiseItem, lastUpadteTime);
    }

    @Override
    public Page<MerchandiseItem> query(MerchandiseItemQuery query, int start, int count) {

        return merchandiseItemDaoMysqlImpl.query(query, start, count);
    }

    @Override
    public Page<MerchandiseItem> list4Select4Admin(MerchandiseItemQuery query, int start, int count) {

        return merchandiseItemDaoMysqlImpl.list4Select4Admin(query, start, count);
    }

    @Override
    public List<MerchandiseItem> merchandiseListByMerchantId(long merchantId) {

        return merchandiseItemDaoMysqlImpl.merchandiseListByMerchantId(merchantId);
    }
}
