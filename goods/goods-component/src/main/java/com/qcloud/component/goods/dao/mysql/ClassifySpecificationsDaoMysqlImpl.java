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
import com.qcloud.component.goods.dao.ClassifySpecificationsDao;
import com.qcloud.component.goods.model.ClassifySpecifications;
import com.qcloud.component.goods.model.query.ClassifySpecificationsQuery;
		
@Repository
public class ClassifySpecificationsDaoMysqlImpl implements ClassifySpecificationsDao {

	@Resource(name = "sqlOperator-goods")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(ClassifySpecifications classifySpecifications) {
		return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.ClassifySpecificationsMapper.insert", classifySpecifications) == 1;
	}	
	
	@Override
	public ClassifySpecifications get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.ClassifySpecificationsMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.ClassifySpecificationsMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(ClassifySpecifications classifySpecifications){
		return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.ClassifySpecificationsMapper.update", classifySpecifications) > 0;
	}
	
	@Override
	public List<ClassifySpecifications> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, ClassifySpecifications> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<ClassifySpecifications> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<ClassifySpecifications> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.ClassifySpecificationsMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.ClassifySpecificationsMapper.count4page",
				param);
		Page<ClassifySpecifications> page = new Page<ClassifySpecifications>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<ClassifySpecifications> page(ClassifySpecificationsQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<ClassifySpecifications> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.ClassifySpecificationsMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.ClassifySpecificationsMapper.count4query",
				param);
		Page<ClassifySpecifications> page = new Page<ClassifySpecifications>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<ClassifySpecifications> listAll(){	
		List<ClassifySpecifications> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.ClassifySpecificationsMapper.listAll");
		return list;
	}
}

