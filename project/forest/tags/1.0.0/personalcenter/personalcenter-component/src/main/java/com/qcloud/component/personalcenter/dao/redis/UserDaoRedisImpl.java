package com.qcloud.component.personalcenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.personalcenter.dao.UserDao;
import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.model.query.UserQuery;
import com.qcloud.component.personalcenter.util.RedisKeyUtils;

@Repository
public class UserDaoRedisImpl implements UserDao {

    @Resource(name = "redis-personalcenter")
    private Redis redis;

    @Override
    public boolean add(User user) {

        redis.set(RedisKeyUtils.getUserIdKey(user.getId()), Json.toJson(user));
        if (StringUtils.isNotEmpty(user.getMobile())) {
            redis.set(RedisKeyUtils.getUserMobileKey(user.getMobile()), String.valueOf(user.getId()));
        }
        if (StringUtils.isNotEmpty(user.getEmail())) {
            redis.set(RedisKeyUtils.getUserEmailKey(user.getEmail()), String.valueOf(user.getId()));
        }
        if (StringUtils.isNotEmpty(user.getMembershipCard())) {
            redis.set(RedisKeyUtils.getUserMembershipCardKey(user.getMembershipCard()), String.valueOf(user.getId()));
        }
        return true;
    }

    @Override
    public User get(Long id) {

        String str = redis.get(RedisKeyUtils.getUserIdKey(id));
        if (str == null) {
            return null;
        }
        return Json.toObject(str, User.class, true);
    }

    @Override
    public boolean delete(Long id) {

        User user = get(id);
        if (user == null) {
            return true;
        }
        redis.del(RedisKeyUtils.getUserIdKey(id));
        if (StringUtils.isNotEmpty(user.getMobile())) {
            redis.del(RedisKeyUtils.getUserMobileKey(user.getMobile()));
        }
        if (StringUtils.isNotEmpty(user.getEmail())) {
            redis.del(RedisKeyUtils.getUserEmailKey(user.getEmail()));
        }
        if (StringUtils.isNotEmpty(user.getMembershipCard())) {
            redis.del(RedisKeyUtils.getUserMembershipCardKey(user.getMembershipCard()));
        }
        return true;
    }

    @Override
    public boolean update(User user) {

        delete(user.getId());
        return true;
    }

    @Override
    public List<User> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, User> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<User> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<User> page(UserQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<User> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public User getByMobile(String mobile) {

        String str = redis.get(RedisKeyUtils.getUserMobileKey(mobile));
        if (str == null) {
            return null;
        }
        long userId = Long.parseLong(str);
        return get(userId);
    }

    @Override
    public User getByEmail(String email) {

        String str = redis.get(RedisKeyUtils.getUserEmailKey(email));
        if (str == null) {
            return null;
        }
        long userId = Long.parseLong(str);
        return get(userId);
    }

    @Override
    public User getByMembershipCard(String membershipCard) {

        String str = redis.get(RedisKeyUtils.getUserMembershipCardKey(membershipCard));
        if (str == null) {
            return null;
        }
        long userId = Long.parseLong(str);
        return get(userId);
    }
}
