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
import com.qcloud.component.orderform.dao.ReturnOrderItemDetailDao;
import com.qcloud.component.orderform.model.ReturnOrderItemDetail;
import com.qcloud.component.orderform.model.query.ReturnOrderItemDetailQuery;
		
@Repository
public class ReturnOrderItemDetailDaoMysqlImpl implements ReturnOrderItemDetailDao {

	@Resource(name = "sqlOperator-orderform")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(ReturnOrderItemDetail returnOrderItemDetail) {
		return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemDetailMapper.insert", returnOrderItemDetail) == 1;
	}	
	
	@Override
	public ReturnOrderItemDetail get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemDetailMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemDetailMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(ReturnOrderItemDetail returnOrderItemDetail){
		return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemDetailMapper.update", returnOrderItemDetail) > 0;
	}
	
	@Override
	public List<ReturnOrderItemDetail> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, ReturnOrderItemDetail> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<ReturnOrderItemDetail> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<ReturnOrderItemDetail> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemDetailMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemDetailMapper.count4page",
				param);
		Page<ReturnOrderItemDetail> page = new Page<ReturnOrderItemDetail>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<ReturnOrderItemDetail> page(ReturnOrderItemDetailQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<ReturnOrderItemDetail> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemDetailMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemDetailMapper.count4query",
				param);
		Page<ReturnOrderItemDetail> page = new Page<ReturnOrderItemDetail>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<ReturnOrderItemDetail> listAll(){	
		List<ReturnOrderItemDetail> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemDetailMapper.listAll");
		return list;
	}
}

