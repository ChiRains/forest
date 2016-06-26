package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.MerchandiseMarketing;
import com.qcloud.component.goods.model.query.MerchandiseMarketingQuery;

public interface MerchandiseMarketingService {
	
	public boolean add(MerchandiseMarketing merchandiseMarketing);	
	
	public MerchandiseMarketing get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MerchandiseMarketing merchandiseMarketing);

	public Page<MerchandiseMarketing> page(MerchandiseMarketingQuery query, int start, int count);
	
	public List<MerchandiseMarketing> listAll();
}

