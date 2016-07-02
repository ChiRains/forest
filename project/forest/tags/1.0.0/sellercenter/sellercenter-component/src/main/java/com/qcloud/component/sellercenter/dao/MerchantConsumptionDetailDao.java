package com.qcloud.component.sellercenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.sellercenter.model.MerchantConsumptionDetail;
import com.qcloud.component.sellercenter.model.query.MerchantConsumptionDetailQuery;
		
public interface MerchantConsumptionDetailDao extends ISimpleDao<MerchantConsumptionDetail, Long> {

	public boolean add(MerchantConsumptionDetail merchantConsumptionDetail);	
	
	public MerchantConsumptionDetail get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MerchantConsumptionDetail merchantConsumptionDetail);
	
	public List<MerchantConsumptionDetail> list(List<Long> idList);
	
	public Map<Long, MerchantConsumptionDetail> map(Set<Long> idSet);
	
	public Page<MerchantConsumptionDetail> page(MerchantConsumptionDetailQuery query, int start, int size);

	public List<MerchantConsumptionDetail> listAll();
	
}
