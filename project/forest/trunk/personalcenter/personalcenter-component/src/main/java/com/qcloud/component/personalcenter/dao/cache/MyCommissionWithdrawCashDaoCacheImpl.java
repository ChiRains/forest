package com.qcloud.component.personalcenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.MyCommissionWithdrawCashDao;
import com.qcloud.component.personalcenter.model.MyCommissionWithdrawCash;
import com.qcloud.component.personalcenter.model.query.MyCommissionWithdrawCashQuery;

@Repository
public class MyCommissionWithdrawCashDaoCacheImpl implements MyCommissionWithdrawCashDao {
	
	@Autowired
	private MyCommissionWithdrawCashDao myCommissionWithdrawCashDaoMysqlImpl;
	
//	@Autowired
//	private MyCommissionWithdrawCashDao myCommissionWithdrawCashDaoRedisImpl;

	@Override
	public boolean add(MyCommissionWithdrawCash myCommissionWithdrawCash) {
		return myCommissionWithdrawCashDaoMysqlImpl.add(myCommissionWithdrawCash);
	}

	@Override
	public MyCommissionWithdrawCash get(Long id) {
	    return myCommissionWithdrawCashDaoMysqlImpl.get(id);
//		return CacheLoader.get(myCommissionWithdrawCashDaoRedisImpl, myCommissionWithdrawCashDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return myCommissionWithdrawCashDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MyCommissionWithdrawCash myCommissionWithdrawCash){
		return myCommissionWithdrawCashDaoMysqlImpl.update(myCommissionWithdrawCash);
	}
	
	@Override
	public List<MyCommissionWithdrawCash> list(List<Long> idList) {
		return CacheLoader.list(myCommissionWithdrawCashDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MyCommissionWithdrawCash> map(Set<Long> idSet){
		return CacheLoader.map(myCommissionWithdrawCashDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MyCommissionWithdrawCash> page(int start, int count){
		return myCommissionWithdrawCashDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MyCommissionWithdrawCash> page(MyCommissionWithdrawCashQuery query, int start, int count){
		return myCommissionWithdrawCashDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MyCommissionWithdrawCash> listAll(){
		return myCommissionWithdrawCashDaoMysqlImpl.listAll();
	}

    @Override
    public List<MyCommissionWithdrawCash> listByUser(Long userId,String checkTime, int start, int count) {
        return myCommissionWithdrawCashDaoMysqlImpl.listByUser(userId, checkTime, start, count);
    }

    @Override
    public double statWithdrawingCommission(Long userId) {
        return myCommissionWithdrawCashDaoMysqlImpl.statWithdrawingCommission(userId);
    }

    @Override
    public double statWithdrawedCommission(Long userId) {
        return myCommissionWithdrawCashDaoMysqlImpl.statWithdrawedCommission(userId);
    }

    @Override
    public List<MyCommissionWithdrawCash> listWithdrawingByUser(Long userId, int start, int count) {
        return myCommissionWithdrawCashDaoMysqlImpl.listWithdrawingByUser(userId, start, count);
    }

    @Override
    public List<MyCommissionWithdrawCash> listWithdrawedByUser(Long userId, int start, int count) {
        return myCommissionWithdrawCashDaoMysqlImpl.listWithdrawedByUser(userId, start, count);
    }
}

