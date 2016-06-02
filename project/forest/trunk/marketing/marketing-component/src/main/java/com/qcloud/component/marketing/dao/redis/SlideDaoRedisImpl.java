package com.qcloud.component.marketing.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.marketing.dao.SlideDao;
import com.qcloud.component.marketing.model.Slide;
import com.qcloud.component.marketing.model.query.SlideQuery;

@Repository
public class SlideDaoRedisImpl implements SlideDao {

    // @Resource(name = "redis-marketing")
    // private Redis redis;
    @Override
    public boolean add(Slide slide) {

        throw new NotImplementedException();
    }

    @Override
    public Slide get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(Slide slide) {

        throw new NotImplementedException();
    }

    @Override
    public List<Slide> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Slide> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Slide> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Slide> page(SlideQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<Slide> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<Slide> listBySence(int sence) {

        throw new NotImplementedException();
    }
}
