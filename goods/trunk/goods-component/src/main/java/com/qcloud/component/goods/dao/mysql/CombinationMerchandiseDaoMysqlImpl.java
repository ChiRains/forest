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
import com.qcloud.component.goods.dao.CombinationMerchandiseDao;
import com.qcloud.component.goods.model.CombinationMerchandise;
import com.qcloud.component.goods.model.query.CombinationMerchandiseQuery;
		
@Repository
public class CombinationMerchandiseDaoMysqlImpl implements CombinationMerchandiseDao {

	@Resource(name = "sqlOperator-goods")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(CombinationMerchandise combinationMerchandise) {
		return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.CombinationMerchandiseMapper.insert", combinationMerchandise) == 1;
	}	
	
	@Override
	public CombinationMerchandise get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.CombinationMerchandiseMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.CombinationMerchandiseMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(CombinationMerchandise combinationMerchandise){
		return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.CombinationMerchandiseMapper.update", combinationMerchandise) > 0;
	}
	
	@Override
	public List<CombinationMerchandise> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, CombinationMerchandise> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<CombinationMerchandise> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<CombinationMerchandise> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.CombinationMerchandiseMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.CombinationMerchandiseMapper.count4page",
				param);
		Page<CombinationMerchandise> page = new Page<CombinationMerchandise>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<CombinationMerchandise> page(CombinationMerchandiseQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<CombinationMerchandise> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.CombinationMerchandiseMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.CombinationMerchandiseMapper.count4query",
				param);
		Page<CombinationMerchandise> page = new Page<CombinationMerchandise>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<CombinationMerchandise> listAll(){	
		List<CombinationMerchandise> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.CombinationMerchandiseMapper.listAll");
		return list;
	}
}

