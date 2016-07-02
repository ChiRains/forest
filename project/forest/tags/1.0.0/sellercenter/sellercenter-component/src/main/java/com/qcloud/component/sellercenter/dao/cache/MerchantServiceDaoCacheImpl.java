package com.qcloud.component.sellercenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.MerchantServiceDao;
import com.qcloud.component.sellercenter.model.MerchantService;
import com.qcloud.component.sellercenter.model.query.MerchantServiceQuery;

@Repository
public class MerchantServiceDaoCacheImpl implements MerchantServiceDao {
	
	@Autowired
	private MerchantServiceDao merchantServiceDaoMysqlImpl;
	
//	@Autowired
//	private MerchantServiceDao merchantServiceDaoRedisImpl;

	@Override
	public boolean add(MerchantService merchantService) {
		return merchantServiceDaoMysqlImpl.add(merchantService);
	}

	@Override
	public MerchantService get(Long id) {
	    return merchantServiceDaoMysqlImpl.get(id);
//		return CacheLoader.get(merchantServiceDaoRedisImpl, merchantServiceDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return merchantServiceDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MerchantService merchantService){
		return merchantServiceDaoMysqlImpl.update(merchantService);
	}
	
	@Override
	public List<MerchantService> list(List<Long> idList) {
        return CacheLoader.list(merchantServiceDaoMysqlImpl, idList);
//		return CacheLoader.list(merchantServiceDaoRedisImpl, merchantServiceDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MerchantService> map(Set<Long> idSet){
        return CacheLoader.map(merchantServiceDaoMysqlImpl, idSet);
//		return CacheLoader.map(merchantServiceDaoRedisImpl, merchantServiceDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MerchantService> page(int start, int count){
		return merchantServiceDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MerchantService> page(MerchantServiceQuery query, int start, int count){
		return merchantServiceDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MerchantService> listAll(){
		return merchantServiceDaoMysqlImpl.listAll();
	}
}

