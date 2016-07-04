//package com.qcloud.component.sellercenter.dao;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.data.api.ISimpleDao;
//import com.qcloud.component.sellercenter.model.MerchantWealthDetail;
//import com.qcloud.component.sellercenter.model.query.MerchantWealthDetailQuery;
//		
//public interface MerchantWealthDetailDao extends ISimpleDao<MerchantWealthDetail, Long> {
//
//	public boolean add(MerchantWealthDetail merchantWealthDetail);	
//	
//	public MerchantWealthDetail get(Long id);
//
//	public boolean delete(Long id);
//	
//	public boolean update(MerchantWealthDetail merchantWealthDetail);
//	
//	public List<MerchantWealthDetail> list(List<Long> idList);
//	
//	public Map<Long, MerchantWealthDetail> map(Set<Long> idSet);
//	
//	public Page<MerchantWealthDetail> page(MerchantWealthDetailQuery query, int start, int size);
//
//	public List<MerchantWealthDetail> listAll();
//	
//}
