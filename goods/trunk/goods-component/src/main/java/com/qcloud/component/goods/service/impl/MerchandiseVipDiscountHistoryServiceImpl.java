package com.qcloud.component.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseVipDiscountHistoryDao;
import com.qcloud.component.goods.model.MerchandiseVipDiscountHistory;
import com.qcloud.component.goods.service.MerchandiseVipDiscountHistoryService;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountHistoryQuery;
		
@Service
public class MerchandiseVipDiscountHistoryServiceImpl implements MerchandiseVipDiscountHistoryService{
	
	@Autowired
	private MerchandiseVipDiscountHistoryDao merchandiseVipDiscountHistoryDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "goods_merchandise_vip_discount_history";

	@Override
	public boolean add(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory) {
		long id = autoIdGenerator.get(ID_KEY);
		merchandiseVipDiscountHistory.setId(id);
		
		return merchandiseVipDiscountHistoryDao.add(merchandiseVipDiscountHistory);
	}

	@Override
	public MerchandiseVipDiscountHistory get(Long id) {	
		return merchandiseVipDiscountHistoryDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchandiseVipDiscountHistoryDao.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory) {
		return merchandiseVipDiscountHistoryDao.update(merchandiseVipDiscountHistory);
	}
	
	@Override
	public Page<MerchandiseVipDiscountHistory> page(MerchandiseVipDiscountHistoryQuery query, int start, int count) {
		return merchandiseVipDiscountHistoryDao.page(query, start, count);
	}
	
	public List<MerchandiseVipDiscountHistory> listAll(){
		return merchandiseVipDiscountHistoryDao.listAll();
	}
}

