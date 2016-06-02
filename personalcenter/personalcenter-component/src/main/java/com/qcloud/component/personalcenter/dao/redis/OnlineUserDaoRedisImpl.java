package com.qcloud.component.personalcenter.dao.redis;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.qcloud.component.personalcenter.dao.OnlineUserDao;
import com.qcloud.component.personalcenter.model.OnlineUser;
import com.qcloud.component.personalcenter.util.RedisKeyUtils;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.pirates.util.DateUtil;

@Repository
public class OnlineUserDaoRedisImpl implements OnlineUserDao {

    @Resource(name = "redis-personalcenter")
    private Redis redis;

    @Override
    public boolean add(OnlineUser onlineUser) {

        onlineUser.setLastVisitTime(new Date());
        redis.zadd(RedisKeyUtils.getOnlineUserKey(), onlineUser.getLastVisitTime().getTime(), RedisKeyUtils.getOnlineUserItemKey(onlineUser.getUserId()));
        return true;
    }

    @Override
    public long count() {

        Date now = new Date();
        Date startEffectiveDate = DateUtil.addDate(now, -15);
        return redis.zcount(RedisKeyUtils.getOnlineUserKey(), startEffectiveDate.getTime(), now.getTime());
    }

    @Override
    public boolean relet(long userId) {

        Date now = new Date();
        redis.zadd(RedisKeyUtils.getOnlineUserKey(), now.getTime(), RedisKeyUtils.getOnlineUserItemKey(userId));
        return true;
    }
}
