package com.qcloud.component.sellercenter.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.model.MerchantMerchandiseClassify;
import com.qcloud.component.sellercenter.model.query.MerchantMerchandiseClassifyQuery;

public interface MerchantMerchandiseClassifyService {
	
	public boolean add(MerchantMerchandiseClassify merchantMerchandiseClassify);	
	
	public MerchantMerchandiseClassify get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MerchantMerchandiseClassify merchantMerchandiseClassify);

	public Page<MerchantMerchandiseClassify> page(MerchantMerchandiseClassifyQuery query, int start, int count);
	
	public List<MerchantMerchandiseClassify> listAll();

	public List<MerchantMerchandiseClassify> listByMerchantId(Long merchantId);
	public boolean  deleteByMerchantId(Long merchantId);
}