package com.qcloud.component.personalcenter.dao.redis;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.qcloud.component.personalcenter.dao.ActiveUserDao;
import com.qcloud.component.personalcenter.model.ActiveUser;
import com.qcloud.component.personalcenter.util.RedisKeyUtils;
import com.qcloud.pirates.data.redis.Redis;

@Repository
public class ActiveUserDaoRedisImpl implements ActiveUserDao {

    @Resource(name = "redis-personalcenter")
    private Redis redis;

    @Override
    public boolean add(ActiveUser activeUser) {

        redis.zadd(RedisKeyUtils.getActiveLoginUserKey(), activeUser.getTimes(), RedisKeyUtils.getActiveLoginUserItemKey(activeUser.getUserId()));
        return true;
    }

    @Override
    public ActiveUser get(long userId) {

        Double score = redis.zscore(RedisKeyUtils.getActiveLoginUserKey(), RedisKeyUtils.getActiveLoginUserItemKey(userId));
        if (score == null) {
            return null;
        }
        ActiveUser activeUser = new ActiveUser();
        activeUser.setTimes(score.intValue());
        activeUser.setUserId(userId);
        return activeUser;
    }

    @Override
    public boolean zincrby(long userId) {

        redis.zincrby(RedisKeyUtils.getActiveLoginUserKey(), 1, RedisKeyUtils.getActiveLoginUserItemKey(userId));
        return true;
    }
}
