package com.qcloud.component.sellercenter.service;

import java.util.List;
import com.qcloud.component.sellercenter.model.MerchantClassify;
import com.qcloud.component.sellercenter.model.query.MerchantClassifyQuery;
import com.qcloud.pirates.data.Page;

public interface MerchantClassifyService {
	
	public boolean add(MerchantClassify merchantClassify);	
	
	public MerchantClassify get(Long id);
	
	public MerchantClassify get(Long classifyId, Long merchantId);
	
	public	boolean delete(Long id);
	
	public	boolean update(MerchantClassify merchantClassify);

	public Page<MerchantClassify> page(MerchantClassifyQuery query, int start, int count);
	
	public List<MerchantClassify> listAll();
	
	List<MerchantClassify> listByMerchant(Long merchantId);
}

