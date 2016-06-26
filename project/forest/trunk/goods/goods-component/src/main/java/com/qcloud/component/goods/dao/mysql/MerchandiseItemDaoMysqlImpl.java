package com.qcloud.component.goods.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.goods.dao.MerchandiseItemDao;
import com.qcloud.component.goods.model.MerchandiseItem;
import com.qcloud.component.goods.model.query.MerchandiseItemQuery;
		
@Repository
public class MerchandiseItemDaoMysqlImpl implements MerchandiseItemDao {

	@Resource(name = "sqlOperator-goods")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MerchandiseItem merchandiseItem) {
		return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.insert", merchandiseItem) == 1;
	}	
	
	@Override
	public MerchandiseItem get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MerchandiseItem merchandiseItem){
		return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.update", merchandiseItem) > 0;
	}
	
	@Override
	public List<MerchandiseItem> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseItem> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseItem> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchandiseItem> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.count4page",
				param);
		Page<MerchandiseItem> page = new Page<MerchandiseItem>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MerchandiseItem> page(MerchandiseItemQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchandiseItem> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.count4query",
				param);
		Page<MerchandiseItem> page = new Page<MerchandiseItem>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MerchandiseItem> listAll(){	
		List<MerchandiseItem> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseItemMapper.listAll");
		return list;
	}
}

