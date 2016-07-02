package com.qcloud.component.seckill.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.seckill.dao.ScreeningsDao;
import com.qcloud.component.seckill.model.Screenings;
import com.qcloud.component.seckill.model.query.ScreeningsQuery;
import com.qcloud.component.seckill.service.ScreeningsService;
import com.qcloud.pirates.data.Page;

@Service
public class ScreeningsServiceImpl implements ScreeningsService {

    @Autowired
    private ScreeningsDao       screeningsDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "seckill_screenings";

    @Override
    public boolean add(Screenings screenings) {

        long id = autoIdGenerator.get(ID_KEY);
        screenings.setId(id);
        screenings.setEnable(1);
        return screeningsDao.add(screenings);
    }

    @Override
    public Screenings get(Long id) {

        return screeningsDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return screeningsDao.delete(id);
    }

    @Override
    public boolean update(Screenings screenings) {

        return screeningsDao.update(screenings);
    }

    @Override
    public Page<Screenings> page(ScreeningsQuery query, int start, int count) {
    	if (query.getBeginTime() == null) {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		query.setBeginTime(sdf.format(new Date()));
    	}
        return screeningsDao.page(query, start, count);
    }

    public List<Screenings> listAll() {

        return screeningsDao.listAll();
    }

    @Override
    public List<Screenings> listToday(Integer size) {

        List<Screenings> list = screeningsDao.listByDate(new Date());
        List<Screenings> reList = new ArrayList<Screenings>();
        if (list.size() < size) {
            reList = list;
        } else {
            Screenings lastEnd = null;
            Date now = new Date();
            for (Screenings screenings : list) {
                if (screenings.getEndTime().before(now)) {
                    if (lastEnd == null) {
                        lastEnd = screenings;
                    } else if (lastEnd.getBeginTime().before(screenings.getBeginTime())) {
                        lastEnd = screenings;
                    }
                }
            }
            if (lastEnd == null) {
                reList.addAll(list.subList(0, size));
            } else {
                int index = list.indexOf(lastEnd);
                index = list.size() - index < size ? list.size() - size : index;
                reList.addAll(list.subList(index, index + size));
            }
        }
        return reList;
    }

    @Override
    public Screenings calculate(List<Screenings> list) {

        Date now = new Date();
        Screenings current = null;
        for (int index = 0; index < list.size(); index++) {
            Screenings screenings = list.get(index);
            if (screenings.getBeginTime().after(now)) {
                current = screenings;
                break;
            } else if (screenings.getBeginTime().before(now) && screenings.getEndTime().after(now)) {
                current = screenings;
                break;
            } else if (index == list.size() - 1) {
                current = screenings;
                break;
            }
        }
        return current;
    }

    @Override
    public List<Screenings> getRepeatByTime(Date beginTime, Date endTime) {
        return screeningsDao.getRepeatByTime(beginTime, endTime);
    }
}
