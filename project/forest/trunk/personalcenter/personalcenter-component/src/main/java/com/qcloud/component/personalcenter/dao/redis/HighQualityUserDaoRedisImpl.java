package com.qcloud.component.personalcenter.dao.redis;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.qcloud.component.personalcenter.dao.HighQualityUserDao;
import com.qcloud.component.personalcenter.model.HighQualityUser;
import com.qcloud.component.personalcenter.util.RedisKeyUtils;
import com.qcloud.pirates.data.redis.Redis;

@Repository
public class HighQualityUserDaoRedisImpl implements HighQualityUserDao {

    @Resource(name = "redis-personalcenter")
    private Redis redis;

    @Override
    public boolean add(HighQualityUser highQualityUser) {

        redis.zadd(RedisKeyUtils.getHighQualityUserKey(), highQualityUser.getMoney(), RedisKeyUtils.getHighQualityUserItemKey(highQualityUser.getUserId()));
        return true;
    }

    @Override
    public boolean zincrby(HighQualityUser highQualityUser) {

        redis.zincrby(RedisKeyUtils.getHighQualityUserKey(), highQualityUser.getMoney(), RedisKeyUtils.getHighQualityUserItemKey(highQualityUser.getUserId()));
        return true;
    }

    @Override
    public HighQualityUser get(long userId) {

        Double score = redis.zscore(RedisKeyUtils.getHighQualityUserKey(), RedisKeyUtils.getHighQualityUserItemKey(userId));
        if (score == null) {
            return null;
        }
        HighQualityUser user = new HighQualityUser();
        user.setUserId(userId);
        user.setMoney(score);
        return user;
    }
}
