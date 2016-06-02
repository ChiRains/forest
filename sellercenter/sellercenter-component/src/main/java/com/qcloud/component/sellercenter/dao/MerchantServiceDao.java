package com.qcloud.component.sellercenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.sellercenter.model.MerchantService;
import com.qcloud.component.sellercenter.model.query.MerchantServiceQuery;
		
public interface MerchantServiceDao extends ISimpleDao<MerchantService, Long> {

	public boolean add(MerchantService merchantService);	
	
	public MerchantService get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MerchantService merchantService);
	
	public List<MerchantService> list(List<Long> idList);
	
	public Map<Long, MerchantService> map(Set<Long> idSet);
	
	public Page<MerchantService> page(MerchantServiceQuery query, int start, int size);

	public List<MerchantService> listAll();
	
}
