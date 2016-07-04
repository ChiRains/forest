//package com.qcloud.component.sellercenter.dao.mysql;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Repository;
//import org.apache.commons.lang.NotImplementedException;
//
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
//import com.qcloud.pirates.util.StringUtil;
//import com.qcloud.component.sellercenter.dao.MemberDao;
//import com.qcloud.component.sellercenter.model.Member;
//import com.qcloud.component.sellercenter.model.query.MemberQuery;
//		   
//@Repository
//public class MemberDaoMysqlImpl implements MemberDao {
//
//	@Resource(name = "sqlOperator-sellercenter")
//	private SqlOperator sqlOperator;
//
//	@Override
//	public boolean add(Member member) {
//		return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.MemberMapper.insert", member) == 1;
//	}	
//	
//	@Override
//	public Member get(Long id) {
//		return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MemberMapper.get", id);
//	}	
//	
//	@Override
//	public boolean delete(Long id){
//		return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.MemberMapper.delete", id) > 0;
//	}	
//		
//	@Override
//	public boolean update(Member member){
//		return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.MemberMapper.update", member) > 0;
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
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("start", start);
//		param.put("count", count);
//
//		List<Member> list = sqlOperator.selectList(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MemberMapper.list4page",
//				param);
//		int total = sqlOperator.selectOne(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MemberMapper.count4page",
//				param);
//		Page<Member> page = new Page<Member>();
//		page.setCount(total);
//		page.setData(list);
//		return page;
//	}
//	
//	@Override
//	public Page<Member> page(MemberQuery query, int start, int count){
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("start", start);
//		param.put("count", count);
//		param.put("name", StringUtil.nullToEmpty(query.getName()));
//		param.put("keyword", StringUtil.nullToEmpty(query.getKeyword()));
//    
//		List<Member> list = sqlOperator.selectList(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MemberMapper.list4query",
//				param);
//		int total = sqlOperator.selectOne(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MemberMapper.count4query",
//				param);
//		Page<Member> page = new Page<Member>();
//		page.setCount(total);
//		page.setData(list);
//		return page;
//	}
//	
//	@Override
//	public List<Member> listAll(){	
//		List<Member> list = sqlOperator.selectList(
//				"com.qcloud.component.sellercenter.dao.mysql.mapper.MemberMapper.listAll");
//		return list;
//	}
//
//    @Override
//    public Member getByAccount(String account) {
//        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.MemberMapper.getByAccount", account);
//    }
//}
//
