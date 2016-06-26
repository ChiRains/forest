package com.qcloud.component.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseDao;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.goods.model.query.MerchandiseQuery;
		
@Service
public class MerchandiseServiceImpl implements MerchandiseService{
	
	@Autowired
	private MerchandiseDao merchandiseDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "goods_merchandise";

	@Override
	public boolean add(Merchandise merchandise) {
		long id = autoIdGenerator.get(ID_KEY);
		merchandise.setId(id);
		
		return merchandiseDao.add(merchandise);
	}

	@Override
	public Merchandise get(Long id) {	
		return merchandiseDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchandiseDao.delete(id);
	}
	
	@Override
	public boolean update(Merchandise merchandise) {
		return merchandiseDao.update(merchandise);
	}
	
	@Override
	public Page<Merchandise> page(MerchandiseQuery query, int start, int count) {
		return merchandiseDao.page(query, start, count);
	}
	
	public List<Merchandise> listAll(){
		return merchandiseDao.listAll();
	}
}

