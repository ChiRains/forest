package com.qcloud.component.goods.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.goods.model.MerchandiseImage;
import com.qcloud.component.goods.model.query.MerchandiseImageQuery;
		
public interface MerchandiseImageDao extends ISimpleDao<MerchandiseImage, Long> {

	public boolean add(MerchandiseImage merchandiseImage);	
	
	public MerchandiseImage get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MerchandiseImage merchandiseImage);
	
	public List<MerchandiseImage> list(List<Long> idList);
	
	public Map<Long, MerchandiseImage> map(Set<Long> idSet);
	
	public Page<MerchandiseImage> page(MerchandiseImageQuery query, int start, int size);

	public List<MerchandiseImage> listAll();
	
}
