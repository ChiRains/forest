package com.qcloud.component.personalcenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.model.query.UserQuery;

public interface UserDao extends ISimpleDao<User, Long> {

    public boolean add(User user);

    public User get(Long id);

    public boolean delete(Long id);

    public boolean update(User user);

    public List<User> list(List<Long> idList);

    public Map<Long, User> map(Set<Long> idSet);

    public Page<User> page(UserQuery query, int start, int size);

    public List<User> listAll();

    User getByMobile(String mobile);

    User getByEmail(String email);

    User getByMembershipCard(String membershipCard);
}
