package com.qcloud.component.personalcenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.qcloud.component.personalcenter.dao.MyWealthDao;
import com.qcloud.component.personalcenter.model.MyWealth;
import com.qcloud.component.personalcenter.model.query.MyWealthQuery;
import com.qcloud.component.personalcenter.util.RedisKeyUtils;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;

@Repository
public class MyWealthDaoRedisImpl implements MyWealthDao {

    @Resource(name = "redis-personalcenter")
    private Redis redis;

    @Override
    public boolean add(MyWealth myWealth) {

        redis.set(RedisKeyUtils.getWealthKey(myWealth.getUserId()), Json.toJson(myWealth), RedisKeyUtils.MYWEALTHTIMESECONDS);
        return true;
    }

    @Override
    public MyWealth get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MyWealth myWealth) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyWealth> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyWealth> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyWealth> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyWealth> page(MyWealthQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyWealth> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public boolean updateLock(MyWealth myWealth) {

        throw new NotImplementedException();
    }

    @Override
    public MyWealth getByUserId(long userId) {

        String str = redis.get(RedisKeyUtils.getWealthKey(userId));
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return Json.toObject(str, MyWealth.class, true);
    }

    @Override
    public boolean deleteByUser(Long userId) {

        redis.del(RedisKeyUtils.getWealthKey(userId));
        return true;
    }

    @Override
    public List<MyWealth> listTop(int number, int type) {
        throw new NotImplementedException();
    }
}
