package com.qcloud.component.orderform.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.MembershipCardOrderDao;
import com.qcloud.component.orderform.model.MembershipCardOrder;
import com.qcloud.component.orderform.model.query.MembershipCardOrderQuery;

@Repository
public class MembershipCardOrderDaoCacheImpl implements MembershipCardOrderDao {
	
	@Autowired
	private MembershipCardOrderDao membershipCardOrderDaoMysqlImpl;
	
	@Autowired
	private MembershipCardOrderDao membershipCardOrderDaoRedisImpl;

	@Override
	public boolean add(MembershipCardOrder membershipCardOrder) {
		return membershipCardOrderDaoMysqlImpl.add(membershipCardOrder);
	}

	@Override
	public MembershipCardOrder get(Long id) {
		return CacheLoader.get(membershipCardOrderDaoRedisImpl, membershipCardOrderDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return membershipCardOrderDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MembershipCardOrder membershipCardOrder){
		return membershipCardOrderDaoMysqlImpl.update(membershipCardOrder);
	}
	
	@Override
	public List<MembershipCardOrder> list(List<Long> idList) {
		return CacheLoader.list(membershipCardOrderDaoRedisImpl, membershipCardOrderDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MembershipCardOrder> map(Set<Long> idSet){
		return CacheLoader.map(membershipCardOrderDaoRedisImpl, membershipCardOrderDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MembershipCardOrder> page(int start, int count){
		return membershipCardOrderDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MembershipCardOrder> page(MembershipCardOrderQuery query, int start, int count){
		return membershipCardOrderDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MembershipCardOrder> listAll(){
		return membershipCardOrderDaoMysqlImpl.listAll();
	}
}

