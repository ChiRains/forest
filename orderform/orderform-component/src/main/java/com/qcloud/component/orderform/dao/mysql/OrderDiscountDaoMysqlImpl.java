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
import com.qcloud.component.orderform.dao.OrderDiscountDao;
import com.qcloud.component.orderform.model.OrderDiscount;
import com.qcloud.component.orderform.model.query.OrderDiscountQuery;
		
@Repository
public class OrderDiscountDaoMysqlImpl implements OrderDiscountDao {

	@Resource(name = "sqlOperator-orderform")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(OrderDiscount orderDiscount) {
		return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.OrderDiscountMapper.insert", orderDiscount) == 1;
	}	
	
	@Override
	public OrderDiscount get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.OrderDiscountMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.orderform.dao.mysql.mapper.OrderDiscountMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(OrderDiscount orderDiscount){
		return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.OrderDiscountMapper.update", orderDiscount) > 0;
	}
	
	@Override
	public List<OrderDiscount> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, OrderDiscount> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<OrderDiscount> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<OrderDiscount> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.OrderDiscountMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.orderform.dao.mysql.mapper.OrderDiscountMapper.count4page",
				param);
		Page<OrderDiscount> page = new Page<OrderDiscount>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<OrderDiscount> page(OrderDiscountQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<OrderDiscount> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.OrderDiscountMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.orderform.dao.mysql.mapper.OrderDiscountMapper.count4query",
				param);
		Page<OrderDiscount> page = new Page<OrderDiscount>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<OrderDiscount> listAll(){	
		List<OrderDiscount> list = sqlOperator.selectList(
				"com.qcloud.component.orderform.dao.mysql.mapper.OrderDiscountMapper.listAll");
		return list;
	}

    @Override
    public List<OrderDiscount> listBySubOrder(long subOrderId) {
        List<OrderDiscount> list = sqlOperator.selectList(
                "com.qcloud.component.orderform.dao.mysql.mapper.OrderDiscountMapper.listBySubOrder", subOrderId);
        return list;
    }
}

