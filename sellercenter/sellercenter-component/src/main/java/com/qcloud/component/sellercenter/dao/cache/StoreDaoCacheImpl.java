//package com.qcloud.component.sellercenter.dao.cache;
//
//import java.util.Map;
//import java.util.List;
//import java.util.Set;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import com.qcloud.pirates.data.CacheLoader;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.component.sellercenter.dao.StoreDao;
//import com.qcloud.component.sellercenter.model.Store;
//import com.qcloud.component.sellercenter.model.query.StoreQuery;
//
//@Repository
//public class StoreDaoCacheImpl implements StoreDao {
//
//    @Autowired
//    private StoreDao storeDaoMysqlImpl;
//
//    // @Autowired
//    // private StoreDao storeDaoRedisImpl;
//    @Override
//    public boolean add(Store store) {
//
//        return storeDaoMysqlImpl.add(store);
//    }
//
//    @Override
//    public Store get(Long id) {
//
//        return storeDaoMysqlImpl.get(id);
//        // return CacheLoader.get(storeDaoRedisImpl, storeDaoMysqlImpl, id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        return storeDaoMysqlImpl.delete(id);
//    }
//
//    @Override
//    public boolean update(Store store) {
//
//        return storeDaoMysqlImpl.update(store);
//    }
//
//    @Override
//    public List<Store> list(List<Long> idList) {
//
//        return CacheLoader.list(storeDaoMysqlImpl, idList);
//        // return CacheLoader.list(storeDaoRedisImpl, storeDaoMysqlImpl, idList);
//    }
//
//    @Override
//    public Map<Long, Store> map(Set<Long> idSet) {
//
//        return CacheLoader.map(storeDaoMysqlImpl, idSet);
//        // return CacheLoader.map(storeDaoRedisImpl, storeDaoMysqlImpl, idSet);
//    }
//
//    @Override
//    public Page<Store> page(int start, int count) {
//
//        return storeDaoMysqlImpl.page(start, count);
//    }
//
//    @Override
//    public Page<Store> page(StoreQuery query, int start, int count) {
//
//        return storeDaoMysqlImpl.page(query, start, count);
//    }
//
//    public List<Store> listAll() {
//
//        return storeDaoMysqlImpl.listAll();
//    }
//
//    @Override
//    public List<Store> listChildren(Long parentId) {
//
//        return storeDaoMysqlImpl.listChildren(parentId);
//    }
//
//    @Override
//    public List<Store> listByMerchant(Long merchantId) {
//
//        return storeDaoMysqlImpl.listByMerchant(merchantId);
//    }
//
//    @Override
//    public List<Store> listByAddress(String province, String city, String district) {
//
//        return storeDaoMysqlImpl.listByAddress(province, city, district);
//    }
//
//    @Override
//    public List<Store> listByParentId(Long parentId) {
//
//        return storeDaoMysqlImpl.listByParentId(parentId);
//    }
//}
