package com.qcloud.component.sellercenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.MerchantMerchandiseClassifyDao;
import com.qcloud.component.sellercenter.model.MerchantMerchandiseClassify;
import com.qcloud.component.sellercenter.service.MerchantMerchandiseClassifyService;
import com.qcloud.component.sellercenter.model.query.MerchantMerchandiseClassifyQuery;
		
@Service
public class MerchantMerchandiseClassifyServiceImpl implements MerchantMerchandiseClassifyService{
	
	@Autowired
	private MerchantMerchandiseClassifyDao merchantMerchandiseClassifyDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "sellercenter_merchant_merchandise_classify";

	@Override
	public boolean add(MerchantMerchandiseClassify merchantMerchandiseClassify) {
		long id = autoIdGenerator.get(ID_KEY);
		merchantMerchandiseClassify.setId(id);
		
		return merchantMerchandiseClassifyDao.add(merchantMerchandiseClassify);
	}

	@Override
	public MerchantMerchandiseClassify get(Long id) {	
		return merchantMerchandiseClassifyDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchantMerchandiseClassifyDao.delete(id);
	}
	
	@Override
	public boolean update(MerchantMerchandiseClassify merchantMerchandiseClassify) {
		return merchantMerchandiseClassifyDao.update(merchantMerchandiseClassify);
	}
	
	@Override
	public Page<MerchantMerchandiseClassify> page(MerchantMerchandiseClassifyQuery query, int start, int count) {
		return merchantMerchandiseClassifyDao.page(query, start, count);
	}
	
	public List<MerchantMerchandiseClassify> listAll(){
		return merchantMerchandiseClassifyDao.listAll();
	}

    @Override
    public List<MerchantMerchandiseClassify> listByMerchantId(Long merchantId) {
        return merchantMerchandiseClassifyDao.listByMerchantId(merchantId);
    }

    @Override
    public boolean deleteByMerchantId(Long merchantId) {
        return merchantMerchandiseClassifyDao.deleteByMerchantId(merchantId);
    }
}

