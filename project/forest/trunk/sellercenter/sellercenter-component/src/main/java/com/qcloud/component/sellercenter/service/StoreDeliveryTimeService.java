package com.qcloud.component.sellercenter.service;

import java.util.List;
import com.qcloud.component.sellercenter.model.StoreDeliveryTime;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryTimeQuery;
import com.qcloud.pirates.data.Page;

public interface StoreDeliveryTimeService {
	
	public boolean add(StoreDeliveryTime storeDeliveryTime);	
	
	public StoreDeliveryTime get(Long id);
	
	StoreDeliveryTime getByStore(Long storeId);
	
	public	boolean delete(Long id);
	
	public	boolean update(StoreDeliveryTime storeDeliveryTime);

	public Page<StoreDeliveryTime> page(StoreDeliveryTimeQuery query, int start, int count);
	
	public List<StoreDeliveryTime> listAll();
}

