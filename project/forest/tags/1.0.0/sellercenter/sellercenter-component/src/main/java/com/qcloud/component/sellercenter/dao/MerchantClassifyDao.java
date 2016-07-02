package com.qcloud.component.sellercenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.sellercenter.model.MerchantClassify;
import com.qcloud.component.sellercenter.model.query.MerchantClassifyQuery;
		
public interface MerchantClassifyDao extends ISimpleDao<MerchantClassify, Long> {

	public boolean add(MerchantClassify merchantClassify);	
	
	public MerchantClassify get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MerchantClassify merchantClassify);
	
	public List<MerchantClassify> list(List<Long> idList);
	
	public Map<Long, MerchantClassify> map(Set<Long> idSet);
	
	public Page<MerchantClassify> page(MerchantClassifyQuery query, int start, int size);

	public List<MerchantClassify> listAll();
	
	List<MerchantClassify> listByMerchant(Long merchantId);
	
	MerchantClassify get(Long classifyId, Long merchantId);
}
