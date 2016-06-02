package com.qcloud.component.personalcenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.personalcenter.model.MyCommissionWithdrawCash;
import com.qcloud.component.personalcenter.model.query.MyCommissionWithdrawCashQuery;

public interface MyCommissionWithdrawCashDao extends ISimpleDao<MyCommissionWithdrawCash, Long> {

    public boolean add(MyCommissionWithdrawCash myCommissionWithdrawCash);

    public MyCommissionWithdrawCash get(Long id);

    public boolean delete(Long id);

    public boolean update(MyCommissionWithdrawCash myCommissionWithdrawCash);

    public List<MyCommissionWithdrawCash> list(List<Long> idList);

    public Map<Long, MyCommissionWithdrawCash> map(Set<Long> idSet);

    public Page<MyCommissionWithdrawCash> page(MyCommissionWithdrawCashQuery query, int start, int size);

    public List<MyCommissionWithdrawCash> listAll();

    List<MyCommissionWithdrawCash> listByUser(Long userId,String checkTime, int start, int count);

    List<MyCommissionWithdrawCash> listWithdrawingByUser(Long userId, int start, int count);

    List<MyCommissionWithdrawCash> listWithdrawedByUser(Long userId, int start, int count);

    double statWithdrawingCommission(Long userId);

    double statWithdrawedCommission(Long userId);
}
