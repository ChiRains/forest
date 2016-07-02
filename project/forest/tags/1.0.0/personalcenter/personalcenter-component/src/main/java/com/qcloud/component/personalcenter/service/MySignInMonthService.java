package com.qcloud.component.personalcenter.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.model.MySignInMonth;
import com.qcloud.component.personalcenter.model.query.MySignInMonthQuery;

public interface MySignInMonthService {

    public boolean add(MySignInMonth mySignInMonth);

    public MySignInMonth get(Long id);

    public MySignInMonth getByYearAndMonth(long userId, int year, int month);

    public boolean delete(Long id);

    public boolean update(MySignInMonth mySignInMonth);

    public Page<MySignInMonth> page(MySignInMonthQuery query, int start, int count);

    public List<MySignInMonth> listAll();
}
