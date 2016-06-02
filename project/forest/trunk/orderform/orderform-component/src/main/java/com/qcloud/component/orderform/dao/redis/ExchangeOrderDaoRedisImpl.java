package com.qcloud.component.orderform.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.ExchangeOrderDao;
import com.qcloud.component.orderform.model.ExchangeOrder;
import com.qcloud.component.orderform.model.query.ExchangeOrderQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class ExchangeOrderDaoRedisImpl implements ExchangeOrderDao {

	//@Resource(name = "redis-orderform")
	//private Redis redis;

	@Override
	public boolean add(ExchangeOrder exchangeOrder) {			
		throw new NotImplementedException();
	}

	@Override
	public ExchangeOrder get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(ExchangeOrder exchangeOrder){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ExchangeOrder> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, ExchangeOrder> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<ExchangeOrder> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<ExchangeOrder> page(ExchangeOrderQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ExchangeOrder> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<ExchangeOrder> listBySubOrder(Long subOrderId) {
        throw new NotImplementedException();
    }

    @Override
    public List<ExchangeOrder> list4ExpireState(int state, int start, int size) {
        throw new NotImplementedException();
    }
}

