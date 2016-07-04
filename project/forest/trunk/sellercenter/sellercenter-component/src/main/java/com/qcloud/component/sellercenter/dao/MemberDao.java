//package com.qcloud.component.sellercenter.dao;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.data.api.ISimpleDao;
//import com.qcloud.component.sellercenter.model.Member;
//import com.qcloud.component.sellercenter.model.query.MemberQuery;
//		
//public interface MemberDao extends ISimpleDao<Member, Long> {
//
//	public boolean add(Member member);	
//	
//	public Member get(Long id);
//
//	public boolean delete(Long id);
//	
//	public boolean update(Member member);
//	
//	public List<Member> list(List<Long> idList);
//	
//	public Map<Long, Member> map(Set<Long> idSet);
//	
//	public Page<Member> page(MemberQuery query, int start, int size);
//
//	public List<Member> listAll();
//	
//	Member getByAccount(String account);
//}
