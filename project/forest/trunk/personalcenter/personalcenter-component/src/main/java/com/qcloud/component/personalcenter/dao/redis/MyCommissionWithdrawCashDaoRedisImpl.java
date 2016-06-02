package com.qcloud.component.personalcenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.personalcenter.dao.MyCommissionWithdrawCashDao;
import com.qcloud.component.personalcenter.model.MyCommissionWithdrawCash;
import com.qcloud.component.personalcenter.model.query.MyCommissionWithdrawCashQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MyCommissionWithdrawCashDaoRedisImpl implements MyCommissionWithdrawCashDao {

	//@Resource(name = "redis-personalcenter")
	//private Redis redis;

	@Override
	public boolean add(MyCommissionWithdrawCash myCommissionWithdrawCash) {			
		throw new NotImplementedException();
	}

	@Override
	public MyCommissionWithdrawCash get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MyCommissionWithdrawCash myCommissionWithdrawCash){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MyCommissionWithdrawCash> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MyCommissionWithdrawCash> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MyCommissionWithdrawCash> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MyCommissionWithdrawCash> page(MyCommissionWithdrawCashQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MyCommissionWithdrawCash> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<MyCommissionWithdrawCash> listByUser(Long userId,String checkTime, int start, int count) {
        throw new NotImplementedException();
    }

    @Override
    public double statWithdrawingCommission(Long userId) {
        throw new NotImplementedException();
    }

    @Override
    public double statWithdrawedCommission(Long userId) {
        throw new NotImplementedException();
    }

    @Override
    public List<MyCommissionWithdrawCash> listWithdrawingByUser(Long userId, int start, int count) {
        throw new NotImplementedException();
    }

    @Override
    public List<MyCommissionWithdrawCash> listWithdrawedByUser(Long userId, int start, int count) {
        throw new NotImplementedException();
    }
}

