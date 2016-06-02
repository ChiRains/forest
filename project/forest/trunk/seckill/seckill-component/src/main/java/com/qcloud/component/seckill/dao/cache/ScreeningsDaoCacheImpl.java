package com.qcloud.component.seckill.dao.cache;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.seckill.dao.ScreeningsDao;
import com.qcloud.component.seckill.model.Screenings;
import com.qcloud.component.seckill.model.query.ScreeningsQuery;

@Repository
public class ScreeningsDaoCacheImpl implements ScreeningsDao {

    @Autowired
    private ScreeningsDao screeningsDaoMysqlImpl;

    // @Autowired
    // private ScreeningsDao screeningsDaoRedisImpl;
    @Override
    public boolean add(Screenings screenings) {

        return screeningsDaoMysqlImpl.add(screenings);
    }

    @Override
    public Screenings get(Long id) {

        return screeningsDaoMysqlImpl.get(id);
        // return CacheLoader.get(screeningsDaoRedisImpl, screeningsDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return screeningsDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(Screenings screenings) {

        return screeningsDaoMysqlImpl.update(screenings);
    }

    @Override
    public List<Screenings> list(List<Long> idList) {

        return CacheLoader.list(screeningsDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, Screenings> map(Set<Long> idSet) {

        return CacheLoader.map(screeningsDaoMysqlImpl, idSet);
    }

    @Override
    public Page<Screenings> page(int start, int count) {

        return screeningsDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<Screenings> page(ScreeningsQuery query, int start, int count) {

        return screeningsDaoMysqlImpl.page(query, start, count);
    }

    public List<Screenings> listAll() {

        return screeningsDaoMysqlImpl.listAll();
    }

    @Override
    public List<Screenings> getRepeatByTime(Date beginTime, Date endTime) {
        return screeningsDaoMysqlImpl.getRepeatByTime(beginTime, endTime);
    }

    @Override
    public List<Screenings> listByDate(Date day) {
        return screeningsDaoMysqlImpl.listByDate(day);
    }
}
