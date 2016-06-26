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
import com.qcloud.component.goods.dao.MerchandiseSpecificationsDao;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsQuery;
		
@Repository
public class MerchandiseSpecificationsDaoMysqlImpl implements MerchandiseSpecificationsDao {

	@Resource(name = "sqlOperator-goods")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MerchandiseSpecifications merchandiseSpecifications) {
		return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.insert", merchandiseSpecifications) == 1;
	}	
	
	@Override
	public MerchandiseSpecifications get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MerchandiseSpecifications merchandiseSpecifications){
		return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.update", merchandiseSpecifications) > 0;
	}
	
	@Override
	public List<MerchandiseSpecifications> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseSpecifications> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseSpecifications> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchandiseSpecifications> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.count4page",
				param);
		Page<MerchandiseSpecifications> page = new Page<MerchandiseSpecifications>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MerchandiseSpecifications> page(MerchandiseSpecificationsQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchandiseSpecifications> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.count4query",
				param);
		Page<MerchandiseSpecifications> page = new Page<MerchandiseSpecifications>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MerchandiseSpecifications> listAll(){	
		List<MerchandiseSpecifications> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseSpecificationsMapper.listAll");
		return list;
	}
}

