package com.qcloud.component.orderform.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.ExchangeOrderItemDetailDao;
import com.qcloud.component.orderform.model.ExchangeOrderItemDetail;
import com.qcloud.component.orderform.model.query.ExchangeOrderItemDetailQuery;

@Repository
public class ExchangeOrderItemDetailDaoCacheImpl implements ExchangeOrderItemDetailDao {
	
	@Autowired
	private ExchangeOrderItemDetailDao exchangeOrderItemDetailDaoMysqlImpl;
	
//	@Autowired
//	private ExchangeOrderItemDetailDao exchangeOrderItemDetailDaoRedisImpl;

	@Override
	public boolean add(ExchangeOrderItemDetail exchangeOrderItemDetail) {
		return exchangeOrderItemDetailDaoMysqlImpl.add(exchangeOrderItemDetail);
	}

	@Override
	public ExchangeOrderItemDetail get(Long id) {
	    return exchangeOrderItemDetailDaoMysqlImpl.get(id);
//		return CacheLoader.get(exchangeOrderItemDetailDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return exchangeOrderItemDetailDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(ExchangeOrderItemDetail exchangeOrderItemDetail){
		return exchangeOrderItemDetailDaoMysqlImpl.update(exchangeOrderItemDetail);
	}
	
	@Override
	public List<ExchangeOrderItemDetail> list(List<Long> idList) {
		return CacheLoader.list(exchangeOrderItemDetailDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, ExchangeOrderItemDetail> map(Set<Long> idSet){
		return CacheLoader.map(exchangeOrderItemDetailDaoMysqlImpl, idSet);
	}

	@Override
	public Page<ExchangeOrderItemDetail> page(int start, int count){
		return exchangeOrderItemDetailDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<ExchangeOrderItemDetail> page(ExchangeOrderItemDetailQuery query, int start, int count){
		return exchangeOrderItemDetailDaoMysqlImpl.page(query, start, count);
	}
	
	public List<ExchangeOrderItemDetail> listAll(){
		return exchangeOrderItemDetailDaoMysqlImpl.listAll();
	}

    @Override
    public List<ExchangeOrderItemDetail> listByExchange(Long exchangeId) {
        return exchangeOrderItemDetailDaoMysqlImpl.listByExchange(exchangeId);
    }
}

