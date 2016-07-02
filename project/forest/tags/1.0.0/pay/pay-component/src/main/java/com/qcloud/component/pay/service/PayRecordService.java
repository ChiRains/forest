package com.qcloud.component.pay.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.pay.model.PayRecord;
import com.qcloud.component.pay.model.query.PayRecordQuery;

public interface PayRecordService {
	
	public boolean add(PayRecord payRecord);	
	
	public PayRecord get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(PayRecord payRecord);

	public Page<PayRecord> page(PayRecordQuery query, int start, int count);
	
	public List<PayRecord> listAll();
}

