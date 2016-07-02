package com.qcloud.component.brokerage.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.brokerage.dao.FormulaCalculationResultDao;
import com.qcloud.component.brokerage.model.FormulaCalculationResult;
import com.qcloud.component.brokerage.model.query.FormulaCalculationResultQuery;

@Repository
public class FormulaCalculationResultDaoRedisImpl implements FormulaCalculationResultDao {

	//@Resource(name = "redis-brokerage")
	//private Redis redis;

	@Override
	public boolean add(FormulaCalculationResult formulaCalculationResult) {			
		throw new NotImplementedException();
	}

	@Override
	public FormulaCalculationResult get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(FormulaCalculationResult formulaCalculationResult){
		throw new NotImplementedException();
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
		throw new NotImplementedException();
	}
	
	@Override
	public Page<FormulaCalculationResult> page(FormulaCalculationResultQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<FormulaCalculationResult> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<FormulaCalculationResult> listToAllocation(long formulaId) {
        throw new NotImplementedException();
    }
}

