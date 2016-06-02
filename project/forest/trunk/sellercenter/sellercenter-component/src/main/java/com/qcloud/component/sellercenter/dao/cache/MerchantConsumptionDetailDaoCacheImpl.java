package com.qcloud.component.sellercenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.MerchantConsumptionDetailDao;
import com.qcloud.component.sellercenter.model.MerchantConsumptionDetail;
import com.qcloud.component.sellercenter.model.query.MerchantConsumptionDetailQuery;

@Repository
public class MerchantConsumptionDetailDaoCacheImpl implements MerchantConsumptionDetailDao {
	
	@Autowired
	private MerchantConsumptionDetailDao merchantConsumptionDetailDaoMysqlImpl;
	
	@Autowired
	private MerchantConsumptionDetailDao merchantConsumptionDetailDaoRedisImpl;

	@Override
	public boolean add(MerchantConsumptionDetail merchantConsumptionDetail) {
		return merchantConsumptionDetailDaoMysqlImpl.add(merchantConsumptionDetail);
	}

	@Override
	public MerchantConsumptionDetail get(Long id) {
		return CacheLoader.get(merchantConsumptionDetailDaoRedisImpl, merchantConsumptionDetailDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return merchantConsumptionDetailDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MerchantConsumptionDetail merchantConsumptionDetail){
		return merchantConsumptionDetailDaoMysqlImpl.update(merchantConsumptionDetail);
	}
	
	@Override
	public List<MerchantConsumptionDetail> list(List<Long> idList) {
		return CacheLoader.list(merchantConsumptionDetailDaoRedisImpl, merchantConsumptionDetailDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MerchantConsumptionDetail> map(Set<Long> idSet){
		return CacheLoader.map(merchantConsumptionDetailDaoRedisImpl, merchantConsumptionDetailDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MerchantConsumptionDetail> page(int start, int count){
		return merchantConsumptionDetailDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MerchantConsumptionDetail> page(MerchantConsumptionDetailQuery query, int start, int count){
		return merchantConsumptionDetailDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MerchantConsumptionDetail> listAll(){
		return merchantConsumptionDetailDaoMysqlImpl.listAll();
	}
}

