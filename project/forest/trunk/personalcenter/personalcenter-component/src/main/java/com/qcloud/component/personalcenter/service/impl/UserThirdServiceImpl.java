package com.qcloud.component.personalcenter.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.UserThirdDao;
import com.qcloud.component.personalcenter.model.UserThird;
import com.qcloud.component.personalcenter.service.UserThirdService;
import com.qcloud.component.personalcenter.model.key.TypeEnum.AccountType;
import com.qcloud.component.personalcenter.model.query.UserThirdQuery;

@Service
public class UserThirdServiceImpl implements UserThirdService {

    @Autowired
    private UserThirdDao        userThirdDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "personalcenter_user_third";

    @Override
    public boolean add(UserThird userThird) {

        long id = autoIdGenerator.get(ID_KEY);
        userThird.setId(id);
        return userThirdDao.add(userThird);
    }

    @Override
    public UserThird get(Long id) {

        return userThirdDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return userThirdDao.delete(id);
    }

    @Override
    public boolean update(UserThird userThird) {

        return userThirdDao.update(userThird);
    }

    @Override
    public Page<UserThird> page(UserThirdQuery query, int start, int count) {

        return userThirdDao.page(query, start, count);
    }

    public List<UserThird> listAll() {

        return userThirdDao.listAll();
    }

    @Override
    public UserThird getByThird(String thirdId, AccountType type) {

        return userThirdDao.getByThird(thirdId, type);
    }

    @Override
    public UserThird getByUser(Long userId) {

        return userThirdDao.getByUser(userId);
    }
}
