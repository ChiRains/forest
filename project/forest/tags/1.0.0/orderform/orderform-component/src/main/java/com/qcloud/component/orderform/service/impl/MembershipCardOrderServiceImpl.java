package com.qcloud.component.orderform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.MembershipCardOrderDao;
import com.qcloud.component.orderform.model.MembershipCardOrder;
import com.qcloud.component.orderform.service.MembershipCardOrderService;
import com.qcloud.component.orderform.model.query.MembershipCardOrderQuery;
		
@Service
public class MembershipCardOrderServiceImpl implements MembershipCardOrderService{
	
	@Autowired
	private MembershipCardOrderDao membershipCardOrderDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "orderform_membership_card_order";

	@Override
	public boolean add(MembershipCardOrder membershipCardOrder) {
		long id = autoIdGenerator.get(ID_KEY);
		membershipCardOrder.setId(id);
		
		return membershipCardOrderDao.add(membershipCardOrder);
	}

	@Override
	public MembershipCardOrder get(Long id) {	
		return membershipCardOrderDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return membershipCardOrderDao.delete(id);
	}
	
	@Override
	public boolean update(MembershipCardOrder membershipCardOrder) {
		return membershipCardOrderDao.update(membershipCardOrder);
	}
	
	@Override
	public Page<MembershipCardOrder> page(MembershipCardOrderQuery query, int start, int count) {
		return membershipCardOrderDao.page(query, start, count);
	}
	
	public List<MembershipCardOrder> listAll(){
		return membershipCardOrderDao.listAll();
	}
}

