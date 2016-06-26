package com.qcloud.component.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseVipDiscountDateDao;
import com.qcloud.component.goods.model.MerchandiseVipDiscountDate;
import com.qcloud.component.goods.service.MerchandiseVipDiscountDateService;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountDateQuery;
		
@Service
public class MerchandiseVipDiscountDateServiceImpl implements MerchandiseVipDiscountDateService{
	
	@Autowired
	private MerchandiseVipDiscountDateDao merchandiseVipDiscountDateDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "goods_merchandise_vip_discount_date";

	@Override
	public boolean add(MerchandiseVipDiscountDate merchandiseVipDiscountDate) {
		long id = autoIdGenerator.get(ID_KEY);
		merchandiseVipDiscountDate.setId(id);
		
		return merchandiseVipDiscountDateDao.add(merchandiseVipDiscountDate);
	}

	@Override
	public MerchandiseVipDiscountDate get(Long id) {	
		return merchandiseVipDiscountDateDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchandiseVipDiscountDateDao.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseVipDiscountDate merchandiseVipDiscountDate) {
		return merchandiseVipDiscountDateDao.update(merchandiseVipDiscountDate);
	}
	
	@Override
	public Page<MerchandiseVipDiscountDate> page(MerchandiseVipDiscountDateQuery query, int start, int count) {
		return merchandiseVipDiscountDateDao.page(query, start, count);
	}
	
	public List<MerchandiseVipDiscountDate> listAll(){
		return merchandiseVipDiscountDateDao.listAll();
	}
}

