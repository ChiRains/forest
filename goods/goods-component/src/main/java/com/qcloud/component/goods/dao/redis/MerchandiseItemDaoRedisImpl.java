//package com.qcloud.component.goods.dao.redis;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import javax.annotation.Resource;
//import org.apache.commons.lang.NotImplementedException;
//import org.springframework.stereotype.Repository;
//import com.qcloud.pirates.core.json.Json;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.data.redis.Redis;
//import com.qcloud.component.goods.dao.MerchandiseItemDao;
//import com.qcloud.component.goods.model.MerchandiseItem;
//import com.qcloud.component.goods.model.key.TypeEnum.OrderType;
//import com.qcloud.component.goods.model.key.TypeEnum.QueryItemType;
//import com.qcloud.component.goods.model.query.MerchandiseItemQuery;
//
//@Repository
//public class MerchandiseItemDaoRedisImpl implements MerchandiseItemDao {
//
//    // @Resource(name = "redis-commoditycenter")
//    // private Redis redis;
//    @Override
//    public boolean add(MerchandiseItem merchandiseItem) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public MerchandiseItem get(Long id) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public boolean update(MerchandiseItem merchandiseItem) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public List<MerchandiseItem> list(List<Long> idList) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Map<Long, MerchandiseItem> map(Set<Long> idSet) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Page<MerchandiseItem> page(int start, int count) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Page<MerchandiseItem> page(MerchandiseItemQuery query, int start, int count) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public List<MerchandiseItem> listAll() {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public MerchandiseItem getByUnifiedMerchandise(Long unifiedMerchandiseId) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public List<MerchandiseItem> listByMerchandise(Long merchandiseId) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public List<MerchandiseItem> list(Map where) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public MerchandiseItem get(Map where) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Page<MerchandiseItem> page(Map where, int start, int size) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Page<MerchandiseItem> page4Price(String name, Long merchantId, Long merchantClassifyId, int start, int size, OrderType orderType, QueryItemType queryItemType) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Page<MerchandiseItem> page4Date(String name, Long merchantId, Long merchantClassifyId, int start, int size, OrderType orderType, QueryItemType queryItemType) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public MerchandiseItem getBySpecifications(Long merchandiseId, Long merchandiseSpecificationsId) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public boolean update(MerchandiseItem merchandiseItem, Date lastUpadteTime) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Page<MerchandiseItem> query(MerchandiseItemQuery query, int start, int count) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public Page<MerchandiseItem> list4Select4Admin(MerchandiseItemQuery query, int start, int count) {
//
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public List<MerchandiseItem> merchandiseListByMerchantId(long merchantId) {
//
//        throw new NotImplementedException();
//    }
//}
