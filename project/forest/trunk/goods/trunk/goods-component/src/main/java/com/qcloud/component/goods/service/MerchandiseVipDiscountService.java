package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.MerchandiseVipDiscount;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountQuery;

public interface MerchandiseVipDiscountService {
	
	public boolean add(MerchandiseVipDiscount merchandiseVipDiscount);	
	
	public MerchandiseVipDiscount get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MerchandiseVipDiscount merchandiseVipDiscount);

	public Page<MerchandiseVipDiscount> page(MerchandiseVipDiscountQuery query, int start, int count);
	
	public List<MerchandiseVipDiscount> listAll();
}

