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
//import com.qcloud.component.sellercenter.dao.MemberDao;
//import com.qcloud.component.sellercenter.model.Member;
//import com.qcloud.component.sellercenter.model.query.MemberQuery;
//
//@Repository
//public class MemberDaoCacheImpl implements MemberDao {
//	
//	@Autowired
//	private MemberDao memberDaoMysqlImpl;
//	
////	@Autowired
////	private MemberDao memberDaoRedisImpl;
//
//	@Override
//	public boolean add(Member member) {
//		return memberDaoMysqlImpl.add(member);
//	}
//
//	@Override
//	public Member get(Long id) {
//	    return memberDaoMysqlImpl.get(id);
////		return CacheLoader.get(memberDaoRedisImpl, memberDaoMysqlImpl, id);
//	}
//
//	@Override
//	public boolean delete(Long id){
//		return memberDaoMysqlImpl.delete(id);
//	}
//	
//	@Override
//	public boolean update(Member member){
//		return memberDaoMysqlImpl.update(member);
//	}
//	
//	@Override
//	public List<Member> list(List<Long> idList) {
//        return CacheLoader.list(memberDaoMysqlImpl, idList);
////		return CacheLoader.list(memberDaoRedisImpl, memberDaoMysqlImpl, idList);
//	}
//
//	@Override
//	public Map<Long, Member> map(Set<Long> idSet){
//        return CacheLoader.map(memberDaoMysqlImpl, idSet);
////		return CacheLoader.map(memberDaoRedisImpl, memberDaoMysqlImpl, idSet);
//	}
//
//	@Override
//	public Page<Member> page(int start, int count){
//		return memberDaoMysqlImpl.page(start, count);
//	}
//	
//	@Override
//	public Page<Member> page(MemberQuery query, int start, int count){
//		return memberDaoMysqlImpl.page(query, start, count);
//	}
//	
//	public List<Member> listAll(){
//		return memberDaoMysqlImpl.listAll();
//	}
//
//    @Override
//    public Member getByAccount(String account) {       
//        return memberDaoMysqlImpl.getByAccount(account);
//    }
//}
//
