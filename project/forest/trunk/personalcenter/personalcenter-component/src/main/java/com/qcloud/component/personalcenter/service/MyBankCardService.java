package com.qcloud.component.personalcenter.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.model.MyBankCard;
import com.qcloud.component.personalcenter.model.query.MyBankCardQuery;

public interface MyBankCardService {

    public boolean add(MyBankCard myBankCard);

    public MyBankCard get(Long id);

    public boolean delete(Long id);

    public boolean update(MyBankCard myBankCard);

    public Page<MyBankCard> page(MyBankCardQuery query, int start, int count);

    public List<MyBankCard> listAll();

    public List<MyBankCard> listByUser(long userId);
}
