package com.qcloud.component.orderform.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.ReturnOrderItemDetailDao;
import com.qcloud.component.orderform.model.ReturnOrderItemDetail;
import com.qcloud.component.orderform.model.query.ReturnOrderItemDetailQuery;

@Repository
public class ReturnOrderItemDetailDaoCacheImpl implements ReturnOrderItemDetailDao {
	
	@Autowired
	private ReturnOrderItemDetailDao returnOrderItemDetailDaoMysqlImpl;
	
	@Autowired
	private ReturnOrderItemDetailDao returnOrderItemDetailDaoRedisImpl;

	@Override
	public boolean add(ReturnOrderItemDetail returnOrderItemDetail) {
		return returnOrderItemDetailDaoMysqlImpl.add(returnOrderItemDetail);
	}

	@Override
	public ReturnOrderItemDetail get(Long id) {
		return CacheLoader.get(returnOrderItemDetailDaoRedisImpl, returnOrderItemDetailDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return returnOrderItemDetailDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(ReturnOrderItemDetail returnOrderItemDetail){
		return returnOrderItemDetailDaoMysqlImpl.update(returnOrderItemDetail);
	}
	
	@Override
	public List<ReturnOrderItemDetail> list(List<Long> idList) {
		return CacheLoader.list(returnOrderItemDetailDaoRedisImpl, returnOrderItemDetailDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, ReturnOrderItemDetail> map(Set<Long> idSet){
		return CacheLoader.map(returnOrderItemDetailDaoRedisImpl, returnOrderItemDetailDaoMysqlImpl, idSet);
	}

	@Override
	public Page<ReturnOrderItemDetail> page(int start, int count){
		return returnOrderItemDetailDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<ReturnOrderItemDetail> page(ReturnOrderItemDetailQuery query, int start, int count){
		return returnOrderItemDetailDaoMysqlImpl.page(query, start, count);
	}
	
	public List<ReturnOrderItemDetail> listAll(){
		return returnOrderItemDetailDaoMysqlImpl.listAll();
	}
}

