package com.qcloud.component.personalcenter;

import java.util.List;
import com.qcloud.pirates.data.Page;

public interface PersonalcenterClient {

    String USER_LOGIN_PARAMETER_KEY    = "personalcenter-login-user";

    String USER_IS_LOGIN_PARAMETER_KEY = "personalcenter-is-login-user";

    //
    Long addUser(String mobile, String cardNumber, String name, String pwd);

    boolean activateUser(long userId);

    QUser getUser(long userId);

    QUser getUser(String account);

    boolean sendMsg(long userId, UserMessageType type, String title, String content);

    String getThirdId(Long userId);

    Page<QUser> userPage(String name, int start, int pAGE_SIZE);

    boolean editSex(Long userId, int sex);

    boolean editHeadImage(Long userId, String headImage);

    boolean editUser(long userId, String mobile, String name);

    List<QUser> userListAll();

    boolean existAndUselessMembershipCard(String cardNumber);

    boolean calculateMyWealth(long userId, WealthType type, double cash, boolean needProportion, String desc);

    QMyWealth getMyWealth(long userId);
    
    boolean editUserParam(long userId,String name,String nickName,String email,String headImage,int sex,String mobile, String province,String city,String district,String address);
}
