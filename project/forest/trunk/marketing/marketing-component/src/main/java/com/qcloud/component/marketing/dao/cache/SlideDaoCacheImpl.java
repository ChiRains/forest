package com.qcloud.component.marketing.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.marketing.dao.SlideDao;
import com.qcloud.component.marketing.model.Slide;
import com.qcloud.component.marketing.model.query.SlideQuery;

@Repository
public class SlideDaoCacheImpl implements SlideDao {

    @Autowired
    private SlideDao slideDaoMysqlImpl;

    // @Autowired
    // private SlideDao slideDaoRedisImpl;
    @Override
    public boolean add(Slide slide) {

        return slideDaoMysqlImpl.add(slide);
    }

    @Override
    public Slide get(Long id) {

        return slideDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return slideDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(Slide slide) {

        return slideDaoMysqlImpl.update(slide);
    }

    @Override
    public List<Slide> list(List<Long> idList) {

        return CacheLoader.list(slideDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, Slide> map(Set<Long> idSet) {

        return CacheLoader.map(slideDaoMysqlImpl, idSet);
    }

    @Override
    public Page<Slide> page(int start, int count) {

        return slideDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<Slide> page(SlideQuery query, int start, int count) {

        return slideDaoMysqlImpl.page(query, start, count);
    }

    public List<Slide> listAll() {

        return slideDaoMysqlImpl.listAll();
    }

    @Override
    public List<Slide> listBySence(int sence) {

        return slideDaoMysqlImpl.listBySence(sence);
    }
}
