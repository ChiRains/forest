package com.qcloud.component.marketing.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.marketing.dao.FullReducesDao;
import com.qcloud.component.marketing.model.FullReduces;
import com.qcloud.component.marketing.model.query.FullReducesQuery;
		
@Repository
public class FullReducesDaoMysqlImpl implements FullReducesDao {

	@Resource(name = "sqlOperator-marketing")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(FullReduces fullReduces) {
		return sqlOperator.insert("com.qcloud.component.marketing.dao.mysql.mapper.FullReducesMapper.insert", fullReduces) == 1;
	}	
	
	@Override
	public FullReduces get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.marketing.dao.mysql.mapper.FullReducesMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.marketing.dao.mysql.mapper.FullReducesMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(FullReduces fullReduces){
		return sqlOperator.update("com.qcloud.component.marketing.dao.mysql.mapper.FullReducesMapper.update", fullReduces) > 0;
	}
	
	@Override
	public List<FullReduces> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, FullReduces> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<FullReduces> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<FullReduces> list = sqlOperator.selectList(
				"com.qcloud.component.marketing.dao.mysql.mapper.FullReducesMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.marketing.dao.mysql.mapper.FullReducesMapper.count4page",
				param);
		Page<FullReduces> page = new Page<FullReduces>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<FullReduces> page(FullReducesQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<FullReduces> list = sqlOperator.selectList(
				"com.qcloud.component.marketing.dao.mysql.mapper.FullReducesMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.marketing.dao.mysql.mapper.FullReducesMapper.count4query",
				param);
		Page<FullReduces> page = new Page<FullReduces>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<FullReduces> listAll(){	
		List<FullReduces> list = sqlOperator.selectList(
				"com.qcloud.component.marketing.dao.mysql.mapper.FullReducesMapper.listAll");
		return list;
	}
}

