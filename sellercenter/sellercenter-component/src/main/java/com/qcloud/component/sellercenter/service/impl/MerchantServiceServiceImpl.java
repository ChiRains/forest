package com.qcloud.component.sellercenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.MerchantServiceDao;
import com.qcloud.component.sellercenter.model.MerchantService;
import com.qcloud.component.sellercenter.service.MerchantServiceService;
import com.qcloud.component.sellercenter.model.query.MerchantServiceQuery;
		
@Service
public class MerchantServiceServiceImpl implements MerchantServiceService{
	
	@Autowired
	private MerchantServiceDao merchantServiceDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "sellercenter_merchant_service";

	@Override
	public boolean add(MerchantService merchantService) {
		long id = autoIdGenerator.get(ID_KEY);
		merchantService.setId(id);
		
		return merchantServiceDao.add(merchantService);
	}

	@Override
	public MerchantService get(Long id) {	
		return merchantServiceDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchantServiceDao.delete(id);
	}
	
	@Override
	public boolean update(MerchantService merchantService) {
		return merchantServiceDao.update(merchantService);
	}
	
	@Override
	public Page<MerchantService> page(MerchantServiceQuery query, int start, int count) {
		return merchantServiceDao.page(query, start, count);
	}
	
	public List<MerchantService> listAll(){
		return merchantServiceDao.listAll();
	}
}

