package com.qcloud.component.seckill.dao.redis;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.seckill.dao.ScreeningsDao;
import com.qcloud.component.seckill.model.Screenings;
import com.qcloud.component.seckill.model.query.ScreeningsQuery;

@Repository
public class ScreeningsDaoRedisImpl implements ScreeningsDao {

    // @Resource(name = "redis-seckill")
    // private Redis redis;
    @Override
    public boolean add(Screenings screenings) {

        throw new NotImplementedException();
    }

    @Override
    public Screenings get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(Screenings screenings) {

        throw new NotImplementedException();
    }

    @Override
    public List<Screenings> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Screenings> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Screenings> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Screenings> page(ScreeningsQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<Screenings> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<Screenings> getRepeatByTime(Date beginTime, Date endTime) {
        throw new NotImplementedException();
    }

    @Override
    public List<Screenings> listByDate(Date day) {
        throw new NotImplementedException();
    }
}
