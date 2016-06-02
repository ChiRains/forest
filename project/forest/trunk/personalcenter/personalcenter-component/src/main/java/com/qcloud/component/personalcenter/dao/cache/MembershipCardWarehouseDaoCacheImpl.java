package com.qcloud.component.personalcenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.MembershipCardWarehouseDao;
import com.qcloud.component.personalcenter.model.MembershipCardWarehouse;
import com.qcloud.component.personalcenter.model.query.MembershipCardWarehouseQuery;

@Repository
public class MembershipCardWarehouseDaoCacheImpl implements MembershipCardWarehouseDao {
	
	@Autowired
	private MembershipCardWarehouseDao membershipCardWarehouseDaoMysqlImpl;
	
//	@Autowired
//	private MembershipCardWarehouseDao membershipCardWarehouseDaoRedisImpl;

	@Override
	public boolean add(MembershipCardWarehouse membershipCardWarehouse) {
		return membershipCardWarehouseDaoMysqlImpl.add(membershipCardWarehouse);
	}

	@Override
	public MembershipCardWarehouse get(Long id) {
	    return membershipCardWarehouseDaoMysqlImpl.get(id);
//		return CacheLoader.get(membershipCardWarehouseDaoRedisImpl, membershipCardWarehouseDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return membershipCardWarehouseDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MembershipCardWarehouse membershipCardWarehouse){
		return membershipCardWarehouseDaoMysqlImpl.update(membershipCardWarehouse);
	}
	
	@Override
	public List<MembershipCardWarehouse> list(List<Long> idList) {
		return CacheLoader.list(membershipCardWarehouseDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MembershipCardWarehouse> map(Set<Long> idSet){
		return CacheLoader.map(membershipCardWarehouseDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MembershipCardWarehouse> page(int start, int count){
		return membershipCardWarehouseDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MembershipCardWarehouse> page(MembershipCardWarehouseQuery query, int start, int count){
		return membershipCardWarehouseDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MembershipCardWarehouse> listAll(){
		return membershipCardWarehouseDaoMysqlImpl.listAll();
	}

    @Override
    public MembershipCardWarehouse getByCardNumber(String number) {
        return membershipCardWarehouseDaoMysqlImpl.getByCardNumber(number);
    }
}

