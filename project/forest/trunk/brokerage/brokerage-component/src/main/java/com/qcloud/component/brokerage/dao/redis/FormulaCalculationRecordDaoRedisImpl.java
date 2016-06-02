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
import com.qcloud.component.brokerage.dao.FormulaCalculationRecordDao;
import com.qcloud.component.brokerage.model.FormulaCalculationRecord;
import com.qcloud.component.brokerage.model.query.FormulaCalculationRecordQuery;

@Repository
public class FormulaCalculationRecordDaoRedisImpl implements FormulaCalculationRecordDao {

	//@Resource(name = "redis-brokerage")
	//private Redis redis;

	@Override
	public boolean add(FormulaCalculationRecord formulaCalculationRecord) {			
		throw new NotImplementedException();
	}

	@Override
	public FormulaCalculationRecord get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(FormulaCalculationRecord formulaCalculationRecord){
		throw new NotImplementedException();
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
		throw new NotImplementedException();
	}
	
	@Override
	public Page<FormulaCalculationRecord> page(FormulaCalculationRecordQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<FormulaCalculationRecord> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public FormulaCalculationRecord getLast(Long formulaId) {
        throw new NotImplementedException();
    }
}

