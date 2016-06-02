package com.qcloud.component.sellercenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.sellercenter.dao.DistributeMembershipCardDao;
import com.qcloud.component.sellercenter.model.DistributeMembershipCard;
import com.qcloud.component.sellercenter.model.DistributeMembershipCardStat;
import com.qcloud.component.sellercenter.model.query.DistributeMembershipCardQuery;
import com.qcloud.component.sellercenter.model.query.DistributeMembershipCardStatQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class DistributeMembershipCardDaoRedisImpl implements DistributeMembershipCardDao {

	//@Resource(name = "redis-sellercenter")
	//private Redis redis;

	@Override
	public boolean add(DistributeMembershipCard distributeMembershipCard) {			
		throw new NotImplementedException();
	}

	@Override
	public DistributeMembershipCard get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(DistributeMembershipCard distributeMembershipCard){
		throw new NotImplementedException();
	}
	
	@Override
	public List<DistributeMembershipCard> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, DistributeMembershipCard> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<DistributeMembershipCard> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<DistributeMembershipCard> page(DistributeMembershipCardQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<DistributeMembershipCard> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public DistributeMembershipCard getByCardNumber(String cardNumber) {
        throw new NotImplementedException();
    }

    @Override
    public Page<DistributeMembershipCardStat> statPage(DistributeMembershipCardStatQuery query, int start, int count) {
        throw new NotImplementedException();
    }

    @Override
    public Page<DistributeMembershipCard> page(Long merchantId, int start, int count) {
        throw new NotImplementedException();
    }

    @Override
    public int countMerchantSended(long merchantId) {
        throw new NotImplementedException();
    }
}

