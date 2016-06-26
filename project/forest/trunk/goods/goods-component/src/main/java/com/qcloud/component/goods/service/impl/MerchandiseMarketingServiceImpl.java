package com.qcloud.component.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseMarketingDao;
import com.qcloud.component.goods.model.MerchandiseMarketing;
import com.qcloud.component.goods.service.MerchandiseMarketingService;
import com.qcloud.component.goods.model.query.MerchandiseMarketingQuery;
		
@Service
public class MerchandiseMarketingServiceImpl implements MerchandiseMarketingService{
	
	@Autowired
	private MerchandiseMarketingDao merchandiseMarketingDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "goods_merchandise_marketing";

	@Override
	public boolean add(MerchandiseMarketing merchandiseMarketing) {
		long id = autoIdGenerator.get(ID_KEY);
		merchandiseMarketing.setId(id);
		
		return merchandiseMarketingDao.add(merchandiseMarketing);
	}

	@Override
	public MerchandiseMarketing get(Long id) {	
		return merchandiseMarketingDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchandiseMarketingDao.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseMarketing merchandiseMarketing) {
		return merchandiseMarketingDao.update(merchandiseMarketing);
	}
	
	@Override
	public Page<MerchandiseMarketing> page(MerchandiseMarketingQuery query, int start, int count) {
		return merchandiseMarketingDao.page(query, start, count);
	}
	
	public List<MerchandiseMarketing> listAll(){
		return merchandiseMarketingDao.listAll();
	}
}

