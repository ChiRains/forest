//package com.qcloud.component.goods.dao.cache;
//
//import java.util.Date;
//import java.util.Map;
//import java.util.List;
//import java.util.Set;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import com.qcloud.pirates.data.CacheLoader;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.component.goods.dao.MerchandiseMarketingDao;
//import com.qcloud.component.goods.model.MerchandiseMarketing;
//import com.qcloud.component.goods.model.query.MerchandiseMarketingQuery;
//
//@Repository
//public class MerchandiseMarketingDaoCacheImpl implements MerchandiseMarketingDao {
//
//    @Autowired
//    private MerchandiseMarketingDao merchandiseMarketingDaoMysqlImpl;
//
//    // @Autowired
//    // private MerchandiseMarketingDao merchandiseMarketingDaoRedisImpl;
//    @Override
//    public boolean add(MerchandiseMarketing merchandiseMarketing) {
//
//        return merchandiseMarketingDaoMysqlImpl.add(merchandiseMarketing);
//    }
//
//    @Override
//    public MerchandiseMarketing get(Long id) {
//
//        return merchandiseMarketingDaoMysqlImpl.get(id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        return merchandiseMarketingDaoMysqlImpl.delete(id);
//    }
//
//    @Override
//    public boolean update(MerchandiseMarketing merchandiseMarketing) {
//
//        return merchandiseMarketingDaoMysqlImpl.update(merchandiseMarketing);
//    }
//
//    @Override
//    public List<MerchandiseMarketing> list(List<Long> idList) {
//
//        return CacheLoader.list(merchandiseMarketingDaoMysqlImpl, idList);
//    }
//
//    @Override
//    public Map<Long, MerchandiseMarketing> map(Set<Long> idSet) {
//
//        return CacheLoader.map(merchandiseMarketingDaoMysqlImpl, idSet);
//    }
//
//    @Override
//    public Page<MerchandiseMarketing> page(int start, int count) {
//
//        return merchandiseMarketingDaoMysqlImpl.page(start, count);
//    }
//
//    @Override
//    public Page<MerchandiseMarketing> page(MerchandiseMarketingQuery query, int start, int count) {
//
//        return merchandiseMarketingDaoMysqlImpl.page(query, start, count);
//    }
//
//    public List<MerchandiseMarketing> listAll() {
//
//        return merchandiseMarketingDaoMysqlImpl.listAll();
//    }
//
//    @Override
//    public boolean update(MerchandiseMarketing merchandiseMarketing, Date lastUpdateTime) {
//
//        return merchandiseMarketingDaoMysqlImpl.update(merchandiseMarketing, lastUpdateTime);
//    }
//
//    @Override
//    public MerchandiseMarketing getByUnifiedMerchandise(Long unifiedMerchandiseId) {
//
//        return merchandiseMarketingDaoMysqlImpl.getByUnifiedMerchandise(unifiedMerchandiseId);
//    }
//
//    @Override
//    public boolean setEnable(Long id, int enable) {
//
//        return merchandiseMarketingDaoMysqlImpl.setEnable(id,enable);
//    }
//
//    @Override
//    public List<MerchandiseMarketing> list(int sence, double discountRange, int start, int count) {
//
//        return merchandiseMarketingDaoMysqlImpl.list(sence, discountRange, start, count);
//    }
//}
