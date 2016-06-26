package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.MerchandiseAttribute;
import com.qcloud.component.goods.model.query.MerchandiseAttributeQuery;

public interface MerchandiseAttributeService {
	
	public boolean add(MerchandiseAttribute merchandiseAttribute);	
	
	public MerchandiseAttribute get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MerchandiseAttribute merchandiseAttribute);

	public Page<MerchandiseAttribute> page(MerchandiseAttributeQuery query, int start, int count);
	
	public List<MerchandiseAttribute> listAll();
}

