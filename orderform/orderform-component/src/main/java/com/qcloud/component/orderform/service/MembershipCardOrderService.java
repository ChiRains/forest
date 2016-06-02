package com.qcloud.component.orderform.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.model.MembershipCardOrder;
import com.qcloud.component.orderform.model.query.MembershipCardOrderQuery;

public interface MembershipCardOrderService {
	
	public boolean add(MembershipCardOrder membershipCardOrder);	
	
	public MembershipCardOrder get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MembershipCardOrder membershipCardOrder);

	public Page<MembershipCardOrder> page(MembershipCardOrderQuery query, int start, int count);
	
	public List<MembershipCardOrder> listAll();
}

