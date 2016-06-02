package com.qcloud.component.orderform.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.orderform.dao.MembershipCardOrderDao;
import com.qcloud.component.orderform.model.MembershipCardOrder;
import com.qcloud.component.orderform.model.query.MembershipCardOrderQuery;
		
@Repository
public class MembershipCardOrderDaoMysqlImpl implements MembershipCardOrderDao {

	@Resource(name = "sqlOperator-orderform")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MembershipCardOrder membershipCardOrder) {
		return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderMapper.insert", membershipCardOrder) == 1;
	}	
	
	@Override
	public MembershipCardOrder get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MembershipCardOrder membershipCardOrder){
		return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderMapper.update", membershipCardOrder) > 0;
	}
	
	@Override
	public List<MembershipCardOrder> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MembershipCardOrder> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MembershipCardOrder> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MembershipCardOrder> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderMapper.count4page",
				param);
		Page<MembershipCardOrder> page = new Page<MembershipCardOrder>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MembershipCardOrder> page(MembershipCardOrderQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MembershipCardOrder> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderMapper.count4query",
				param);
		Page<MembershipCardOrder> page = new Page<MembershipCardOrder>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MembershipCardOrder> listAll(){	
		List<MembershipCardOrder> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.MembershipCardOrderMapper.listAll");
		return list;
	}
}

