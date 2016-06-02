package com.qcloud.component.commoditycenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.commoditycenter.dao.UnifiedMerchandiseDao;
import com.qcloud.component.commoditycenter.model.UnifiedMerchandise;
import com.qcloud.component.commoditycenter.service.UnifiedMerchandiseService;
import com.qcloud.component.commoditycenter.model.query.UnifiedMerchandiseQuery;
		
@Service
public class UnifiedMerchandiseServiceImpl implements UnifiedMerchandiseService{
	
	@Autowired
	private UnifiedMerchandiseDao unifiedMerchandiseDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "commoditycenter_unified_merchandise";

	@Override
	public boolean add(UnifiedMerchandise unifiedMerchandise) {
		long id = autoIdGenerator.get(ID_KEY);
		unifiedMerchandise.setId(id);
		
		return unifiedMerchandiseDao.add(unifiedMerchandise);
	}

	@Override
	public UnifiedMerchandise get(Long id) {	
		return unifiedMerchandiseDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return unifiedMerchandiseDao.delete(id);
	}
	
	@Override
	public boolean update(UnifiedMerchandise unifiedMerchandise) {
		return unifiedMerchandiseDao.update(unifiedMerchandise);
	}
	
	@Override
	public Page<UnifiedMerchandise> page(UnifiedMerchandiseQuery query, int start, int count) {
		return unifiedMerchandiseDao.page(query, start, count);
	}
	
	public List<UnifiedMerchandise> listAll(){
		return unifiedMerchandiseDao.listAll();
	}

    @Override
    public Long addThenRetrunId(UnifiedMerchandise unifiedMerchandise) {
        long id = autoIdGenerator.get(ID_KEY);
        unifiedMerchandise.setId(id);
        
        return unifiedMerchandiseDao.add(unifiedMerchandise)?id:-1L;
    }
}
