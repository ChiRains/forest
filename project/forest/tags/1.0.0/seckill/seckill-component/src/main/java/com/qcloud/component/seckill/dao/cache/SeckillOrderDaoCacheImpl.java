package com.qcloud.component.seckill.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.seckill.dao.SeckillOrderDao;
import com.qcloud.component.seckill.model.SeckillOrder;
import com.qcloud.component.seckill.model.query.SeckillOrderQuery;

@Repository
public class SeckillOrderDaoCacheImpl implements SeckillOrderDao {
	
	@Autowired
	private SeckillOrderDao seckillOrderDaoMysqlImpl;
	
	@Autowired
	private SeckillOrderDao seckillOrderDaoRedisImpl;

	@Override
	public boolean add(SeckillOrder seckillOrder) {
		return seckillOrderDaoMysqlImpl.add(seckillOrder);
	}

	@Override
	public SeckillOrder get(Long id) {
		return CacheLoader.get(seckillOrderDaoRedisImpl, seckillOrderDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return seckillOrderDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(SeckillOrder seckillOrder){
		return seckillOrderDaoMysqlImpl.update(seckillOrder);
	}
	
	@Override
	public List<SeckillOrder> list(List<Long> idList) {
		return CacheLoader.list(seckillOrderDaoRedisImpl, seckillOrderDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, SeckillOrder> map(Set<Long> idSet){
		return CacheLoader.map(seckillOrderDaoRedisImpl, seckillOrderDaoMysqlImpl, idSet);
	}

	@Override
	public Page<SeckillOrder> page(int start, int count){
		return seckillOrderDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<SeckillOrder> page(SeckillOrderQuery query, int start, int count){
		return seckillOrderDaoMysqlImpl.page(query, start, count);
	}
	
	public List<SeckillOrder> listAll(){
		return seckillOrderDaoMysqlImpl.listAll();
	}
}

