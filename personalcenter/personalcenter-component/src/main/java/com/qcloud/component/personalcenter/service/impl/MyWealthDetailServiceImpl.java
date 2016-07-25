package com.qcloud.component.personalcenter.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.MyWealthDetailDao;
import com.qcloud.component.personalcenter.model.MyWealthDetail;
import com.qcloud.component.personalcenter.service.MyWealthDetailService;
import com.qcloud.component.personalcenter.model.query.MyWealthDetailQuery;

@Service
public class MyWealthDetailServiceImpl implements MyWealthDetailService {

    @Autowired
    private MyWealthDetailDao   myWealthDetailDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "personalcenter_my_wealth_detail";

    @Override
    public boolean add(MyWealthDetail myWealthDetail) {

        long id = autoIdGenerator.get(ID_KEY);
        myWealthDetail.setId(id);
        return myWealthDetailDao.add(myWealthDetail);
    }

    @Override
    public MyWealthDetail get(Long id) {

        return myWealthDetailDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return myWealthDetailDao.delete(id);
    }

    @Override
    public boolean update(MyWealthDetail myWealthDetail) {

        return myWealthDetailDao.update(myWealthDetail);
    }

    @Override
    public Page<MyWealthDetail> page(MyWealthDetailQuery query, int start, int count) {

        return myWealthDetailDao.page(query, start, count);
    }

    public List<MyWealthDetail> listAll() {

        return myWealthDetailDao.listAll();
    }

    @Override
    public Page<MyWealthDetail> getWealthDetails(Long wealthId, Long userId, int type, int start, int size) {

        return myWealthDetailDao.getWealthDetails(wealthId, userId, type, start, size);
    }

    @Override
    public List<MyWealthDetail> listByUser(Long wealthId, Long userId, Integer type, Integer detailType, int start, int size) {

        return myWealthDetailDao.listByUser(wealthId, userId, type, detailType, start, size);
    }

    @Override
    public List<MyWealthDetail> listByTime(Long wealthId, Long userId, Integer type, Date begin, Date end) {

        return myWealthDetailDao.listByTime(wealthId, userId, type, begin, end);
    }

    @Override
    public List<MyWealthDetail> listByUserAndTime(Long wealthId, Long userId, Integer type, Integer detailType, Date begin, Date end, int start, int size) {

        return myWealthDetailDao.listByUserAndTime(wealthId, userId, type, detailType, begin, end, start, size);
    }

    @Override
    public int countByUserAndTime(Long wealthId, Long userId, Integer type, Integer detailType, Date begin, Date end) {

        return myWealthDetailDao.countByUserAndTime(wealthId, userId, type, detailType, begin, end);
    }

    @Override
    public double sumByUserAndTime(long wealthId, long userId, int type, Integer detailType, Date begin, Date end, int start, int size) {

        return myWealthDetailDao.sumByUserAndTime(wealthId, userId, type, detailType, begin, end, start, size);
    }

    @Override
    public MyWealthDetail getByIdandUserId(Long id, Long userId) {

        return myWealthDetailDao.getByIdandUserId(id, userId);
    }
}
