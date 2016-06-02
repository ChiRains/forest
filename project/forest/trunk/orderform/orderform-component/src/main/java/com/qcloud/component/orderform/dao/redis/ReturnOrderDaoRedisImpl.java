package com.qcloud.component.orderform.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.ReturnOrderDao;
import com.qcloud.component.orderform.model.ReturnOrder;
import com.qcloud.component.orderform.model.query.ReturnOrderQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class ReturnOrderDaoRedisImpl implements ReturnOrderDao {

	//@Resource(name = "redis-orderform")
	//private Redis redis;

	@Override
	public boolean add(ReturnOrder returnOrder) {			
		throw new NotImplementedException();
	}

	@Override
	public ReturnOrder get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(ReturnOrder returnOrder){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ReturnOrder> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, ReturnOrder> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<ReturnOrder> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<ReturnOrder> page(ReturnOrderQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ReturnOrder> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<ReturnOrder> listBySubOrder(Long subOrderId) {
        throw new NotImplementedException();
    }

    @Override
    public List<ReturnOrder> list4ExpireState(int state, int start, int size) {
        throw new NotImplementedException();
    }
}

