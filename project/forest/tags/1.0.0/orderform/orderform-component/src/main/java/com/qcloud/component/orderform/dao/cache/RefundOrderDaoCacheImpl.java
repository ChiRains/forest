package com.qcloud.component.orderform.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.RefundOrderDao;
import com.qcloud.component.orderform.model.RefundOrder;
import com.qcloud.component.orderform.model.query.RefundOrderQuery;

@Repository
public class RefundOrderDaoCacheImpl implements RefundOrderDao {
	
	@Autowired
	private RefundOrderDao refundOrderDaoMysqlImpl;
	
//	@Autowired
//	private RefundOrderDao refundOrderDaoRedisImpl;

	@Override
	public boolean add(RefundOrder refundOrder) {
		return refundOrderDaoMysqlImpl.add(refundOrder);
	}

	@Override
	public RefundOrder get(Long id) {
	    return refundOrderDaoMysqlImpl.get(id);
//		return CacheLoader.get(refundOrderDaoRedisImpl, refundOrderDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return refundOrderDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(RefundOrder refundOrder){
		return refundOrderDaoMysqlImpl.update(refundOrder);
	}
	
	@Override
	public List<RefundOrder> list(List<Long> idList) {
		return CacheLoader.list(refundOrderDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, RefundOrder> map(Set<Long> idSet){
		return CacheLoader.map(refundOrderDaoMysqlImpl, idSet);
	}

	@Override
	public Page<RefundOrder> page(int start, int count){
		return refundOrderDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<RefundOrder> page(RefundOrderQuery query, int start, int count){
		return refundOrderDaoMysqlImpl.page(query, start, count);
	}
	
	public List<RefundOrder> listAll(){
		return refundOrderDaoMysqlImpl.listAll();
	}

    @Override
    public List<RefundOrder> listBySubOrder(Long subOrderId) {
        return refundOrderDaoMysqlImpl.listBySubOrder(subOrderId);
    }

    @Override
    public List<RefundOrder> list4ExpireState(int state, int start, int size) {
        return refundOrderDaoMysqlImpl.list4ExpireState(state, start, size);
    }
}

