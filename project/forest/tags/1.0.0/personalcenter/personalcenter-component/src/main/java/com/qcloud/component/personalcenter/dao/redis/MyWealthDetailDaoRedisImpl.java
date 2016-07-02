package com.qcloud.component.personalcenter.dao.redis;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.personalcenter.dao.MyWealthDetailDao;
import com.qcloud.component.personalcenter.model.MyWealthDetail;
import com.qcloud.component.personalcenter.model.query.MyWealthDetailQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;

@Repository
public class MyWealthDetailDaoRedisImpl implements MyWealthDetailDao {

    @Resource(name = "redis-personalcenter")
    private Redis redis;

    @Override
    public boolean add(MyWealthDetail myWealthDetail) {

        throw new NotImplementedException();
    }

    @Override
    public MyWealthDetail get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MyWealthDetail myWealthDetail) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyWealthDetail> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyWealthDetail> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyWealthDetail> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyWealthDetail> page(MyWealthDetailQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyWealthDetail> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyWealthDetail> getWealthDetails(Long wealthId, Long userId, int type, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyWealthDetail> listByUser(Long wealthId, Long userId, Integer type, Integer detailType, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyWealthDetail> listByTime(Long wealthId, Long userId, Integer type, Date begin, Date end) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyWealthDetail> listByUserAndTime(Long wealthId, Long userId, Integer type, Integer detailType, Date begin, Date end, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public double sumByUserAndTime(long wealthId, long userId, int type, Integer detailType, Date begin, Date end, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public MyWealthDetail getByIdandUserId(Long id, Long userId) {
        throw new NotImplementedException();

    }
}
