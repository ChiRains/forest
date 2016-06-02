//package com.qcloud.component.orderform.dao;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.data.api.ISimpleDao;
//import com.qcloud.component.orderform.model.PayRecord;
//import com.qcloud.component.orderform.model.query.PayRecordQuery;
//		
//public interface PayRecordDao extends ISimpleDao<PayRecord, Long> {
//
//	public boolean add(PayRecord payRecord);	
//	
//	public PayRecord get(Long id);
//
//	public boolean delete(Long id);
//	
//	public boolean update(PayRecord payRecord);
//	
//	public List<PayRecord> list(List<Long> idList);
//	
//	public Map<Long, PayRecord> map(Set<Long> idSet);
//	
//	public Page<PayRecord> page(PayRecordQuery query, int start, int size);
//
//	public List<PayRecord> listAll();
//	
//}
