package com.qcloud.component.personalcenter.service;

import java.util.List;
import com.qcloud.component.personalcenter.model.UserThird;
import com.qcloud.component.personalcenter.model.key.TypeEnum.AccountType;
import com.qcloud.component.personalcenter.model.query.UserThirdQuery;
import com.qcloud.pirates.data.Page;

public interface UserThirdService {

    public boolean add(UserThird userThird);

    UserThird getByThird(String thirdId, AccountType type);

    // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public UserThird get(Long id);

    public UserThird getByUser(Long userId);

    public boolean delete(Long id);

    public boolean update(UserThird userThird);

    public Page<UserThird> page(UserThirdQuery query, int start, int count);

    public List<UserThird> listAll();
    // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}
