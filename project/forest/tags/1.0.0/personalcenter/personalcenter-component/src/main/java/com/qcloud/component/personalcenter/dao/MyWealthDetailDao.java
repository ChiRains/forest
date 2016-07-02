package com.qcloud.component.personalcenter.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.personalcenter.model.MyWealthDetail;
import com.qcloud.component.personalcenter.model.query.MyWealthDetailQuery;

public interface MyWealthDetailDao extends ISimpleDao<MyWealthDetail, Long> {

    public boolean add(MyWealthDetail myWealthDetail);

    public MyWealthDetail get(Long id);

    public boolean delete(Long id);

    public boolean update(MyWealthDetail myWealthDetail);

    public List<MyWealthDetail> list(List<Long> idList);

    public Map<Long, MyWealthDetail> map(Set<Long> idSet);

    public Page<MyWealthDetail> page(MyWealthDetailQuery query, int start, int size);

    public List<MyWealthDetail> listAll();

    public Page<MyWealthDetail> getWealthDetails(Long wealthId, Long userId, int type, int start, int size);

    List<MyWealthDetail> listByUser(Long wealthId, Long userId, Integer type, Integer detailType, int start, int size);

    List<MyWealthDetail> listByTime(Long wealthId, Long userId, Integer type, Date begin, Date end);

    public List<MyWealthDetail> listByUserAndTime(Long wealthId, Long userId, Integer type, Integer detailType, Date begin, Date end, int start, int size);

    public double sumByUserAndTime(long wealthId, long userId, int type, Integer detailType, Date begin, Date end, int start, int size);
    
    MyWealthDetail getByIdandUserId(Long id, Long userId);
}
