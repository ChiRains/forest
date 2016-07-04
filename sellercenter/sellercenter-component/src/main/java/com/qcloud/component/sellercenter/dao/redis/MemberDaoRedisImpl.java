//package com.qcloud.component.sellercenter.dao.redis;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.lang.NotImplementedException;
//import org.springframework.stereotype.Repository;
//
//import com.qcloud.pirates.core.json.Json;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.data.redis.Redis;
//import com.qcloud.component.sellercenter.dao.MemberDao;
//import com.qcloud.component.sellercenter.model.Member;
//import com.qcloud.component.sellercenter.model.query.MemberQuery;
//
//@Repository
//public class MemberDaoRedisImpl implements MemberDao {
//
//	//@Resource(name = "redis-sellercenter")
//	//private Redis redis;
//
//	@Override
//	public boolean add(Member member) {			
//		throw new NotImplementedException();
//	}
//
//	@Override
//	public Member get(Long id) {		
//		throw new NotImplementedException();
//	}
//	
//	@Override
//	public boolean delete(Long id){
//		throw new NotImplementedException();
//	}
//	
//	@Override
//	public boolean update(Member member){
//		throw new NotImplementedException();
//	}
//	
//	@Override
//	public List<Member> list(List<Long> idList) {
//		throw new NotImplementedException();
//	}
//
//	@Override
//	public Map<Long, Member> map(Set<Long> idSet){
//		throw new NotImplementedException();
//	}
//		
//	@Override
//	public Page<Member> page(int start, int count){
//		throw new NotImplementedException();
//	}
//	
//	@Override
//	public Page<Member> page(MemberQuery query, int start, int count){
//		throw new NotImplementedException();
//	}
//	
//	@Override
//	public List<Member> listAll(){	
//		throw new NotImplementedException();
//	}
//
//    @Override
//    public Member getByAccount(String account) {
//        throw new NotImplementedException();
//    }
//}
//
