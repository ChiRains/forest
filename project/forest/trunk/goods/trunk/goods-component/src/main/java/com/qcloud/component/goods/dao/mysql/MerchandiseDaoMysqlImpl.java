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
import com.qcloud.component.goods.dao.MerchandiseDao;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.query.MerchandiseQuery;
		
@Repository
public class MerchandiseDaoMysqlImpl implements MerchandiseDao {

	@Resource(name = "sqlOperator-goods")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(Merchandise merchandise) {
		return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMapper.insert", merchandise) == 1;
	}	
	
	@Override
	public Merchandise get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(Merchandise merchandise){
		return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMapper.update", merchandise) > 0;
	}
	
	@Override
	public List<Merchandise> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, Merchandise> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<Merchandise> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<Merchandise> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMapper.count4page",
				param);
		Page<Merchandise> page = new Page<Merchandise>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<Merchandise> page(MerchandiseQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<Merchandise> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMapper.count4query",
				param);
		Page<Merchandise> page = new Page<Merchandise>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<Merchandise> listAll(){	
		List<Merchandise> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseMapper.listAll");
		return list;
	}
}

