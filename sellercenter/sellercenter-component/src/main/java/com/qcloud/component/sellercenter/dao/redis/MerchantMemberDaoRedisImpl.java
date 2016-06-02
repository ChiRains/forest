package com.qcloud.component.sellercenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.sellercenter.dao.MerchantMemberDao;
import com.qcloud.component.sellercenter.model.MerchantMember;
import com.qcloud.component.sellercenter.model.query.MerchantMemberQuery;

@Repository
public class MerchantMemberDaoRedisImpl implements MerchantMemberDao {

	//@Resource(name = "redis-sellercenter")
	//private Redis redis;

	@Override
	public boolean add(MerchantMember merchantMember) {			
		throw new NotImplementedException();
	}

	@Override
	public MerchantMember get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MerchantMember merchantMember){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchantMember> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchantMember> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchantMember> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MerchantMember> page(MerchantMemberQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchantMember> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<MerchantMember> listByMerchant(Long merchantId) {
        throw new NotImplementedException();
    }

    @Override
    public MerchantMember get(Long memberId, Long merchantId) {
        throw new NotImplementedException();
    }

    @Override
    public List<MerchantMember> listByMember(Long memberId) {
        throw new NotImplementedException();
    }
}

