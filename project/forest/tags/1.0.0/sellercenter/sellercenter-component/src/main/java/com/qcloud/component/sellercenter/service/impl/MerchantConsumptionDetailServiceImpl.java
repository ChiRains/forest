package com.qcloud.component.sellercenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.MerchantConsumptionDetailDao;
import com.qcloud.component.sellercenter.model.MerchantConsumptionDetail;
import com.qcloud.component.sellercenter.service.MerchantConsumptionDetailService;
import com.qcloud.component.sellercenter.model.query.MerchantConsumptionDetailQuery;
		
@Service
public class MerchantConsumptionDetailServiceImpl implements MerchantConsumptionDetailService{
	
	@Autowired
	private MerchantConsumptionDetailDao merchantConsumptionDetailDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "sellercenter_merchant_consumption_detail";

	@Override
	public boolean add(MerchantConsumptionDetail merchantConsumptionDetail) {
		long id = autoIdGenerator.get(ID_KEY);
		merchantConsumptionDetail.setId(id);
		
		return merchantConsumptionDetailDao.add(merchantConsumptionDetail);
	}

	@Override
	public MerchantConsumptionDetail get(Long id) {	
		return merchantConsumptionDetailDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchantConsumptionDetailDao.delete(id);
	}
	
	@Override
	public boolean update(MerchantConsumptionDetail merchantConsumptionDetail) {
		return merchantConsumptionDetailDao.update(merchantConsumptionDetail);
	}
	
	@Override
	public Page<MerchantConsumptionDetail> page(MerchantConsumptionDetailQuery query, int start, int count) {
		return merchantConsumptionDetailDao.page(query, start, count);
	}
	
	public List<MerchantConsumptionDetail> listAll(){
		return merchantConsumptionDetailDao.listAll();
	}
}

