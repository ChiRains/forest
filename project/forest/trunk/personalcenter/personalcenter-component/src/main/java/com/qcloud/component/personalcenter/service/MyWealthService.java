package com.qcloud.component.personalcenter.service;

import java.util.List;
import com.qcloud.component.personalcenter.model.MyWealth;
import com.qcloud.component.personalcenter.model.query.MyWealthQuery;
import com.qcloud.pirates.data.Page;

public interface MyWealthService {

    MyWealth getByUserId(long userId);

    boolean calculateWealth(long userId, double wealth, int type, String desc);

    boolean withdrawCommission(long userId, Long bankCardId, double cash);

    public boolean add(MyWealth myWealth);

    public MyWealth get(Long id);

    public boolean delete(Long id);

    public boolean update(MyWealth myWealth);

    public Page<MyWealth> page(MyWealthQuery query, int start, int count);

    public List<MyWealth> listAll();

    boolean integralToCurrency(long userId, int integral);

    List<MyWealth> listTop(int number, int type);
}
