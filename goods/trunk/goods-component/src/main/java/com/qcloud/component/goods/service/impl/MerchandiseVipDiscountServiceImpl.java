package com.qcloud.component.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseVipDiscountDao;
import com.qcloud.component.goods.model.MerchandiseVipDiscount;
import com.qcloud.component.goods.service.MerchandiseVipDiscountService;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountQuery;
		
@Service
public class MerchandiseVipDiscountServiceImpl implements MerchandiseVipDiscountService{
	
	@Autowired
	private MerchandiseVipDiscountDao merchandiseVipDiscountDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "goods_merchandise_vip_discount";

	@Override
	public boolean add(MerchandiseVipDiscount merchandiseVipDiscount) {
		long id = autoIdGenerator.get(ID_KEY);
		merchandiseVipDiscount.setId(id);
		
		return merchandiseVipDiscountDao.add(merchandiseVipDiscount);
	}

	@Override
	public MerchandiseVipDiscount get(Long id) {	
		return merchandiseVipDiscountDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchandiseVipDiscountDao.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseVipDiscount merchandiseVipDiscount) {
		return merchandiseVipDiscountDao.update(merchandiseVipDiscount);
	}
	
	@Override
	public Page<MerchandiseVipDiscount> page(MerchandiseVipDiscountQuery query, int start, int count) {
		return merchandiseVipDiscountDao.page(query, start, count);
	}
	
	public List<MerchandiseVipDiscount> listAll(){
		return merchandiseVipDiscountDao.listAll();
	}
}

