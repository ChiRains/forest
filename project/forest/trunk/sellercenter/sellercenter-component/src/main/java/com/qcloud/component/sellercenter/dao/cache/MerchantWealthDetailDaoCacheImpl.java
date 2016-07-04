//package com.qcloud.component.sellercenter.dao.cache;
//
//import java.util.Map;
//import java.util.List;
//import java.util.Set;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import com.qcloud.pirates.data.CacheLoader;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.component.sellercenter.dao.MerchantWealthDetailDao;
//import com.qcloud.component.sellercenter.model.MerchantWealthDetail;
//import com.qcloud.component.sellercenter.model.query.MerchantWealthDetailQuery;
//
//@Repository
//public class MerchantWealthDetailDaoCacheImpl implements MerchantWealthDetailDao {
//
//    @Autowired
//    private MerchantWealthDetailDao merchantWealthDetailDaoMysqlImpl;
//
//    @Autowired
//    private MerchantWealthDetailDao merchantWealthDetailDaoRedisImpl;
//
//    @Override
//    public boolean add(MerchantWealthDetail merchantWealthDetail) {
//
//        return merchantWealthDetailDaoMysqlImpl.add(merchantWealthDetail);
//    }
//
//    @Override
//    public MerchantWealthDetail get(Long id) {
//
//        // return CacheLoader.get(merchantWealthDetailDaoRedisImpl, merchantWealthDetailDaoMysqlImpl, id);
//        return merchantWealthDetailDaoMysqlImpl.get(id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        return merchantWealthDetailDaoMysqlImpl.delete(id);
//    }
//
//    @Override
//    public boolean update(MerchantWealthDetail merchantWealthDetail) {
//
//        return merchantWealthDetailDaoMysqlImpl.update(merchantWealthDetail);
//    }
//
//    @Override
//    public List<MerchantWealthDetail> list(List<Long> idList) {
//
//        return CacheLoader.list(merchantWealthDetailDaoRedisImpl, merchantWealthDetailDaoMysqlImpl, idList);
//    }
//
//    @Override
//    public Map<Long, MerchantWealthDetail> map(Set<Long> idSet) {
//
//        return CacheLoader.map(merchantWealthDetailDaoRedisImpl, merchantWealthDetailDaoMysqlImpl, idSet);
//    }
//
//    @Override
//    public Page<MerchantWealthDetail> page(int start, int count) {
//
//        return merchantWealthDetailDaoMysqlImpl.page(start, count);
//    }
//
//    @Override
//    public Page<MerchantWealthDetail> page(MerchantWealthDetailQuery query, int start, int count) {
//
//        return merchantWealthDetailDaoMysqlImpl.page(query, start, count);
//    }
//
//    public List<MerchantWealthDetail> listAll() {
//
//        return merchantWealthDetailDaoMysqlImpl.listAll();
//    }
//}
