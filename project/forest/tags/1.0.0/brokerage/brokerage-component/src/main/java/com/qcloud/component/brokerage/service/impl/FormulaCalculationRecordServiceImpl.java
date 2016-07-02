package com.qcloud.component.brokerage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.dao.FormulaCalculationRecordDao;
import com.qcloud.component.brokerage.model.FormulaCalculationRecord;
import com.qcloud.component.brokerage.service.FormulaCalculationRecordService;
import com.qcloud.component.brokerage.model.query.FormulaCalculationRecordQuery;
		
@Service
public class FormulaCalculationRecordServiceImpl implements FormulaCalculationRecordService{
	
	@Autowired
	private FormulaCalculationRecordDao formulaCalculationRecordDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "brokerage_formula_calculation_record";

	@Override
	public boolean add(FormulaCalculationRecord formulaCalculationRecord) {
		long id = autoIdGenerator.get(ID_KEY);
		formulaCalculationRecord.setId(id);
		
		return formulaCalculationRecordDao.add(formulaCalculationRecord);
	}

	@Override
	public FormulaCalculationRecord get(Long id) {	
		return formulaCalculationRecordDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return formulaCalculationRecordDao.delete(id);
	}
	
	@Override
	public boolean update(FormulaCalculationRecord formulaCalculationRecord) {
		return formulaCalculationRecordDao.update(formulaCalculationRecord);
	}
	
	@Override
	public Page<FormulaCalculationRecord> page(FormulaCalculationRecordQuery query, int start, int count) {
		return formulaCalculationRecordDao.page(query, start, count);
	}
	
	public List<FormulaCalculationRecord> listAll(){
		return formulaCalculationRecordDao.listAll();
	}

    @Override
    public FormulaCalculationRecord getLast(Long formulaId) {
        return formulaCalculationRecordDao.getLast(formulaId);
    }
}

