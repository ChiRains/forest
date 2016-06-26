package com.qcloud.component.sellercenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.sellercenter.model.Sexpress;
import com.qcloud.component.sellercenter.model.query.SexpressQuery;
		
public interface SexpressDao extends ISimpleDao<Sexpress, Long> {

	public boolean add(Sexpress sexpress);	
	
	public Sexpress get(Long id);

	public boolean delete(Long id);
	
	public boolean update(Sexpress sexpress);
	
	public List<Sexpress> list(List<Long> idList);
	
	public Map<Long, Sexpress> map(Set<Long> idSet);
	
	public Page<Sexpress> page(SexpressQuery query, int start, int size);

	public List<Sexpress> listAll();
	
	public List<Sexpress> listByMerchant(Long merchantId);
	
	public Sexpress getByCode(String expressCode,long merchantId);
	
}
