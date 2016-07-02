package com.qcloud.component.marketing.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.marketing.dao.RecentDiscountDao;
import com.qcloud.component.marketing.model.RecentDiscount;
import com.qcloud.component.marketing.service.RecentDiscountService;
import com.qcloud.component.marketing.model.query.RecentDiscountQuery;

@Service
public class RecentDiscountServiceImpl implements RecentDiscountService {

    @Autowired
    private RecentDiscountDao   recentDiscountDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "marketing_recent_discount";

    @Override
    public boolean add(RecentDiscount recentDiscount) {

        long id = autoIdGenerator.get(ID_KEY);
        recentDiscount.setId(id);
        return recentDiscountDao.add(recentDiscount);
    }

    @Override
    public RecentDiscount get(Long id) {

        return recentDiscountDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return recentDiscountDao.delete(id);
    }

    @Override
    public boolean update(RecentDiscount recentDiscount) {

        return recentDiscountDao.update(recentDiscount);
    }

    @Override
    public Page<RecentDiscount> page(RecentDiscountQuery query, int start, int count) {

        return recentDiscountDao.page(query, start, count);
    }

    public List<RecentDiscount> listAll() {

        return recentDiscountDao.listAll();
    }

    @Override
    public List<RecentDiscount> list(RecentDiscountQuery query, int start, int size) {

        return recentDiscountDao.list(query, start, size);
    }

    @Override
    public int count(RecentDiscountQuery query) {

        return recentDiscountDao.count(query);
    }
}
