package com.qcloud.component.marketing.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.marketing.dao.RecentDiscountDao;
import com.qcloud.component.marketing.model.RecentDiscount;
import com.qcloud.component.marketing.model.query.RecentDiscountQuery;

@Repository
public class RecentDiscountDaoCacheImpl implements RecentDiscountDao {
	
	@Autowired
	private RecentDiscountDao recentDiscountDaoMysqlImpl;
	
	@Autowired
	private RecentDiscountDao recentDiscountDaoRedisImpl;

	@Override
	public boolean add(RecentDiscount recentDiscount) {
		return recentDiscountDaoMysqlImpl.add(recentDiscount);
	}

	@Override
	public RecentDiscount get(Long id) {
		return recentDiscountDaoMysqlImpl.get(id);
	}

	@Override
	public boolean delete(Long id){
		return recentDiscountDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(RecentDiscount recentDiscount){
		return recentDiscountDaoMysqlImpl.update(recentDiscount);
	}
	
	@Override
	public List<RecentDiscount> list(List<Long> idList) {
		return CacheLoader.list(recentDiscountDaoRedisImpl, recentDiscountDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, RecentDiscount> map(Set<Long> idSet){
		return CacheLoader.map(recentDiscountDaoRedisImpl, recentDiscountDaoMysqlImpl, idSet);
	}

	@Override
	public Page<RecentDiscount> page(int start, int count){
		return recentDiscountDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<RecentDiscount> page(RecentDiscountQuery query, int start, int count){
		return recentDiscountDaoMysqlImpl.page(query, start, count);
	}
	
	public List<RecentDiscount> listAll(){
		return recentDiscountDaoMysqlImpl.listAll();
	}

    @Override
    public List<RecentDiscount> list(RecentDiscountQuery query, int start, int size) {

        return recentDiscountDaoMysqlImpl.list(query, start, size);
    }

    @Override
    public int count(RecentDiscountQuery query) {

        return recentDiscountDaoMysqlImpl.count(query);
    }
}

