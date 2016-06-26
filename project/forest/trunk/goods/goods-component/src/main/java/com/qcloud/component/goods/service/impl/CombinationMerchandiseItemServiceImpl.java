package com.qcloud.component.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.CombinationMerchandiseItemDao;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.service.CombinationMerchandiseItemService;
import com.qcloud.component.goods.model.query.CombinationMerchandiseItemQuery;
		
@Service
public class CombinationMerchandiseItemServiceImpl implements CombinationMerchandiseItemService{
	
	@Autowired
	private CombinationMerchandiseItemDao combinationMerchandiseItemDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "goods_combination_merchandise_item";

	@Override
	public boolean add(CombinationMerchandiseItem combinationMerchandiseItem) {
		long id = autoIdGenerator.get(ID_KEY);
		combinationMerchandiseItem.setId(id);
		
		return combinationMerchandiseItemDao.add(combinationMerchandiseItem);
	}

	@Override
	public CombinationMerchandiseItem get(Long id) {	
		return combinationMerchandiseItemDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return combinationMerchandiseItemDao.delete(id);
	}
	
	@Override
	public boolean update(CombinationMerchandiseItem combinationMerchandiseItem) {
		return combinationMerchandiseItemDao.update(combinationMerchandiseItem);
	}
	
	@Override
	public Page<CombinationMerchandiseItem> page(CombinationMerchandiseItemQuery query, int start, int count) {
		return combinationMerchandiseItemDao.page(query, start, count);
	}
	
	public List<CombinationMerchandiseItem> listAll(){
		return combinationMerchandiseItemDao.listAll();
	}
}

