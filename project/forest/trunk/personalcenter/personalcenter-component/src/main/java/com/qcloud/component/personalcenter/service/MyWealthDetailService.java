package com.qcloud.component.personalcenter.service;

import java.util.Date;
import java.util.List;
import com.qcloud.component.personalcenter.model.MyWealthDetail;
import com.qcloud.component.personalcenter.model.query.MyWealthDetailQuery;
import com.qcloud.pirates.data.Page;

public interface MyWealthDetailService {

    public boolean add(MyWealthDetail myWealthDetail);

    public MyWealthDetail get(Long id);

    public boolean delete(Long id);

    public boolean update(MyWealthDetail myWealthDetail);

    public Page<MyWealthDetail> page(MyWealthDetailQuery query, int start, int count);

    public List<MyWealthDetail> listAll();

    public Page<MyWealthDetail> getWealthDetails(Long wealthId, Long userId, int type, int start, int size);

    public List<MyWealthDetail> listByUser(Long wealthId, Long userId, Integer type, Integer detailType, int start, int size);

    public List<MyWealthDetail> listByTime(Long wealthId, Long userId, Integer type, Date begin, Date end);

    public List<MyWealthDetail> listByUserAndTime(Long wealthId, Long userId, Integer type, Integer detailType, Date begin, Date end, int start, int size);

    public int countByUserAndTime(Long wealthId, Long userId, Integer type, Integer detailType, Date begin, Date end);

    public double sumByUserAndTime(long wealthId, long userId, int type, Integer detailType, Date beginTime, Date endTime, int start, int size);

    public MyWealthDetail getByIdandUserId(Long id, Long userId);
}
