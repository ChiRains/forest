package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsQuery;

public interface MerchandiseSpecificationsService {
	
	public boolean add(MerchandiseSpecifications merchandiseSpecifications);	
	
	public MerchandiseSpecifications get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MerchandiseSpecifications merchandiseSpecifications);

	public Page<MerchandiseSpecifications> page(MerchandiseSpecificationsQuery query, int start, int count);
	
	public List<MerchandiseSpecifications> listAll();
}

