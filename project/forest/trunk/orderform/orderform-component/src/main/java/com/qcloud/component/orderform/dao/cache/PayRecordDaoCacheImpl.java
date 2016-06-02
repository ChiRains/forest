//package com.qcloud.component.orderform.dao.cache;
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
//import com.qcloud.component.orderform.dao.PayRecordDao;
//import com.qcloud.component.orderform.model.PayRecord;
//import com.qcloud.component.orderform.model.query.PayRecordQuery;
//
//@Repository
//public class PayRecordDaoCacheImpl implements PayRecordDao {
//	
//	@Autowired
//	private PayRecordDao payRecordDaoMysqlImpl;
//	
////	@Autowired
////	private PayRecordDao payRecordDaoRedisImpl;
//
//	@Override
//	public boolean add(PayRecord payRecord) {
//		return payRecordDaoMysqlImpl.add(payRecord);
//	}
//
//	@Override
//	public PayRecord get(Long id) {
//	    return payRecordDaoMysqlImpl.get(id);
////		return CacheLoader.get(payRecordDaoRedisImpl, payRecordDaoMysqlImpl, id);
//	}
//
//	@Override
//	public boolean delete(Long id){
//		return payRecordDaoMysqlImpl.delete(id);
//	}
//	
//	@Override
//	public boolean update(PayRecord payRecord){
//		return payRecordDaoMysqlImpl.update(payRecord);
//	}
//	
//	@Override
//	public List<PayRecord> list(List<Long> idList) {
//		return CacheLoader.list(payRecordDaoMysqlImpl, idList);
//	}
//
//	@Override
//	public Map<Long, PayRecord> map(Set<Long> idSet){
//		return CacheLoader.map(payRecordDaoMysqlImpl, idSet);
//	}
//
//	@Override
//	public Page<PayRecord> page(int start, int count){
//		return payRecordDaoMysqlImpl.page(start, count);
//	}
//	
//	@Override
//	public Page<PayRecord> page(PayRecordQuery query, int start, int count){
//		return payRecordDaoMysqlImpl.page(query, start, count);
//	}
//	
//	public List<PayRecord> listAll(){
//		return payRecordDaoMysqlImpl.listAll();
//	}
//}
//
