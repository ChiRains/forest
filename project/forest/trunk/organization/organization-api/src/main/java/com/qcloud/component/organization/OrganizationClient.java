package com.qcloud.component.organization;

import java.util.List;
import com.qcloud.pirates.data.Page;

public interface OrganizationClient {

    String CLERK_LOGIN_PARAMETER_KEY = "organization-login-clerk";

    Page<QClerk> query(String name, Long departmentId, int start, int count);

    // 员工档案录入,并添加到组织机构信息中
    Long registClerk(String name, String mobile, Long departmentId, String jobEmail, String idCard, String password);

    boolean setName(Long clerkId, String name);

    boolean setJobEmail(Long clerkId, String jobEmail);

    boolean setSex(Long clerkId, int sex);

    boolean setHeadImage(Long clerkId, String headImage);

    boolean setInside(Long clerkId, String inside);

    boolean frozenClerk(Long clerkId);

    boolean activateClerk(Long clerkId);

    // 设置职工上级
    boolean setClerkLeader(Long clerkId, Long leaderClerkId);

    // 设置职工岗位// 换岗,则先找系统管理员把职员原权限干掉
    boolean setClerkPost(Long clerkId, Long postId);

    QClerk getClerk(Long id);

    Long getClerkIdByMobile(String mobile);

    QDepartment getDepartment(Long id);

    QDepartment getParentDepartment(Long id);

    QPost getPost(Long id);

    List<Long> listAllClerkIds();

    QSuperior getSuperior(Long clerkId);

    List<QClerk> listAllClerk();

    public boolean existByMobile(String mobile);

    public boolean existByIdCard(String idCard);

    public boolean existByJobEmail(String email);

    public boolean sendMsg(long clerkId, ClerkMessageType type, String title, String content);

    public Long sendMsgForId(long clerkId, ClerkMessageType type, String title, String content);

    public List<QPost> listPost();
}