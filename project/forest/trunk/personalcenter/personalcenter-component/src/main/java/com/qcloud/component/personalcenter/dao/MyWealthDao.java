package com.qcloud.component.personalcenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.personalcenter.model.MyWealth;
import com.qcloud.component.personalcenter.model.query.MyWealthQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface MyWealthDao extends ISimpleDao<MyWealth, Long> {

    public boolean add(MyWealth myWealth);

    public MyWealth get(Long id);

    public boolean delete(Long id);

    public boolean deleteByUser(Long id);

    public boolean updateLock(MyWealth myWealth);

    public boolean update(MyWealth myWealth);

    public List<MyWealth> list(List<Long> idList);

    public Map<Long, MyWealth> map(Set<Long> idSet);

    public Page<MyWealth> page(MyWealthQuery query, int start, int size);

    public List<MyWealth> listAll();

    MyWealth getByUserId(long userId);

    List<MyWealth> listTop(int number, int type);
}
