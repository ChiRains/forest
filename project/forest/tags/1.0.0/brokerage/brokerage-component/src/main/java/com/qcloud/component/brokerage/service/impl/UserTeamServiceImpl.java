package com.qcloud.component.brokerage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.brokerage.dao.UserTeamDao;
import com.qcloud.component.brokerage.model.UserTeam;
import com.qcloud.component.brokerage.model.query.UserTeamQuery;
import com.qcloud.component.brokerage.service.UserTeamService;
import com.qcloud.pirates.data.Page;

@Service
public class UserTeamServiceImpl implements UserTeamService {

    @Autowired
    private UserTeamDao         userTeamDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "distribution_user_team";

    @Transactional
    @Override
    public boolean add(UserTeam userTeam) {

        long id = autoIdGenerator.get(ID_KEY);
        userTeam.setId(id);
        return userTeamDao.add(userTeam);
    }

    @Override
    public UserTeam get(Long id) {

        return userTeamDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return userTeamDao.delete(id);
    }

    @Override
    public boolean update(UserTeam userTeam) {

        return userTeamDao.update(userTeam);
    }

    @Override
    public Page<UserTeam> page(UserTeamQuery query, int start, int count) {

        return userTeamDao.page(query, start, count);
    }

    public List<UserTeam> listAll() {

        return userTeamDao.listAll();
    }

    @Override
    public UserTeam getByUserId(long userId) {

        return userTeamDao.getByUserId(userId);
    }

    @Override
    public boolean isInLeader(long userId, long leader) {

        if (userId == leader) {
            return true;
        }
        long user = userId;
        UserTeam ut = null;
        do {
            ut = getByUserId(user);
            if (ut != null) {
                if (ut.getLeader() == leader) {
                    return true;
                }
                user = ut.getLeader();
            }
        } while (ut != null);
        return false;
    }

    @Override
    public List<UserTeam> listChildren(long leader) {

        return userTeamDao.listChildren(leader);
    }

    @Override
    public int countChildren(long leader) {

        return userTeamDao.countChildren(leader);
    }

    @Override
    public Page<UserTeam> pageLeader(UserTeamQuery query, int start, int count) {

        return userTeamDao.pageLeader(query, start, count);
    }
}
