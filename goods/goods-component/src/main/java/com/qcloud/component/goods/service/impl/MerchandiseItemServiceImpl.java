package com.qcloud.component.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseItemDao;
import com.qcloud.component.goods.model.MerchandiseItem;
import com.qcloud.component.goods.service.MerchandiseItemService;
import com.qcloud.component.goods.model.query.MerchandiseItemQuery;
		
@Service
public class MerchandiseItemServiceImpl implements MerchandiseItemService{
	
	@Autowired
	private MerchandiseItemDao merchandiseItemDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "goods_merchandise_item";

	@Override
	public boolean add(MerchandiseItem merchandiseItem) {
		long id = autoIdGenerator.get(ID_KEY);
		merchandiseItem.setId(id);
		
		return merchandiseItemDao.add(merchandiseItem);
	}

	@Override
	public MerchandiseItem get(Long id) {	
		return merchandiseItemDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchandiseItemDao.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseItem merchandiseItem) {
		return merchandiseItemDao.update(merchandiseItem);
	}
	
	@Override
	public Page<MerchandiseItem> page(MerchandiseItemQuery query, int start, int count) {
		return merchandiseItemDao.page(query, start, count);
	}
	
	public List<MerchandiseItem> listAll(){
		return merchandiseItemDao.listAll();
	}
}

