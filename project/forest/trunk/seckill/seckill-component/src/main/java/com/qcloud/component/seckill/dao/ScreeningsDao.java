package com.qcloud.component.seckill.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.seckill.model.Screenings;
import com.qcloud.component.seckill.model.query.ScreeningsQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface ScreeningsDao extends ISimpleDao<Screenings, Long> {

    public boolean add(Screenings screenings);

    public Screenings get(Long id);

    List<Screenings> listByDate(Date day);

    public boolean delete(Long id);

    public boolean update(Screenings screenings);

    public List<Screenings> list(List<Long> idList);

    public Map<Long, Screenings> map(Set<Long> idSet);

    public Page<Screenings> page(ScreeningsQuery query, int start, int size);

    public List<Screenings> listAll();

    public List<Screenings> getRepeatByTime(Date beginTime, Date endTime);
}
