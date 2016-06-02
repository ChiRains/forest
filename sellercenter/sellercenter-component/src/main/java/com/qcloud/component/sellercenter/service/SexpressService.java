package com.qcloud.component.sellercenter.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.model.Sexpress;
import com.qcloud.component.sellercenter.model.query.SexpressQuery;

public interface SexpressService {
	
	public Long add(Sexpress sexpress);	
	
	public Sexpress get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(Sexpress sexpress);

	public Page<Sexpress> page(SexpressQuery query, int start, int count);
	
	public List<Sexpress> listAll();
	
	public List<Sexpress> listByMerchant(Long merchantId);
}

