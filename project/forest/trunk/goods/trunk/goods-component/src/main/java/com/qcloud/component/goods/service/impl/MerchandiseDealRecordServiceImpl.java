package com.qcloud.component.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseDealRecordDao;
import com.qcloud.component.goods.model.MerchandiseDealRecord;
import com.qcloud.component.goods.service.MerchandiseDealRecordService;
import com.qcloud.component.goods.model.query.MerchandiseDealRecordQuery;
		
@Service
public class MerchandiseDealRecordServiceImpl implements MerchandiseDealRecordService{
	
	@Autowired
	private MerchandiseDealRecordDao merchandiseDealRecordDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "goods_merchandise_deal_record";

	@Override
	public boolean add(MerchandiseDealRecord merchandiseDealRecord) {
		long id = autoIdGenerator.get(ID_KEY);
		merchandiseDealRecord.setId(id);
		
		return merchandiseDealRecordDao.add(merchandiseDealRecord);
	}

	@Override
	public MerchandiseDealRecord get(Long id) {	
		return merchandiseDealRecordDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchandiseDealRecordDao.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseDealRecord merchandiseDealRecord) {
		return merchandiseDealRecordDao.update(merchandiseDealRecord);
	}
	
	@Override
	public Page<MerchandiseDealRecord> page(MerchandiseDealRecordQuery query, int start, int count) {
		return merchandiseDealRecordDao.page(query, start, count);
	}
	
	public List<MerchandiseDealRecord> listAll(){
		return merchandiseDealRecordDao.listAll();
	}
}

