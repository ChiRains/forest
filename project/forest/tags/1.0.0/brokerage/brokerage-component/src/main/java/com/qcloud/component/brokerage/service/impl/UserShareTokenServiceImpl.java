package com.qcloud.component.brokerage.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.brokerage.dao.UserShareTokenDao;
import com.qcloud.component.brokerage.model.UserShareToken;
import com.qcloud.component.brokerage.model.query.UserShareTokenQuery;
import com.qcloud.component.brokerage.service.UserShareTokenService;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.StringUtil;

@Service
public class UserShareTokenServiceImpl implements UserShareTokenService {
    @Autowired
    private UserShareTokenDao   userShareTokenDao;
    @Autowired
    private AutoIdGenerator     autoIdGenerator;
    private static final String ID_KEY = "distribution_user_share_token";

    @Override
    public boolean add(UserShareToken userShareToken) {
        long id = autoIdGenerator.get(ID_KEY);
        userShareToken.setId(id);
        return userShareTokenDao.add(userShareToken);
    }

    @Override
    public UserShareToken get(Long id) {
        return userShareTokenDao.get(id);
    }

    @Override
    public boolean delete(Long id) {
        return userShareTokenDao.delete(id);
    }

    @Override
    public boolean update(UserShareToken userShareToken) {
        return userShareTokenDao.update(userShareToken);
    }

    @Override
    public Page<UserShareToken> page(UserShareTokenQuery query, int start, int count) {
        return userShareTokenDao.page(query, start, count);
    }

    public List<UserShareToken> listAll() {
        return userShareTokenDao.listAll();
    }

    @Override
    public UserShareToken getByUser(Long userId) {
        UserShareToken userShareToken = userShareTokenDao.getByUser(userId);
        if (userShareToken == null) {
            userShareToken = new UserShareToken();
            userShareToken.setUserId(userId);
            userShareToken.setToken(StringUtil.uuid());
            add(userShareToken);
        }
        return userShareToken;
    }

    @Override
    public UserShareToken getByToken(String token) {
        return userShareTokenDao.getByToken(token);
    }
}
