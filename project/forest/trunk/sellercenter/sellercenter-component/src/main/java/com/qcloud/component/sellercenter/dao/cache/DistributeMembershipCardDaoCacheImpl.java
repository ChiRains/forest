package com.qcloud.component.sellercenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.DistributeMembershipCardDao;
import com.qcloud.component.sellercenter.model.DistributeMembershipCard;
import com.qcloud.component.sellercenter.model.DistributeMembershipCardStat;
import com.qcloud.component.sellercenter.model.query.DistributeMembershipCardQuery;
import com.qcloud.component.sellercenter.model.query.DistributeMembershipCardStatQuery;

@Repository
public class DistributeMembershipCardDaoCacheImpl implements DistributeMembershipCardDao {
	
	@Autowired
	private DistributeMembershipCardDao distributeMembershipCardDaoMysqlImpl;
	
//	@Autowired
//	private DistributeMembershipCardDao distributeMembershipCardDaoRedisImpl;

	@Override
	public boolean add(DistributeMembershipCard distributeMembershipCard) {
		return distributeMembershipCardDaoMysqlImpl.add(distributeMembershipCard);
	}

	@Override
	public DistributeMembershipCard get(Long id) {
	    return distributeMembershipCardDaoMysqlImpl.get(id);
//		return CacheLoader.get(distributeMembershipCardDaoRedisImpl, distributeMembershipCardDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return distributeMembershipCardDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(DistributeMembershipCard distributeMembershipCard){
		return distributeMembershipCardDaoMysqlImpl.update(distributeMembershipCard);
	}
	
	@Override
	public List<DistributeMembershipCard> list(List<Long> idList) {
		return CacheLoader.list(distributeMembershipCardDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, DistributeMembershipCard> map(Set<Long> idSet){
		return CacheLoader.map(distributeMembershipCardDaoMysqlImpl, idSet);
	}

	@Override
	public Page<DistributeMembershipCard> page(int start, int count){
		return distributeMembershipCardDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<DistributeMembershipCard> page(DistributeMembershipCardQuery query, int start, int count){
		return distributeMembershipCardDaoMysqlImpl.page(query, start, count);
	}
	
	public List<DistributeMembershipCard> listAll(){
		return distributeMembershipCardDaoMysqlImpl.listAll();
	}

    @Override
    public DistributeMembershipCard getByCardNumber(String cardNumber) {

        return distributeMembershipCardDaoMysqlImpl.getByCardNumber(cardNumber);
    }

    @Override
    public Page<DistributeMembershipCardStat> statPage(DistributeMembershipCardStatQuery query, int start, int count)  {

        return distributeMembershipCardDaoMysqlImpl.statPage(query, start, count);
    }

    @Override
    public Page<DistributeMembershipCard> page(Long merchantId, int start, int count) {

        return distributeMembershipCardDaoMysqlImpl.page(merchantId, start, count);
    }

    @Override
    public int countMerchantSended(long merchantId) {

        return distributeMembershipCardDaoMysqlImpl.countMerchantSended(merchantId);
    }
}

