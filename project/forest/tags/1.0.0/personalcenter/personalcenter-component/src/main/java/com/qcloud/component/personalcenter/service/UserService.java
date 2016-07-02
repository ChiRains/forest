package com.qcloud.component.personalcenter.service;

import java.util.List;
import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.model.query.UserQuery;
import com.qcloud.pirates.data.Page;

public interface UserService {

    public boolean add(User user, String pwd);

    public User get(Long id);

    public boolean delete(Long id);

    public boolean update(User user); 

    public boolean changePwd(Long id, String pwd);

    public boolean changeMobile(Long id, String mobile);

    public boolean changeEmail(Long id, String email);

    public boolean setMembershipCard(Long id, String membershipCard);

    public boolean calculateGrade(Long id);

    public Page<User> page(UserQuery query, int start, int count);

    public List<User> listAll();

    User getByAccount(String account);

    boolean isUser(String account, String password);

    String isUser(Long id, String password);

    boolean isEnableUser(User user);

    // String getEncodePsw(String psw);
    boolean resetPwd(Long userId);
}
