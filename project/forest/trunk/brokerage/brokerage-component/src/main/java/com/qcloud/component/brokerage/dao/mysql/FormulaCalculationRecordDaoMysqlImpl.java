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
import com.qcloud.component.brokerage.dao.FormulaCalculationRecordDao;
import com.qcloud.component.brokerage.model.FormulaCalculationRecord;
import com.qcloud.component.brokerage.model.query.FormulaCalculationRecordQuery;
		
@Repository
public class FormulaCalculationRecordDaoMysqlImpl implements FormulaCalculationRecordDao {

	@Resource(name = "sqlOperator-brokerage")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(FormulaCalculationRecord formulaCalculationRecord) {
		return sqlOperator.insert("com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationRecordMapper.insert", formulaCalculationRecord) == 1;
	}	
	
	@Override
	public FormulaCalculationRecord get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationRecordMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationRecordMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(FormulaCalculationRecord formulaCalculationRecord){
		return sqlOperator.update("com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationRecordMapper.update", formulaCalculationRecord) > 0;
	}
	
	@Override
	public List<FormulaCalculationRecord> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, FormulaCalculationRecord> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<FormulaCalculationRecord> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<FormulaCalculationRecord> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationRecordMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationRecordMapper.count4page",
				param);
		Page<FormulaCalculationRecord> page = new Page<FormulaCalculationRecord>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<FormulaCalculationRecord> page(FormulaCalculationRecordQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<FormulaCalculationRecord> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationRecordMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationRecordMapper.count4query",
				param);
		Page<FormulaCalculationRecord> page = new Page<FormulaCalculationRecord>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<FormulaCalculationRecord> listAll(){	
		List<FormulaCalculationRecord> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationRecordMapper.listAll");
		return list;
	}

    @Override
    public FormulaCalculationRecord getLast(Long formulaId) {
        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationRecordMapper.getLast", formulaId);
    }
}

