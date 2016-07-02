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
import com.qcloud.component.brokerage.dao.DataPoolDao;
import com.qcloud.component.brokerage.model.DataPool;
import com.qcloud.component.brokerage.model.FormulaSqlResult;
import com.qcloud.component.brokerage.model.query.DataPoolQuery;
		
@Repository
public class DataPoolDaoMysqlImpl implements DataPoolDao {

	@Resource(name = "sqlOperator-brokerage")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(DataPool dataPool) {
		return sqlOperator.insert("com.qcloud.component.brokerage.dao.mysql.mapper.DataPoolMapper.insert", dataPool) == 1;
	}	
	
	@Override
	public DataPool get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.DataPoolMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.brokerage.dao.mysql.mapper.DataPoolMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(DataPool dataPool){
		return sqlOperator.update("com.qcloud.component.brokerage.dao.mysql.mapper.DataPoolMapper.update", dataPool) > 0;
	}
	
	@Override
	public List<DataPool> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, DataPool> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<DataPool> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<DataPool> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.DataPoolMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.brokerage.dao.mysql.mapper.DataPoolMapper.count4page",
				param);
		Page<DataPool> page = new Page<DataPool>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<DataPool> page(DataPoolQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<DataPool> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.DataPoolMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.brokerage.dao.mysql.mapper.DataPoolMapper.count4query",
				param);
		Page<DataPool> page = new Page<DataPool>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<DataPool> listAll(){	
		List<DataPool> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.DataPoolMapper.listAll");
		return list;
	}

    @Override
    public List<FormulaSqlResult> query(String sql) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("sql", sql);
        List<FormulaSqlResult> list = sqlOperator.selectList(
                "com.qcloud.component.brokerage.dao.mysql.mapper.DataPoolMapper.query", param);
        return list;
    }
}

