package com.qcloud.component.orderform.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.ReturnOrderDao;
import com.qcloud.component.orderform.model.ReturnOrder;
import com.qcloud.component.orderform.model.query.ReturnOrderQuery;

@Repository
public class ReturnOrderDaoCacheImpl implements ReturnOrderDao {
	
	@Autowired
	private ReturnOrderDao returnOrderDaoMysqlImpl;
	
//	@Autowired
//	private ReturnOrderDao returnOrderDaoRedisImpl;

	@Override
	public boolean add(ReturnOrder returnOrder) {
		return returnOrderDaoMysqlImpl.add(returnOrder);
	}

	@Override
	public ReturnOrder get(Long id) {
	    return returnOrderDaoMysqlImpl.get(id);
//		return CacheLoader.get(returnOrderDaoRedisImpl, returnOrderDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return returnOrderDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(ReturnOrder returnOrder){
		return returnOrderDaoMysqlImpl.update(returnOrder);
	}
	
	@Override
	public List<ReturnOrder> list(List<Long> idList) {
		return CacheLoader.list(returnOrderDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, ReturnOrder> map(Set<Long> idSet){
		return CacheLoader.map(returnOrderDaoMysqlImpl, idSet);
	}

	@Override
	public Page<ReturnOrder> page(int start, int count){
		return returnOrderDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<ReturnOrder> page(ReturnOrderQuery query, int start, int count){
		return returnOrderDaoMysqlImpl.page(query, start, count);
	}
	
	public List<ReturnOrder> listAll(){
		return returnOrderDaoMysqlImpl.listAll();
	}

    @Override
    public List<ReturnOrder> listBySubOrder(Long subOrderId) {
        return returnOrderDaoMysqlImpl.listBySubOrder(subOrderId);
    }

    @Override
    public List<ReturnOrder> list4ExpireState(int state, int start, int size) {
        return returnOrderDaoMysqlImpl.list4ExpireState(state, start, size);
    }
}

