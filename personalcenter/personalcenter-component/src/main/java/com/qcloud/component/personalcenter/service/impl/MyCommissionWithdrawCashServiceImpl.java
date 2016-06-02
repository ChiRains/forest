package com.qcloud.component.personalcenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.MyCommissionWithdrawCashDao;
import com.qcloud.component.personalcenter.model.MyCommissionWithdrawCash;
import com.qcloud.component.personalcenter.service.MyCommissionWithdrawCashService;
import com.qcloud.component.personalcenter.model.query.MyCommissionWithdrawCashQuery;
		
@Service
public class MyCommissionWithdrawCashServiceImpl implements MyCommissionWithdrawCashService{
	
	@Autowired
	private MyCommissionWithdrawCashDao myCommissionWithdrawCashDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "personalcenter_my_commission_withdraw_cash";

	@Override
	public boolean add(MyCommissionWithdrawCash myCommissionWithdrawCash) {
		long id = autoIdGenerator.get(ID_KEY);
		myCommissionWithdrawCash.setId(id);
		
		return myCommissionWithdrawCashDao.add(myCommissionWithdrawCash);
	}

	@Override
	public MyCommissionWithdrawCash get(Long id) {	
		return myCommissionWithdrawCashDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return myCommissionWithdrawCashDao.delete(id);
	}
	
	@Override
	public boolean update(MyCommissionWithdrawCash myCommissionWithdrawCash) {
		return myCommissionWithdrawCashDao.update(myCommissionWithdrawCash);
	}
	
	@Override
	public Page<MyCommissionWithdrawCash> page(MyCommissionWithdrawCashQuery query, int start, int count) {
		return myCommissionWithdrawCashDao.page(query, start, count);
	}
	
	public List<MyCommissionWithdrawCash> listAll(){
		return myCommissionWithdrawCashDao.listAll();
	}

    @Override
    public List<MyCommissionWithdrawCash> listByUser(Long userId,String checkTime, int start, int count) {
        return myCommissionWithdrawCashDao.listByUser(userId, checkTime, start, count);
    }

    @Override
    public double statWithdrawingCommission(Long userId) {
        return myCommissionWithdrawCashDao.statWithdrawingCommission(userId);
    }

    @Override
    public double statWithdrawedCommission(Long userId) {
        return myCommissionWithdrawCashDao.statWithdrawedCommission(userId);
    }

    @Override
    public List<MyCommissionWithdrawCash> listWithdrawingByUser(Long userId, int start, int count) {
        return myCommissionWithdrawCashDao.listWithdrawingByUser(userId, start, count);
    }

    @Override
    public List<MyCommissionWithdrawCash> listWithdrawedByUser(Long userId, int start, int count) {
        return myCommissionWithdrawCashDao.listWithdrawedByUser(userId, start, count);
    }
}

