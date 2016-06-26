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
import com.qcloud.component.goods.dao.MerchandiseDealRecordDao;
import com.qcloud.component.goods.model.MerchandiseDealRecord;
import com.qcloud.component.goods.model.query.MerchandiseDealRecordQuery;
		
@Repository
public class MerchandiseDealRecordDaoMysqlImpl implements MerchandiseDealRecordDao {

	@Resource(name = "sqlOperator-goods")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MerchandiseDealRecord merchandiseDealRecord) {
		return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseDealRecordMapper.insert", merchandiseDealRecord) == 1;
	}	
	
	@Override
	public MerchandiseDealRecord get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseDealRecordMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseDealRecordMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MerchandiseDealRecord merchandiseDealRecord){
		return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseDealRecordMapper.update", merchandiseDealRecord) > 0;
	}
	
	@Override
	public List<MerchandiseDealRecord> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseDealRecord> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseDealRecord> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchandiseDealRecord> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseDealRecordMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseDealRecordMapper.count4page",
				param);
		Page<MerchandiseDealRecord> page = new Page<MerchandiseDealRecord>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MerchandiseDealRecord> page(MerchandiseDealRecordQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MerchandiseDealRecord> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseDealRecordMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseDealRecordMapper.count4query",
				param);
		Page<MerchandiseDealRecord> page = new Page<MerchandiseDealRecord>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MerchandiseDealRecord> listAll(){	
		List<MerchandiseDealRecord> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.MerchandiseDealRecordMapper.listAll");
		return list;
	}
}

