package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.MerchandiseVipDiscountDate;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountDateQuery;

public interface MerchandiseVipDiscountDateService {
	
	public boolean add(MerchandiseVipDiscountDate merchandiseVipDiscountDate);	
	
	public MerchandiseVipDiscountDate get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MerchandiseVipDiscountDate merchandiseVipDiscountDate);

	public Page<MerchandiseVipDiscountDate> page(MerchandiseVipDiscountDateQuery query, int start, int count);
	
	public List<MerchandiseVipDiscountDate> listAll();
}

