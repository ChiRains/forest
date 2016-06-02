package com.qcloud.component.seckill.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.seckill.dao.SeckillOrderDao;
import com.qcloud.component.seckill.model.SeckillOrder;
import com.qcloud.component.seckill.model.query.SeckillOrderQuery;
		
@Repository
public class SeckillOrderDaoMysqlImpl implements SeckillOrderDao {

	@Resource(name = "sqlOperator-seckill")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(SeckillOrder seckillOrder) {
		return sqlOperator.insert("com.qcloud.component.seckill.dao.mysql.mapper.SeckillOrderMapper.insert", seckillOrder) == 1;
	}	
	
	@Override
	public SeckillOrder get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.seckill.dao.mysql.mapper.SeckillOrderMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.seckill.dao.mysql.mapper.SeckillOrderMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(SeckillOrder seckillOrder){
		return sqlOperator.update("com.qcloud.component.seckill.dao.mysql.mapper.SeckillOrderMapper.update", seckillOrder) > 0;
	}
	
	@Override
	public List<SeckillOrder> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, SeckillOrder> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<SeckillOrder> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<SeckillOrder> list = sqlOperator.selectList(
				"com.qcloud.component.seckill.dao.mysql.mapper.SeckillOrderMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.seckill.dao.mysql.mapper.SeckillOrderMapper.count4page",
				param);
		Page<SeckillOrder> page = new Page<SeckillOrder>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<SeckillOrder> page(SeckillOrderQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<SeckillOrder> list = sqlOperator.selectList(
				"com.qcloud.component.seckill.dao.mysql.mapper.SeckillOrderMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.seckill.dao.mysql.mapper.SeckillOrderMapper.count4query",
				param);
		Page<SeckillOrder> page = new Page<SeckillOrder>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<SeckillOrder> listAll(){	
		List<SeckillOrder> list = sqlOperator.selectList(
				"com.qcloud.component.seckill.dao.mysql.mapper.SeckillOrderMapper.listAll");
		return list;
	}
}

