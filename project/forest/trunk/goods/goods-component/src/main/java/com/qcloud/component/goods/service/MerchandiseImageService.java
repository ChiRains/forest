package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.MerchandiseImage;
import com.qcloud.component.goods.model.query.MerchandiseImageQuery;

public interface MerchandiseImageService {
	
	public boolean add(MerchandiseImage merchandiseImage);	
	
	public MerchandiseImage get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MerchandiseImage merchandiseImage);

	public Page<MerchandiseImage> page(MerchandiseImageQuery query, int start, int count);
	
	public List<MerchandiseImage> listAll();
}

