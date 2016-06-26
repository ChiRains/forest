package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseBrowsingHistoryDao;
import com.qcloud.component.goods.model.MerchandiseBrowsingHistory;
import com.qcloud.component.goods.model.query.MerchandiseBrowsingHistoryQuery;

@Repository
public class MerchandiseBrowsingHistoryDaoCacheImpl implements MerchandiseBrowsingHistoryDao {
	
	@Autowired
	private MerchandiseBrowsingHistoryDao merchandiseBrowsingHistoryDaoMysqlImpl;
	
	@Autowired
	private MerchandiseBrowsingHistoryDao merchandiseBrowsingHistoryDaoRedisImpl;

	@Override
	public boolean add(MerchandiseBrowsingHistory merchandiseBrowsingHistory) {
		return merchandiseBrowsingHistoryDaoMysqlImpl.add(merchandiseBrowsingHistory);
	}

	@Override
	public MerchandiseBrowsingHistory get(Long id) {
		return CacheLoader.get(merchandiseBrowsingHistoryDaoRedisImpl, merchandiseBrowsingHistoryDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return merchandiseBrowsingHistoryDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseBrowsingHistory merchandiseBrowsingHistory){
		return merchandiseBrowsingHistoryDaoMysqlImpl.update(merchandiseBrowsingHistory);
	}
	
	@Override
	public List<MerchandiseBrowsingHistory> list(List<Long> idList) {
		return CacheLoader.list(merchandiseBrowsingHistoryDaoRedisImpl, merchandiseBrowsingHistoryDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MerchandiseBrowsingHistory> map(Set<Long> idSet){
		return CacheLoader.map(merchandiseBrowsingHistoryDaoRedisImpl, merchandiseBrowsingHistoryDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MerchandiseBrowsingHistory> page(int start, int count){
		return merchandiseBrowsingHistoryDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MerchandiseBrowsingHistory> page(MerchandiseBrowsingHistoryQuery query, int start, int count){
		return merchandiseBrowsingHistoryDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MerchandiseBrowsingHistory> listAll(){
		return merchandiseBrowsingHistoryDaoMysqlImpl.listAll();
	}
}

