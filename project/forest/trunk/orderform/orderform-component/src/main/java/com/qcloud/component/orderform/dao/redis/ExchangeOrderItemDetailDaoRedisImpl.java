package com.qcloud.component.orderform.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.ExchangeOrderItemDetailDao;
import com.qcloud.component.orderform.model.ExchangeOrderItemDetail;
import com.qcloud.component.orderform.model.query.ExchangeOrderItemDetailQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class ExchangeOrderItemDetailDaoRedisImpl implements ExchangeOrderItemDetailDao {

	//@Resource(name = "redis-orderform")
	//private Redis redis;

	@Override
	public boolean add(ExchangeOrderItemDetail exchangeOrderItemDetail) {			
		throw new NotImplementedException();
	}

	@Override
	public ExchangeOrderItemDetail get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(ExchangeOrderItemDetail exchangeOrderItemDetail){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ExchangeOrderItemDetail> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, ExchangeOrderItemDetail> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<ExchangeOrderItemDetail> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<ExchangeOrderItemDetail> page(ExchangeOrderItemDetailQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ExchangeOrderItemDetail> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<ExchangeOrderItemDetail> listByExchange(Long exchangeId) {
        throw new NotImplementedException();
    }
}

