package com.qcloud.component.sellercenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.MerchantClassifyDao;
import com.qcloud.component.sellercenter.model.MerchantClassify;
import com.qcloud.component.sellercenter.service.MerchantClassifyService;
import com.qcloud.component.sellercenter.model.query.MerchantClassifyQuery;
		
@Service
public class MerchantClassifyServiceImpl implements MerchantClassifyService{
	
	@Autowired
	private MerchantClassifyDao merchantClassifyDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "sellercenter_merchant_classify";

	@Override
	public boolean add(MerchantClassify merchantClassify) {
		long id = autoIdGenerator.get(ID_KEY);
		merchantClassify.setId(id);
		
		return merchantClassifyDao.add(merchantClassify);
	}

	@Override
	public MerchantClassify get(Long id) {	
		return merchantClassifyDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchantClassifyDao.delete(id);
	}
	
	@Override
	public boolean update(MerchantClassify merchantClassify) {
		return merchantClassifyDao.update(merchantClassify);
	}
	
	@Override
	public Page<MerchantClassify> page(MerchantClassifyQuery query, int start, int count) {
		return merchantClassifyDao.page(query, start, count);
	}
	
	public List<MerchantClassify> listAll(){
		return merchantClassifyDao.listAll();
	}

    @Override
    public List<MerchantClassify> listByMerchant(Long merchantId) {
        return merchantClassifyDao.listByMerchant(merchantId);
    }

    @Override
    public MerchantClassify get(Long classifyId, Long merchantId) {
        return merchantClassifyDao.get(classifyId, merchantId);
    }
}

