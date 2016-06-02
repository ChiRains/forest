package com.qcloud.component.brokerage.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.brokerage.dao.DistributionBrokerageDao;
import com.qcloud.component.brokerage.model.DistributionBrokerage;
import com.qcloud.component.brokerage.model.query.DistributionBrokerageQuery;
		
@Repository
public class DistributionBrokerageDaoMysqlImpl implements DistributionBrokerageDao {

	@Resource(name = "sqlOperator-brokerage")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(DistributionBrokerage distributionBrokerage) {
		return sqlOperator.insert("com.qcloud.component.brokerage.dao.mysql.mapper.DistributionBrokerageMapper.insert", distributionBrokerage) == 1;
	}	
	
	@Override
	public DistributionBrokerage get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.DistributionBrokerageMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.brokerage.dao.mysql.mapper.DistributionBrokerageMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(DistributionBrokerage distributionBrokerage){
		return sqlOperator.update("com.qcloud.component.brokerage.dao.mysql.mapper.DistributionBrokerageMapper.update", distributionBrokerage) > 0;
	}
	
	@Override
	public List<DistributionBrokerage> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, DistributionBrokerage> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<DistributionBrokerage> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<DistributionBrokerage> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.DistributionBrokerageMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.brokerage.dao.mysql.mapper.DistributionBrokerageMapper.count4page",
				param);
		Page<DistributionBrokerage> page = new Page<DistributionBrokerage>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<DistributionBrokerage> page(DistributionBrokerageQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);
		param.put("startDate", query.getStartDate());
		param.put("endDate", query.getEndDate());
		param.put("state", query.getState());
		param.put("type", query.getType());

		List<DistributionBrokerage> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.DistributionBrokerageMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.brokerage.dao.mysql.mapper.DistributionBrokerageMapper.count4query",
				param);
		Page<DistributionBrokerage> page = new Page<DistributionBrokerage>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<DistributionBrokerage> listAll(){	
		List<DistributionBrokerage> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.DistributionBrokerageMapper.listAll");
		return list;
	}
}

