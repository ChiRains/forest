package com.qcloud.component.personalcenter.dao.redis;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.qcloud.component.personalcenter.dao.RecentlyLoginUserDao;
import com.qcloud.component.personalcenter.model.RecentlyLoginUser;
import com.qcloud.component.personalcenter.util.RedisKeyUtils;
import com.qcloud.pirates.data.redis.Redis;

@Repository
public class RecentlyLoginUserDaoRedisImpl implements RecentlyLoginUserDao {

    @Resource(name = "redis-personalcenter")
    private Redis redis;

    @Override
    public boolean add(RecentlyLoginUser recentlyLoginUser) {

        recentlyLoginUser.setLoginTime(new Date());
        redis.zadd(RedisKeyUtils.getRecentlyLoginUserKey(), recentlyLoginUser.getLoginTime().getTime(), RedisKeyUtils.getRecentlyLoginUserItemKey(recentlyLoginUser.getUserId()));
        return true;
    }

    @Override
    public boolean relet(long userId) {

        Date now = new Date();
        redis.zadd(RedisKeyUtils.getRecentlyLoginUserKey(), now.getTime(), RedisKeyUtils.getRecentlyLoginUserItemKey(userId));
        return true;
    }
}
