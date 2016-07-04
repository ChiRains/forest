//package com.qcloud.component.sellercenter.dao.cache;
//
//import java.util.Map;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.qcloud.pirates.data.CacheLoader;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.component.sellercenter.dao.MerchantPayDao;
//import com.qcloud.component.sellercenter.model.MerchantPay;
//import com.qcloud.component.sellercenter.model.query.MerchantPayQuery;
//
//@Repository
//public class MerchantPayDaoCacheImpl implements MerchantPayDao {
//	
//	@Autowired
//	private MerchantPayDao merchantPayDaoMysqlImpl;
//	
////	@Autowired
////	private MerchantPayDao merchantPayDaoRedisImpl;
//
//	@Override
//	public boolean add(MerchantPay merchantPay) {
//		return merchantPayDaoMysqlImpl.add(merchantPay);
//	}
//
//	@Override
//	public MerchantPay get(Long id) {
//	    return merchantPayDaoMysqlImpl.get(id);
////		return CacheLoader.get(merchantPayDaoRedisImpl, merchantPayDaoMysqlImpl, id);
//	}
//
//	@Override
//	public boolean delete(Long id){
//		return merchantPayDaoMysqlImpl.delete(id);
//	}
//	
//	@Override
//	public boolean update(MerchantPay merchantPay){
//		return merchantPayDaoMysqlImpl.update(merchantPay);
//	}
//	
//	@Override
//	public List<MerchantPay> list(List<Long> idList) {
//		return CacheLoader.list(merchantPayDaoMysqlImpl, idList);
////        return CacheLoader.list(merchantPayDaoRedisImpl, merchantPayDaoMysqlImpl, idList);
//	}
//
//	@Override
//	public Map<Long, MerchantPay> map(Set<Long> idSet){
//        return CacheLoader.map(merchantPayDaoMysqlImpl, idSet);
////		return CacheLoader.map(merchantPayDaoRedisImpl, merchantPayDaoMysqlImpl, idSet);
//	}
//
//	@Override
//	public Page<MerchantPay> page(int start, int count){
//		return merchantPayDaoMysqlImpl.page(start, count);
//	}
//	
//	@Override
//	public Page<MerchantPay> page(MerchantPayQuery query, int start, int count){
//		return merchantPayDaoMysqlImpl.page(query, start, count);
//	}
//	
//	public List<MerchantPay> listAll(){
//		return merchantPayDaoMysqlImpl.listAll();
//	}
//}
//
