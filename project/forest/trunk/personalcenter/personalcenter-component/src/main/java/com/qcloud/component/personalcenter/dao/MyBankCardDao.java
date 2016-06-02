package com.qcloud.component.personalcenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.personalcenter.model.MyBankCard;
import com.qcloud.component.personalcenter.model.query.MyBankCardQuery;

public interface MyBankCardDao extends ISimpleDao<MyBankCard, Long> {

    public boolean add(MyBankCard myBankCard);

    public MyBankCard get(Long id);

    public boolean delete(Long id);

    public boolean update(MyBankCard myBankCard);

    public List<MyBankCard> list(List<Long> idList);

    public Map<Long, MyBankCard> map(Set<Long> idSet);

    public Page<MyBankCard> page(MyBankCardQuery query, int start, int size);

    public List<MyBankCard> listAll();

    List<MyBankCard> listByUser(long userId);
}
