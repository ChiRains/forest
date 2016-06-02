package com.qcloud.component.sellercenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.MerchantWealthDetailDao;
import com.qcloud.component.sellercenter.model.MerchantWealthDetail;
import com.qcloud.component.sellercenter.service.MerchantWealthDetailService;
import com.qcloud.component.sellercenter.model.query.MerchantWealthDetailQuery;
		
@Service
public class MerchantWealthDetailServiceImpl implements MerchantWealthDetailService{
	
	@Autowired
	private MerchantWealthDetailDao merchantWealthDetailDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "sellercenter_merchant_wealth_detail";

	@Override
	public boolean add(MerchantWealthDetail merchantWealthDetail) {
		long id = autoIdGenerator.get(ID_KEY);
		merchantWealthDetail.setId(id);
		
		return merchantWealthDetailDao.add(merchantWealthDetail);
	}

	@Override
	public MerchantWealthDetail get(Long id) {	
		return merchantWealthDetailDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchantWealthDetailDao.delete(id);
	}
	
	@Override
	public boolean update(MerchantWealthDetail merchantWealthDetail) {
		return merchantWealthDetailDao.update(merchantWealthDetail);
	}
	
	@Override
	public Page<MerchantWealthDetail> page(MerchantWealthDetailQuery query, int start, int count) {
		return merchantWealthDetailDao.page(query, start, count);
	}
	
	public List<MerchantWealthDetail> listAll(){
		return merchantWealthDetailDao.listAll();
	}
}

