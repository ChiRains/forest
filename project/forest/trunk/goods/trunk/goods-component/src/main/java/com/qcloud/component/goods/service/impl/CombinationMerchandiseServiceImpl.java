package com.qcloud.component.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.CombinationMerchandiseDao;
import com.qcloud.component.goods.model.CombinationMerchandise;
import com.qcloud.component.goods.service.CombinationMerchandiseService;
import com.qcloud.component.goods.model.query.CombinationMerchandiseQuery;
		
@Service
public class CombinationMerchandiseServiceImpl implements CombinationMerchandiseService{
	
	@Autowired
	private CombinationMerchandiseDao combinationMerchandiseDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "goods_combination_merchandise";

	@Override
	public boolean add(CombinationMerchandise combinationMerchandise) {
		long id = autoIdGenerator.get(ID_KEY);
		combinationMerchandise.setId(id);
		
		return combinationMerchandiseDao.add(combinationMerchandise);
	}

	@Override
	public CombinationMerchandise get(Long id) {	
		return combinationMerchandiseDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return combinationMerchandiseDao.delete(id);
	}
	
	@Override
	public boolean update(CombinationMerchandise combinationMerchandise) {
		return combinationMerchandiseDao.update(combinationMerchandise);
	}
	
	@Override
	public Page<CombinationMerchandise> page(CombinationMerchandiseQuery query, int start, int count) {
		return combinationMerchandiseDao.page(query, start, count);
	}
	
	public List<CombinationMerchandise> listAll(){
		return combinationMerchandiseDao.listAll();
	}
}

