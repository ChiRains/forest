package com.qcloud.component.personalcenter.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.personalcenter.model.MySignInDay;
import com.qcloud.component.personalcenter.model.query.MySignInDayQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface MySignInDayDao extends ISimpleDao<MySignInDay, Long> {

    public boolean add(MySignInDay mySignInDay);

    public MySignInDay get(Long id);

    public boolean delete(Long id);

    public boolean update(MySignInDay mySignInDay);

    public List<MySignInDay> list(List<Long> idList);

    public Map<Long, MySignInDay> map(Set<Long> idSet);

    public Page<MySignInDay> page(MySignInDayQuery query, int start, int size);

    public List<MySignInDay> listAll();

    List<MySignInDay> listByYearAndMonth(long userId, int year, int month);

    public MySignInDay getByDate(long userId, Date date);
}
