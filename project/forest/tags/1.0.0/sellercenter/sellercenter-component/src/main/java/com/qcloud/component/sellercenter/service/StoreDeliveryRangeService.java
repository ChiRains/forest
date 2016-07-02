package com.qcloud.component.sellercenter.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.model.StoreDeliveryRange;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryRangeQuery;

public interface StoreDeliveryRangeService {
	
	public boolean add(StoreDeliveryRange storeDeliveryRange);	
	
	public StoreDeliveryRange get(Long id);
	
	StoreDeliveryRange getByStore(Long storeId);
	
	public	boolean delete(Long id);
	
	public	boolean update(StoreDeliveryRange storeDeliveryRange);

	public Page<StoreDeliveryRange> page(StoreDeliveryRangeQuery query, int start, int count);
	
	public List<StoreDeliveryRange> listAll();
}

