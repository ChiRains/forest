package com.qcloud.component.personalcenter.service;

import java.util.List;
import com.qcloud.component.personalcenter.model.MySignInDay;
import com.qcloud.component.personalcenter.model.MySignInRuleConfig;
import com.qcloud.component.personalcenter.model.query.MySignInDayQuery;
import com.qcloud.pirates.data.Page;

public interface MySignInDayService {

    public boolean signIn(long userId);

    public boolean add(MySignInDay mySignInDay);

    public MySignInDay get(Long id);

    public boolean delete(Long id);

    public boolean update(MySignInDay mySignInDay);

    public Page<MySignInDay> page(MySignInDayQuery query, int start, int count);

    public List<MySignInDay> listAll();

    public List<MySignInDay> listByYearAndMonth(long userId, int year, int month);

    public int calculateIntegral(int signNumber, MySignInRuleConfig mySignInRuleConfig);

    public int calculateNextDay(int signNumber, MySignInRuleConfig mySignInRuleConfig);

    public int calculateNextIntegral(int signNumber, MySignInRuleConfig mySignInRuleConfig);
}
