package com.qcloud.component.personalcenter.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.personalcenter.dao.MyBankCardDao;
import com.qcloud.component.personalcenter.model.MyBankCard;
import com.qcloud.component.personalcenter.model.query.MyBankCardQuery;
import com.qcloud.component.personalcenter.service.MyBankCardService;
import com.qcloud.pirates.data.Page;

@Service
public class MyBankCardServiceImpl implements MyBankCardService {

    @Autowired
    private MyBankCardDao       myBankCardDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "personalcenter_my_bank_card";

    @Override
    public boolean add(MyBankCard myBankCard) {

        long id = autoIdGenerator.get(ID_KEY);
        myBankCard.setId(id);
        myBankCard.setTime(new Date());
        return myBankCardDao.add(myBankCard);
    }

    @Override
    public MyBankCard get(Long id) {

        return myBankCardDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return myBankCardDao.delete(id);
    }

    @Override
    public boolean update(MyBankCard myBankCard) {

        return myBankCardDao.update(myBankCard);
    }

    @Override
    public Page<MyBankCard> page(MyBankCardQuery query, int start, int count) {

        return myBankCardDao.page(query, start, count);
    }

    public List<MyBankCard> listAll() {

        return myBankCardDao.listAll();
    }

    @Override
    public List<MyBankCard> listByUser(long userId) {

        return myBankCardDao.listByUser(userId);
    }
}
