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
import com.qcloud.component.goods.dao.UnifiedMerchandiseDao;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;
		
@Repository
public class UnifiedMerchandiseDaoMysqlImpl implements UnifiedMerchandiseDao {

	@Resource(name = "sqlOperator-goods")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(UnifiedMerchandise unifiedMerchandise) {
		return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.insert", unifiedMerchandise) == 1;
	}	
	
	@Override
	public UnifiedMerchandise get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(UnifiedMerchandise unifiedMerchandise){
		return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.update", unifiedMerchandise) > 0;
	}
	
	@Override
	public List<UnifiedMerchandise> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, UnifiedMerchandise> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<UnifiedMerchandise> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<UnifiedMerchandise> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.count4page",
				param);
		Page<UnifiedMerchandise> page = new Page<UnifiedMerchandise>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<UnifiedMerchandise> page(UnifiedMerchandiseQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<UnifiedMerchandise> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.count4query",
				param);
		Page<UnifiedMerchandise> page = new Page<UnifiedMerchandise>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<UnifiedMerchandise> listAll(){	
		List<UnifiedMerchandise> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.UnifiedMerchandiseMapper.listAll");
		return list;
	}
}

