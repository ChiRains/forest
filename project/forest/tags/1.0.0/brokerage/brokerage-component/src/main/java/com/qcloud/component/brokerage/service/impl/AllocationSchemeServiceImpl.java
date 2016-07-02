package com.qcloud.component.brokerage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.dao.AllocationSchemeDao;
import com.qcloud.component.brokerage.model.AllocationScheme;
import com.qcloud.component.brokerage.service.AllocationSchemeService;
import com.qcloud.component.brokerage.model.query.AllocationSchemeQuery;
		
@Service
public class AllocationSchemeServiceImpl implements AllocationSchemeService{
	
	@Autowired
	private AllocationSchemeDao allocationSchemeDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "brokerage_allocation_scheme";

	@Override
	public boolean add(AllocationScheme allocationScheme) {
		long id = autoIdGenerator.get(ID_KEY);
		allocationScheme.setId(id);
		
		return allocationSchemeDao.add(allocationScheme);
	}

	@Override
	public AllocationScheme get(Long id) {	
		return allocationSchemeDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return allocationSchemeDao.delete(id);
	}
	
	@Override
	public boolean update(AllocationScheme allocationScheme) {
		return allocationSchemeDao.update(allocationScheme);
	}
	
	@Override
	public Page<AllocationScheme> page(AllocationSchemeQuery query, int start, int count) {
		return allocationSchemeDao.page(query, start, count);
	}
	
	public List<AllocationScheme> listAll(){
		return allocationSchemeDao.listAll();
	}

    @Override
    public List<AllocationScheme> listByFormula(long formulaId) {
        return allocationSchemeDao.listByFormula(formulaId);
    }
}

