package com.qcloud.component.orderform.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.model.ReturnOrderItemDetail;
import com.qcloud.component.orderform.model.query.ReturnOrderItemDetailQuery;

public interface ReturnOrderItemDetailService {
	
	public boolean add(ReturnOrderItemDetail returnOrderItemDetail);	
	
	public ReturnOrderItemDetail get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(ReturnOrderItemDetail returnOrderItemDetail);

	public Page<ReturnOrderItemDetail> page(ReturnOrderItemDetailQuery query, int start, int count);
	
	public List<ReturnOrderItemDetail> listAll();
}

