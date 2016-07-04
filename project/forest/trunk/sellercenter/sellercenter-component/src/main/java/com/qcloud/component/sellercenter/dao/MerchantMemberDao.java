//package com.qcloud.component.sellercenter.dao;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.data.api.ISimpleDao;
//import com.qcloud.component.sellercenter.model.MerchantMember;
//import com.qcloud.component.sellercenter.model.query.MerchantMemberQuery;
//		
//public interface MerchantMemberDao extends ISimpleDao<MerchantMember, Long> {
//
//	public boolean add(MerchantMember merchantMember);	
//	
//	public MerchantMember get(Long id);
//	
//	public MerchantMember get(Long memberId, Long merchantId);
//
//	public boolean delete(Long id);
//	
//	public boolean update(MerchantMember merchantMember);
//	
//	public List<MerchantMember> list(List<Long> idList);
//	
//	public Map<Long, MerchantMember> map(Set<Long> idSet);
//	
//	public Page<MerchantMember> page(MerchantMemberQuery query, int start, int size);
//
//	public List<MerchantMember> listAll();
//	
//	List<MerchantMember> listByMerchant(Long merchantId);
//	
//	List<MerchantMember> listByMember(Long memberId);
//}
