package com.qcloud.component.seckill.service;

import java.util.Date;
import java.util.List;

import com.qcloud.component.seckill.model.Screenings;
import com.qcloud.component.seckill.model.query.ScreeningsQuery;
import com.qcloud.pirates.data.Page;

public interface ScreeningsService {

    public boolean add(Screenings screenings);

    public Screenings get(Long id);

    public boolean delete(Long id);

    public boolean update(Screenings screenings);

    public Page<Screenings> page(ScreeningsQuery query, int start, int count);

    public List<Screenings> listAll();

    public List<Screenings> listToday(Integer size);

    public Screenings calculate(List<Screenings> list);
	
	public List<Screenings> getRepeatByTime(Date beginTime, Date endTime);
}
