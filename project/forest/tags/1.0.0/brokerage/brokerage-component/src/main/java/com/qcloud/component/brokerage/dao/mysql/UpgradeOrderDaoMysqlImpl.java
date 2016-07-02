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
import com.qcloud.component.brokerage.dao.UpgradeOrderDao;
import com.qcloud.component.brokerage.model.UpgradeOrder;
import com.qcloud.component.brokerage.model.query.UpgradeOrderQuery;
		
@Repository
public class UpgradeOrderDaoMysqlImpl implements UpgradeOrderDao {

	@Resource(name = "sqlOperator-brokerage")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(UpgradeOrder upgradeOrder) {
		return sqlOperator.insert("com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeOrderMapper.insert", upgradeOrder) == 1;
	}	
	
	@Override
	public UpgradeOrder get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeOrderMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeOrderMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(UpgradeOrder upgradeOrder){
		return sqlOperator.update("com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeOrderMapper.update", upgradeOrder) > 0;
	}
	
	@Override
	public List<UpgradeOrder> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, UpgradeOrder> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<UpgradeOrder> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<UpgradeOrder> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeOrderMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeOrderMapper.count4page",
				param);
		Page<UpgradeOrder> page = new Page<UpgradeOrder>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<UpgradeOrder> page(UpgradeOrderQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<UpgradeOrder> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeOrderMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeOrderMapper.count4query",
				param);
		Page<UpgradeOrder> page = new Page<UpgradeOrder>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<UpgradeOrder> listAll(){	
		List<UpgradeOrder> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.UpgradeOrderMapper.listAll");
		return list;
	}
}

