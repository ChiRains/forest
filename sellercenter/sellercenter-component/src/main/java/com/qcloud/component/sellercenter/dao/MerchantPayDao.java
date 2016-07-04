//package com.qcloud.component.sellercenter.dao;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.data.api.ISimpleDao;
//import com.qcloud.component.sellercenter.model.MerchantPay;
//import com.qcloud.component.sellercenter.model.query.MerchantPayQuery;
//		
//public interface MerchantPayDao extends ISimpleDao<MerchantPay, Long> {
//
//	public boolean add(MerchantPay merchantPay);	
//	
//	public MerchantPay get(Long id);
//
//	public boolean delete(Long id);
//	
//	public boolean update(MerchantPay merchantPay);
//	
//	public List<MerchantPay> list(List<Long> idList);
//	
//	public Map<Long, MerchantPay> map(Set<Long> idSet);
//	
//	public Page<MerchantPay> page(MerchantPayQuery query, int start, int size);
//
//	public List<MerchantPay> listAll();
//	
//}
