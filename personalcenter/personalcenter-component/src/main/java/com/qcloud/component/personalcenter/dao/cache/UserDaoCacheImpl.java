package com.qcloud.component.personalcenter.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.personalcenter.dao.UserDao;
import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.model.query.UserQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class UserDaoCacheImpl implements UserDao {

    @Autowired
    private UserDao userDaoMysqlImpl;

    @Autowired
    private UserDao userDaoRedisImpl;

    @Override
    public boolean add(User user) {

        return userDaoMysqlImpl.add(user);
    }

    @Override
    public User get(Long id) {

        return CacheLoader.get(userDaoRedisImpl, userDaoMysqlImpl, id);
        // return userDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        userDaoMysqlImpl.delete(id);
        userDaoRedisImpl.delete(id);
        return true;
    }

    @Override
    public boolean update(User user) {

        userDaoMysqlImpl.update(user);
        userDaoRedisImpl.delete(user.getId());
        return true;
    }

    @Override
    public List<User> list(List<Long> idList) {

        return CacheLoader.list(userDaoRedisImpl, userDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, User> map(Set<Long> idSet) {

        return CacheLoader.map(userDaoRedisImpl, userDaoMysqlImpl, idSet);
    }

    @Override
    public Page<User> page(int start, int count) {

        return userDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<User> page(UserQuery query, int start, int count) {

        return userDaoMysqlImpl.page(query, start, count);
    }

    public List<User> listAll() {

        return userDaoMysqlImpl.listAll();
    }

    @Override
    public User getByMobile(String mobile) {

        User user = userDaoRedisImpl.getByMobile(mobile);
        if (user == null) {
            user = userDaoMysqlImpl.getByMobile(mobile);
            if (user != null) {
                userDaoRedisImpl.add(user);
            }
        }
        return user;
    }

    @Override
    public User getByEmail(String email) {

        User user = userDaoRedisImpl.getByEmail(email);
        if (user == null) {
            user = userDaoMysqlImpl.getByEmail(email);
            if (user != null) {
                userDaoRedisImpl.add(user);
            }
        }
        return user;
    }

    @Override
    public User getByMembershipCard(String membershipCard) {

        User user = userDaoRedisImpl.getByMembershipCard(membershipCard);
        if (user == null) {
            user = userDaoMysqlImpl.getByMembershipCard(membershipCard);
            if (user != null) {
                userDaoRedisImpl.add(user);
            }
        }
        return user;
    }
}
