package com.qcloud.component.personalcenter.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.model.MyCommissionWithdrawCash;
import com.qcloud.component.personalcenter.model.MyWealthDetail;
import com.qcloud.component.personalcenter.model.query.MyCommissionWithdrawCashQuery;

public interface MyCommissionWithdrawCashService {

    public boolean add(MyCommissionWithdrawCash myCommissionWithdrawCash);

    public MyCommissionWithdrawCash get(Long id);

    public boolean delete(Long id);

    public boolean update(MyCommissionWithdrawCash myCommissionWithdrawCash);

    public Page<MyCommissionWithdrawCash> page(MyCommissionWithdrawCashQuery query, int start, int count);

    public List<MyCommissionWithdrawCash> listAll();

    List<MyCommissionWithdrawCash> listByUser(Long userId,String checkTime, int start, int count);

    List<MyCommissionWithdrawCash> listWithdrawingByUser(Long userId, int start, int count);

    List<MyCommissionWithdrawCash> listWithdrawedByUser(Long userId, int start, int count);

    double statWithdrawingCommission(Long userId);

    double statWithdrawedCommission(Long userId);
    
}
