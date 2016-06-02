package com.qcloud.component.orderform.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.orderform.model.ExchangeOrderItemDetail;
import com.qcloud.component.orderform.model.query.ExchangeOrderItemDetailQuery;
		
public interface ExchangeOrderItemDetailDao extends ISimpleDao<ExchangeOrderItemDetail, Long> {

	public boolean add(ExchangeOrderItemDetail exchangeOrderItemDetail);	
	
	public ExchangeOrderItemDetail get(Long id);

	public boolean delete(Long id);
	
	public boolean update(ExchangeOrderItemDetail exchangeOrderItemDetail);
	
	public List<ExchangeOrderItemDetail> list(List<Long> idList);
	
	public Map<Long, ExchangeOrderItemDetail> map(Set<Long> idSet);
	
	public Page<ExchangeOrderItemDetail> page(ExchangeOrderItemDetailQuery query, int start, int size);

	public List<ExchangeOrderItemDetail> listAll();
	
	List<ExchangeOrderItemDetail> listByExchange(Long exchangeId);	
}
