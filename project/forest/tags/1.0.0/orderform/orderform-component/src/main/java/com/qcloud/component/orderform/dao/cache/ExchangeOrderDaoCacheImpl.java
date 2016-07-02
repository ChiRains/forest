package com.qcloud.component.orderform.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.ExchangeOrderDao;
import com.qcloud.component.orderform.model.ExchangeOrder;
import com.qcloud.component.orderform.model.query.ExchangeOrderQuery;

@Repository
public class ExchangeOrderDaoCacheImpl implements ExchangeOrderDao {
	
	@Autowired
	private ExchangeOrderDao exchangeOrderDaoMysqlImpl;
	
//	@Autowired
//	private ExchangeOrderDao exchangeOrderDaoRedisImpl;

	@Override
	public boolean add(ExchangeOrder exchangeOrder) {
		return exchangeOrderDaoMysqlImpl.add(exchangeOrder);
	}

	@Override
	public ExchangeOrder get(Long id) {
	    return exchangeOrderDaoMysqlImpl.get(id);
//		return CacheLoader.get(exchangeOrderDaoRedisImpl, exchangeOrderDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return exchangeOrderDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(ExchangeOrder exchangeOrder){
		return exchangeOrderDaoMysqlImpl.update(exchangeOrder);
	}
	
	@Override
	public List<ExchangeOrder> list(List<Long> idList) {
		return CacheLoader.list(exchangeOrderDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, ExchangeOrder> map(Set<Long> idSet){
		return CacheLoader.map(exchangeOrderDaoMysqlImpl, idSet);
	}

	@Override
	public Page<ExchangeOrder> page(int start, int count){
		return exchangeOrderDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<ExchangeOrder> page(ExchangeOrderQuery query, int start, int count){
		return exchangeOrderDaoMysqlImpl.page(query, start, count);
	}
	
	public List<ExchangeOrder> listAll(){
		return exchangeOrderDaoMysqlImpl.listAll();
	}

    @Override
    public List<ExchangeOrder> listBySubOrder(Long subOrderId) {
        return exchangeOrderDaoMysqlImpl.listBySubOrder(subOrderId);
    }

    @Override
    public List<ExchangeOrder> list4ExpireState(int state, int start, int size) {
        return exchangeOrderDaoMysqlImpl.list4ExpireState(state, start, size);
    }
}

