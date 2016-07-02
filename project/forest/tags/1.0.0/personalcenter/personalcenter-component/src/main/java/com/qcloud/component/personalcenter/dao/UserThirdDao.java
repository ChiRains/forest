package com.qcloud.component.personalcenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.personalcenter.model.UserThird;
import com.qcloud.component.personalcenter.model.key.TypeEnum.AccountType;
import com.qcloud.component.personalcenter.model.query.UserThirdQuery;

public interface UserThirdDao extends ISimpleDao<UserThird, Long> {

    public boolean add(UserThird userThird);

    UserThird getByThird(String thirdId, AccountType type);

    public UserThird get(Long id);

    public boolean delete(Long id);

    public boolean update(UserThird userThird);

    public List<UserThird> list(List<Long> idList);

    public Map<Long, UserThird> map(Set<Long> idSet);

    public Page<UserThird> page(UserThirdQuery query, int start, int size);

    public List<UserThird> listAll();

    UserThird getByUser(Long userId);
}
