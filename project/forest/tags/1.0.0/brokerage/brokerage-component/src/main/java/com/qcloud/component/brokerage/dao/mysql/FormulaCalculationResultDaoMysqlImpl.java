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
import com.qcloud.component.brokerage.dao.FormulaCalculationResultDao;
import com.qcloud.component.brokerage.model.FormulaCalculationResult;
import com.qcloud.component.brokerage.model.query.FormulaCalculationResultQuery;
		
@Repository
public class FormulaCalculationResultDaoMysqlImpl implements FormulaCalculationResultDao {

	@Resource(name = "sqlOperator-brokerage")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(FormulaCalculationResult formulaCalculationResult) {
		return sqlOperator.insert("com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationResultMapper.insert", formulaCalculationResult) == 1;
	}	
	
	@Override
	public FormulaCalculationResult get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationResultMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationResultMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(FormulaCalculationResult formulaCalculationResult){
		return sqlOperator.update("com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationResultMapper.update", formulaCalculationResult) > 0;
	}
	
	@Override
	public List<FormulaCalculationResult> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, FormulaCalculationResult> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<FormulaCalculationResult> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<FormulaCalculationResult> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationResultMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationResultMapper.count4page",
				param);
		Page<FormulaCalculationResult> page = new Page<FormulaCalculationResult>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<FormulaCalculationResult> page(FormulaCalculationResultQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<FormulaCalculationResult> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationResultMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationResultMapper.count4query",
				param);
		Page<FormulaCalculationResult> page = new Page<FormulaCalculationResult>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<FormulaCalculationResult> listAll(){	
		List<FormulaCalculationResult> list = sqlOperator.selectList(
				"com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationResultMapper.listAll");
		return list;
	}

    @Override
    public List<FormulaCalculationResult> listToAllocation(long formulaId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("formulaId", formulaId);
        List<FormulaCalculationResult> list = sqlOperator.selectList(
                "com.qcloud.component.brokerage.dao.mysql.mapper.FormulaCalculationResultMapper.listToAllocation", param);
        return list;
    }
}

