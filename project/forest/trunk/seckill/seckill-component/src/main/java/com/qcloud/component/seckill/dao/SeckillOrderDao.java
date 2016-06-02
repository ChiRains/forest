package com.qcloud.component.seckill.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.seckill.model.SeckillOrder;
import com.qcloud.component.seckill.model.query.SeckillOrderQuery;
		
public interface SeckillOrderDao extends ISimpleDao<SeckillOrder, Long> {

	public boolean add(SeckillOrder seckillOrder);	
	
	public SeckillOrder get(Long id);

	public boolean delete(Long id);
	
	public boolean update(SeckillOrder seckillOrder);
	
	public List<SeckillOrder> list(List<Long> idList);
	
	public Map<Long, SeckillOrder> map(Set<Long> idSet);
	
	public Page<SeckillOrder> page(SeckillOrderQuery query, int start, int size);

	public List<SeckillOrder> listAll();
	
}
