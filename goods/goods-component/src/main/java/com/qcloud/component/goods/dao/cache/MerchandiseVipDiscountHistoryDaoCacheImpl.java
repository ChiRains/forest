package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseVipDiscountHistoryDao;
import com.qcloud.component.goods.model.MerchandiseVipDiscountHistory;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountHistoryQuery;

@Repository
public class MerchandiseVipDiscountHistoryDaoCacheImpl implements MerchandiseVipDiscountHistoryDao {
	
	@Autowired
	private MerchandiseVipDiscountHistoryDao merchandiseVipDiscountHistoryDaoMysqlImpl;
	
	@Autowired
	private MerchandiseVipDiscountHistoryDao merchandiseVipDiscountHistoryDaoRedisImpl;

	@Override
	public boolean add(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory) {
		return merchandiseVipDiscountHistoryDaoMysqlImpl.add(merchandiseVipDiscountHistory);
	}

	@Override
	public MerchandiseVipDiscountHistory get(Long id) {
		return CacheLoader.get(merchandiseVipDiscountHistoryDaoRedisImpl, merchandiseVipDiscountHistoryDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return merchandiseVipDiscountHistoryDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory){
		return merchandiseVipDiscountHistoryDaoMysqlImpl.update(merchandiseVipDiscountHistory);
	}
	
	@Override
	public List<MerchandiseVipDiscountHistory> list(List<Long> idList) {
		return CacheLoader.list(merchandiseVipDiscountHistoryDaoRedisImpl, merchandiseVipDiscountHistoryDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MerchandiseVipDiscountHistory> map(Set<Long> idSet){
		return CacheLoader.map(merchandiseVipDiscountHistoryDaoRedisImpl, merchandiseVipDiscountHistoryDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MerchandiseVipDiscountHistory> page(int start, int count){
		return merchandiseVipDiscountHistoryDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MerchandiseVipDiscountHistory> page(MerchandiseVipDiscountHistoryQuery query, int start, int count){
		return merchandiseVipDiscountHistoryDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MerchandiseVipDiscountHistory> listAll(){
		return merchandiseVipDiscountHistoryDaoMysqlImpl.listAll();
	}
}

