package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MonthHotSaleDao;
import com.qcloud.component.goods.model.MonthHotSale;
import com.qcloud.component.goods.model.query.MonthHotSaleQuery;

@Repository
public class MonthHotSaleDaoCacheImpl implements MonthHotSaleDao {
	
	@Autowired
	private MonthHotSaleDao monthHotSaleDaoMysqlImpl;
	
	@Autowired
	private MonthHotSaleDao monthHotSaleDaoRedisImpl;

	@Override
	public boolean add(MonthHotSale monthHotSale) {
		return monthHotSaleDaoMysqlImpl.add(monthHotSale);
	}

	@Override
	public MonthHotSale get(Long id) {
		return CacheLoader.get(monthHotSaleDaoRedisImpl, monthHotSaleDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return monthHotSaleDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MonthHotSale monthHotSale){
		return monthHotSaleDaoMysqlImpl.update(monthHotSale);
	}
	
	@Override
	public List<MonthHotSale> list(List<Long> idList) {
		return CacheLoader.list(monthHotSaleDaoRedisImpl, monthHotSaleDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MonthHotSale> map(Set<Long> idSet){
		return CacheLoader.map(monthHotSaleDaoRedisImpl, monthHotSaleDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MonthHotSale> page(int start, int count){
		return monthHotSaleDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MonthHotSale> page(MonthHotSaleQuery query, int start, int count){
		return monthHotSaleDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MonthHotSale> listAll(){
		return monthHotSaleDaoMysqlImpl.listAll();
	}
}

