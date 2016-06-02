package com.qcloud.component.sellercenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.MerchantPayDao;
import com.qcloud.component.sellercenter.model.MerchantPay;
import com.qcloud.component.sellercenter.service.MerchantPayService;
import com.qcloud.component.sellercenter.model.query.MerchantPayQuery;
		
@Service
public class MerchantPayServiceImpl implements MerchantPayService{
	
	@Autowired
	private MerchantPayDao merchantPayDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "sellercenter_merchant_pay";

	@Override
	public boolean add(MerchantPay merchantPay) {
		long id = autoIdGenerator.get(ID_KEY);
		merchantPay.setId(id);
		
		return merchantPayDao.add(merchantPay);
	}

	@Override
	public MerchantPay get(Long id) {	
		return merchantPayDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchantPayDao.delete(id);
	}
	
	@Override
	public boolean update(MerchantPay merchantPay) {
		return merchantPayDao.update(merchantPay);
	}
	
	@Override
	public Page<MerchantPay> page(MerchantPayQuery query, int start, int count) {
		return merchantPayDao.page(query, start, count);
	}
	
	public List<MerchantPay> listAll(){
		return merchantPayDao.listAll();
	}
}

