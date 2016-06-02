package com.qcloud.component.brokerage.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.brokerage.dao.UserShareTokenDao;
import com.qcloud.component.brokerage.model.UserShareToken;
import com.qcloud.component.brokerage.model.query.UserShareTokenQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
		
@Repository
public class UserShareTokenDaoMysqlImpl implements UserShareTokenDao {

	@Resource(name = "sqlOperator-brokerage")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(UserShareToken userShareToken) {
		return sqlOperator.insert("com.qcloud.component.brokerage.dao.mysql.mapper.UserShareTokenMapper.insert", userShareToken) == 1;
	}	
	
	@Override
	public UserShareToken get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserShareTokenMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.brokerage.dao.mysql.mapper.UserShareTokenMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(UserShareToken userShareToken){
		return sqlOperator.update("com.qcloud.component.brokerage.dao.mysql.mapper.UserShareTokenMapper.update", userShareToken) > 0;
	}
	
	@Override
	public List<UserShareToken> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, UserShareToken> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<UserShareToken> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<UserShareToken> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.UserShareTokenMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.brokerage.dao.mysql.mapper.UserShareTokenMapper.count4page",
				param);
		Page<UserShareToken> page = new Page<UserShareToken>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<UserShareToken> page(UserShareTokenQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<UserShareToken> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.UserShareTokenMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.brokerage.dao.mysql.mapper.UserShareTokenMapper.count4query",
				param);
		Page<UserShareToken> page = new Page<UserShareToken>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<UserShareToken> listAll(){	
		List<UserShareToken> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.UserShareTokenMapper.listAll");
		return list;
	}

    @Override
    public UserShareToken getByUser(Long userId) {
        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserShareTokenMapper.getByUser", userId);
    }

    @Override
    public UserShareToken getByToken(String token) {
        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserShareTokenMapper.getByToken", token);
    }
}

