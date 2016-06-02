package com.qcloud.component.sellercenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.MerchantClassifyDao;
import com.qcloud.component.sellercenter.model.MerchantClassify;
import com.qcloud.component.sellercenter.model.query.MerchantClassifyQuery;

@Repository
public class MerchantClassifyDaoCacheImpl implements MerchantClassifyDao {
	
	@Autowired
	private MerchantClassifyDao merchantClassifyDaoMysqlImpl;
	
	@Autowired
	private MerchantClassifyDao merchantClassifyDaoRedisImpl;

	@Override
	public boolean add(MerchantClassify merchantClassify) {
		return merchantClassifyDaoMysqlImpl.add(merchantClassify);
	}

	@Override
	public MerchantClassify get(Long id) {
		return CacheLoader.get(merchantClassifyDaoRedisImpl, merchantClassifyDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return merchantClassifyDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MerchantClassify merchantClassify){
		return merchantClassifyDaoMysqlImpl.update(merchantClassify);
	}
	
	@Override
	public List<MerchantClassify> list(List<Long> idList) {
		return CacheLoader.list(merchantClassifyDaoRedisImpl, merchantClassifyDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MerchantClassify> map(Set<Long> idSet){
		return CacheLoader.map(merchantClassifyDaoRedisImpl, merchantClassifyDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MerchantClassify> page(int start, int count){
		return merchantClassifyDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MerchantClassify> page(MerchantClassifyQuery query, int start, int count){
		return merchantClassifyDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MerchantClassify> listAll(){
		return merchantClassifyDaoMysqlImpl.listAll();
	}

    @Override
    public List<MerchantClassify> listByMerchant(Long merchantId) {
        return merchantClassifyDaoMysqlImpl.listByMerchant(merchantId);
    }

    @Override
    public MerchantClassify get(Long classifyId, Long merchantId) {
        return merchantClassifyDaoMysqlImpl.get(classifyId, merchantId);
    }
}

