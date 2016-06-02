package com.qcloud.component.sellercenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.StoreDeliveryRangeDao;
import com.qcloud.component.sellercenter.model.StoreDeliveryRange;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryRangeQuery;

@Repository
public class StoreDeliveryRangeDaoCacheImpl implements StoreDeliveryRangeDao {
	
	@Autowired
	private StoreDeliveryRangeDao storeDeliveryRangeDaoMysqlImpl;
	
//	@Autowired
//	private StoreDeliveryRangeDao storeDeliveryRangeDaoRedisImpl;

	@Override
	public boolean add(StoreDeliveryRange storeDeliveryRange) {
		return storeDeliveryRangeDaoMysqlImpl.add(storeDeliveryRange);
	}

	@Override
	public StoreDeliveryRange get(Long id) {
	    return storeDeliveryRangeDaoMysqlImpl.get(id);
//		return CacheLoader.get(storeDeliveryRangeDaoRedisImpl, storeDeliveryRangeDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return storeDeliveryRangeDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(StoreDeliveryRange storeDeliveryRange){
		return storeDeliveryRangeDaoMysqlImpl.update(storeDeliveryRange);
	}
	
	@Override
	public List<StoreDeliveryRange> list(List<Long> idList) {
		return CacheLoader.list(storeDeliveryRangeDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, StoreDeliveryRange> map(Set<Long> idSet){
		return CacheLoader.map(storeDeliveryRangeDaoMysqlImpl, idSet);
	}

	@Override
	public Page<StoreDeliveryRange> page(int start, int count){
		return storeDeliveryRangeDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<StoreDeliveryRange> page(StoreDeliveryRangeQuery query, int start, int count){
		return storeDeliveryRangeDaoMysqlImpl.page(query, start, count);
	}
	
	public List<StoreDeliveryRange> listAll(){
		return storeDeliveryRangeDaoMysqlImpl.listAll();
	}

    @Override
    public StoreDeliveryRange getByStore(Long storeId) {

        return storeDeliveryRangeDaoMysqlImpl.getByStore(storeId);
    }
}

