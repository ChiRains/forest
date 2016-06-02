package com.qcloud.component.sellercenter.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.model.MerchantConsumptionDetail;
import com.qcloud.component.sellercenter.model.query.MerchantConsumptionDetailQuery;

public interface MerchantConsumptionDetailService {
	
	public boolean add(MerchantConsumptionDetail merchantConsumptionDetail);	
	
	public MerchantConsumptionDetail get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MerchantConsumptionDetail merchantConsumptionDetail);

	public Page<MerchantConsumptionDetail> page(MerchantConsumptionDetailQuery query, int start, int count);
	
	public List<MerchantConsumptionDetail> listAll();
}

