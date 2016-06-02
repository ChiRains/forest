package com.qcloud.component.orderform.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.orderform.model.MembershipCardOrder;
import com.qcloud.component.orderform.model.query.MembershipCardOrderQuery;
		
public interface MembershipCardOrderDao extends ISimpleDao<MembershipCardOrder, Long> {

	public boolean add(MembershipCardOrder membershipCardOrder);	
	
	public MembershipCardOrder get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MembershipCardOrder membershipCardOrder);
	
	public List<MembershipCardOrder> list(List<Long> idList);
	
	public Map<Long, MembershipCardOrder> map(Set<Long> idSet);
	
	public Page<MembershipCardOrder> page(MembershipCardOrderQuery query, int start, int size);

	public List<MembershipCardOrder> listAll();
	
}
