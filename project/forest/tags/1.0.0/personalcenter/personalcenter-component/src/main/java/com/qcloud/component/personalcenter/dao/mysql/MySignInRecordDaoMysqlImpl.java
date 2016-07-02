package com.qcloud.component.personalcenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.personalcenter.dao.MySignInRecordDao;
import com.qcloud.component.personalcenter.model.MySignInRecord;
import com.qcloud.component.personalcenter.model.query.MySignInRecordQuery;
import com.qcloud.component.personalcenter.service.MySignInRecordService;
		
@Repository
public class MySignInRecordDaoMysqlImpl implements MySignInRecordDao {

	@Resource(name = "sqlOperator-personalcenter")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MySignInRecord mySignInRecord) {
		return sqlOperator.insert("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInRecordMapper.insert", mySignInRecord) == 1;
	}	
	
	@Override
	public MySignInRecord get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInRecordMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInRecordMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MySignInRecord mySignInRecord){
		return sqlOperator.update("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInRecordMapper.update", mySignInRecord) > 0;
	}
	
	@Override
	public List<MySignInRecord> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MySignInRecord> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MySignInRecord> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MySignInRecord> list = sqlOperator.selectList(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInRecordMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInRecordMapper.count4page",
				param);
		Page<MySignInRecord> page = new Page<MySignInRecord>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MySignInRecord> page(MySignInRecordQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MySignInRecord> list = sqlOperator.selectList(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInRecordMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInRecordMapper.count4query",
				param);
		Page<MySignInRecord> page = new Page<MySignInRecord>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MySignInRecord> listAll(){	
		List<MySignInRecord> list = sqlOperator.selectList(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInRecordMapper.listAll");
		return list;
	}

	@Override
	public MySignInRecord listByUserId(Long userId) {
		return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInRecordMapper.listByUserId", userId);
	}

}

