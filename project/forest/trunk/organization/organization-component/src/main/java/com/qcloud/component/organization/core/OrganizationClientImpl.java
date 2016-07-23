package com.qcloud.component.organization.core;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.organization.ClerkMessageType;
import com.qcloud.component.organization.OrganizationClient;
import com.qcloud.component.organization.QClerk;
import com.qcloud.component.organization.QDepartment;
import com.qcloud.component.organization.QPost;
import com.qcloud.component.organization.QSuperior;
import com.qcloud.component.organization.entity.ClerkEntity;
import com.qcloud.component.organization.entity.DepartmentEntity;
import com.qcloud.component.organization.entity.PostEntity;
import com.qcloud.component.organization.entity.SuperiorEntity;
import com.qcloud.component.organization.exception.OrganizationException;
import com.qcloud.component.organization.model.Clerk;
import com.qcloud.component.organization.model.ClerkPost;
import com.qcloud.component.organization.model.Department;
import com.qcloud.component.organization.model.DepartmentClerk;
import com.qcloud.component.organization.model.DepartmentImage;
import com.qcloud.component.organization.model.Post;
import com.qcloud.component.organization.model.PostRole;
import com.qcloud.component.organization.model.Superior;
import com.qcloud.component.organization.model.key.TypeEnum;
import com.qcloud.component.organization.model.key.TypeEnum.DepartmentClerkType;
import com.qcloud.component.organization.model.key.TypeEnum.EnableType;
import com.qcloud.component.organization.service.ClerkPostService;
import com.qcloud.component.organization.service.ClerkService;
import com.qcloud.component.organization.service.DepartmentClerkService;
import com.qcloud.component.organization.service.DepartmentImageService;
import com.qcloud.component.organization.service.DepartmentService;
import com.qcloud.component.organization.service.PostRoleService;
import com.qcloud.component.organization.service.PostService;
import com.qcloud.component.organization.service.SuperiorService;
import com.qcloud.component.permission.AccountClient;
import com.qcloud.component.permission.PermissionClient;
import com.qcloud.component.permission.model.Account;
import com.qcloud.component.publicservice.MessageClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Component
public class OrganizationClientImpl implements OrganizationClient {

    @Autowired
    DepartmentService      departmentService;

    @Autowired
    ClerkService           clerkService;

    @Autowired
    DepartmentClerkService departmentClerkService;

    @Autowired
    SuperiorService        superiorService;

    @Autowired
    PostService            postService;

    @Autowired
    ClerkPostService       clerkPostService;

    @Autowired
    PostRoleService        postRoleService;

    @Autowired
    AccountClient          accountClient;

    @Autowired
    PermissionClient       permissionClient;

    @Autowired
    MessageClient          messageClient;

    @Autowired
    DepartmentImageService departmentImageService;

    @Autowired
    FileSDKClient          fileSDKClient;

    @Override
    public Long registClerk(String name, String mobile, Long departmentId, String jobEmail, String idCard, String password) {

        Department department = departmentService.get(departmentId);
        AssertUtil.assertNotNull(department, "部门不存在." + departmentId);
        Clerk clerk = new Clerk();
        clerk.setName(name);
        clerk.setMobile(mobile);
        clerk.setJobEmail(jobEmail);
        clerk.setIdCard(idCard);
        clerkService.add(clerk);
        //
        if (StringUtils.isNotEmpty(password)) {
            clerkService.changePwd(clerk.getId(), password);
        }
        DepartmentClerk departmentClerk = new DepartmentClerk();
        departmentClerk.setType(DepartmentClerkType.BELONGS.getKey());
        departmentClerk.setClerkId(clerk.getId());
        departmentClerk.setDepartmentId(departmentId);
        departmentClerkService.add(departmentClerk);
        return clerk.getId();
    }

    @Override
    public boolean setClerkLeader(Long clerkId, Long leaderClerkId) {

        AssertUtil.assertNotNull(clerkId, "职员不能为空.");
        Clerk clerk = clerkService.get(clerkId);
        AssertUtil.assertNotNull(clerk, "职员不存在." + clerkId);
        AssertUtil.assertNotNull(leaderClerkId, "职员上级不能为空.");
        Clerk leader = clerkService.get(leaderClerkId);
        AssertUtil.assertNotNull(leader, "职员上级不存在." + leaderClerkId);
        Superior s = superiorService.getByClerk(clerkId);
        if (s == null) {
            Superior superior = new Superior();
            superior.setClerkId(clerkId);
            superior.setLeaderId(leaderClerkId);
            return superiorService.add(superior);
        } else {
            s.setLeaderId(leaderClerkId);
            return superiorService.update(s);
        }
    }

    // 只管加,不管减
    @Override
    public boolean setClerkPost(Long clerkId, Long postId) {

        Clerk clerk = clerkService.get(clerkId);
        AssertUtil.assertNotNull(clerk, "职员不存在." + clerkId);
        Post post = postService.get(postId);
        AssertUtil.assertNotNull(post, "岗位不存在." + postId);
        // 验证岗位尚未和用户关联
        List<ClerkPost> list = clerkPostService.listByClerk(clerkId);
        for (ClerkPost clerkPost : list) {
            if (clerkPost.getPostId() == post.getId()) {
                return false;
            }
        }
        // 添加用户和岗位关系
        ClerkPost clerkPost = new ClerkPost();
        clerkPost.setClerkId(clerkId);
        clerkPost.setPostId(postId);
        boolean result = clerkPostService.add(clerkPost);
        AssertUtil.assertTrue(result, "设置用户岗位失败.");
        // 授权
        grantAccount(clerk.getMobile(), postId);
        grantAccount(clerk.getJobEmail(), postId);
        grantAccount(clerk.getIdCard(), postId);
        return result;
    }

    private void grantAccount(String accountStr, Long postId) {

        if (StringUtils.isEmpty(accountStr)) {
            return;
        }
        String str = clerkService.getClerkPermissionAccountCode(accountStr);
        Account account = accountClient.getAccount(str);
        if (account == null) {
            return;
        }
        List<PostRole> rpList = postRoleService.listByPost(postId);
        for (PostRole postRole : rpList) {
            permissionClient.grant(account.getId(), postRole.getRoleId());
        }
    }

    @Override
    public Page<QClerk> query(String name, Long departmentId, int start, int count) {

        departmentId = departmentId == null ? 0L : departmentId;
        Department department = departmentService.get(departmentId);
        Page<QClerk> page = new Page<QClerk>();
        if (StringUtils.isNotEmpty(name) || department == null) {
            List<Clerk> clerkList = clerkService.listByName(name);
            List<Clerk> dcList = new ArrayList<Clerk>();
            if (department != null) {
                List<DepartmentClerk> list = departmentClerkService.listByDepartment(departmentId);
                for (Clerk clerk : clerkList) {
                    for (DepartmentClerk departmentClerk : list) {
                        if (clerk.getId() == departmentClerk.getClerkId()) {
                            dcList.add(clerk);
                            break;
                        }
                    }
                }
            } else {
                dcList = clerkList;
            }
            List<QClerk> result = new ArrayList<QClerk>();
            for (int index = start; index < dcList.size() && index < start + count; index++) {
                Clerk clerk = dcList.get(index);
                ClerkEntity ce = new ClerkEntity();
                ce.setMobile(clerk.getMobile());
                ce.setName(clerk.getName());
                ce.setId(clerk.getId());
                DepartmentClerk departmentClerk = departmentClerkService.getBelongsDepartment(clerk.getId());
                AssertUtil.assertNotNull(departmentClerk, "用户没有设置部门." + clerk.getName());
                ce.setDepartmentId(departmentClerk.getDepartmentId());
                List<ClerkPost> clerkPostList = clerkPostService.listByClerk(clerk.getId());
                AssertUtil.assertTrue(clerkPostList.size() > 0, "用户没有设置岗位." + clerk.getName());
                AssertUtil.assertTrue(clerkPostList.size() == 1, "用户有多个岗位." + clerk.getName());
                ce.setPostId(clerkPostList.get(0).getPostId());
                result.add(ce);
            }
            page.setData(result);
            page.setCount(dcList.size());
        } else {
            List<DepartmentClerk> list = departmentClerkService.listByDepartment(departmentId);
            List<QClerk> result = new ArrayList<QClerk>();
            for (int index = start; index < list.size() && index < start + count; index++) {
                Clerk clerk = clerkService.get(list.get(index).getClerkId());
                if (clerk.getEnable() == EnableType.DISABLE.getKey()) {
                    continue;
                }
                AssertUtil.assertNotNull(clerk, "" + list.get(index).getClerkId());
                ClerkEntity ce = new ClerkEntity();
                ce.setMobile(clerk.getMobile());
                ce.setName(clerk.getName());
                ce.setId(clerk.getId());
                DepartmentClerk departmentClerk = departmentClerkService.getBelongsDepartment(clerk.getId());
                ce.setDepartmentId(departmentClerk.getDepartmentId());
                List<ClerkPost> clerkPostList = clerkPostService.listByClerk(clerk.getId());
                if (CollectionUtils.isEmpty(clerkPostList) || clerkPostList.size() > 1) {
                    throw new OrganizationException("用户有多个岗位." + clerk.getName());
                }
                ce.setPostId(clerkPostList.get(0).getPostId());
                result.add(ce);
            }
            page.setData(result);
            page.setCount(list.size());
        }
        return page;
    }

    @Override
    public QDepartment getDepartment(Long id) {

        Department department = departmentService.get(id);
        AssertUtil.assertNotNull(department, "部门不存在." + id);
        return getDepartment(department);
    }

    @Override
    public QPost getPost(Long id) {

        Post post = postService.get(id);
        PostEntity pe = new PostEntity();
        pe.setId(id);
        pe.setName(post.getName());
        return pe;
    }

    // @Override
    // public Map<Long, QClerk> mapClerkAll(ClerkQuery clerkQuery) {
    //
    // Map<Long, QClerk> map = new HashMap<Long, QClerk>();
    // for (Clerk clerk : clerkService.listAll(BeanUtils.transBean2Map(clerkQuery))) {
    // ClerkEntity clerkEntity = new ClerkEntity();
    // clerkEntity.setMobile(clerk.getMobile());
    // clerkEntity.setName(clerk.getName());
    // clerkEntity.setId(clerk.getId());
    // DepartmentClerk departmentClerk = departmentClerkService.getBelongsDepartment(clerk.getId());
    // if (departmentClerk == null) {
    // throw new OrganizationException(clerk.getName() + "(" + clerk.getMobile() + ")" + ".未配置部门信息!");
    // }
    // clerkEntity.setDepartmentId(departmentClerk.getDepartmentId());
    // map.put(clerkEntity.getId(), clerkEntity);
    // }
    // return map;
    // }
    //
    // @Override
    // public List<DepartmentClerk> listDepartmentClerkAll() {
    //
    // return departmentClerkService.listAll();
    // }
    //
    // @Override
    // public Map<Long, Department> mapDepartmentAll() {
    //
    // Map<Long, Department> map = new HashMap<Long, Department>();
    // for (Department department : departmentService.listAll()) {
    // map.put(department.getId(), department);
    // }
    // return map;
    // }
    @Override
    public List<Long> listAllClerkIds() {

        List<Long> list = new ArrayList<Long>();
        for (Clerk clerk : clerkService.listAll()) {
            list.add(clerk.getId());
        }
        return list;
    }

    @Override
    public QClerk getClerk(Long id) {

        Clerk clerk = clerkService.get(id);
        AssertUtil.assertNotNull(clerk, "职员不存在." + id);
        return toEntity(clerk);
    }

    @Override
    public DepartmentEntity getParentDepartment(Long id) {

        Department department = departmentService.get(id);
        AssertUtil.assertNotNull(department, "部门不存在." + id);
        long parentId = department.getParentId();
        Department parent = departmentService.get(parentId);
        if (parent == null) {
            return null;
        }
        return getDepartment(parent);
    }

    @Override
    public QSuperior getSuperior(Long clerkId) {

        Superior superior = superiorService.getByClerk(clerkId);
        SuperiorEntity superiorEntity = null;
        if (superior != null) {
            superiorEntity = new SuperiorEntity();
            Clerk clerk = clerkService.get(clerkId);
            Clerk leaderClerk = clerkService.get(superior.getLeaderId());
            superiorEntity.setId(superior.getId());
            superiorEntity.setClerkId(clerk.getId());
            superiorEntity.setClerkName(clerk.getName());
            superiorEntity.setLeaderId(leaderClerk.getId());
            superiorEntity.setLeaderName(leaderClerk.getName());
        }
        return superiorEntity;
    }

    @Override
    public List<QClerk> listAllClerk() {

        List<Clerk> clerkList = clerkService.listAll();
        List<QClerk> qc = new ArrayList<QClerk>();
        for (Clerk clerk : clerkList) {
            qc.add(toEntity(clerk));
        }
        return qc;
    }

    private QClerk toEntity(Clerk clerk) {

        ClerkEntity clerkEntity = new ClerkEntity();
        clerkEntity.setMobile(clerk.getMobile());
        clerkEntity.setName(clerk.getName());
        clerkEntity.setId(clerk.getId());
        clerkEntity.setInside(clerk.getInside());
        DepartmentClerk departmentClerk = departmentClerkService.getBelongsDepartment(clerk.getId());
        AssertUtil.assertNotNull(departmentClerk, clerk.getName() + ".未配置部门信息!");
        clerkEntity.setDepartmentId(departmentClerk.getDepartmentId());
        QDepartment department = getDepartment(departmentClerk.getDepartmentId());
        clerkEntity.setDepartmentName(department.getName());
        List<ClerkPost> clerkPostList = clerkPostService.listByClerk(clerk.getId());
        if (CollectionUtils.isNotEmpty(clerkPostList)) {
            clerkEntity.setPostId(clerkPostList.get(0).getPostId());
        } else {
            clerkEntity.setPostId(-1L);
        }
        clerkEntity.setHeadImage(clerk.getHeadImage());
        clerkEntity.setSex(clerk.getSex());
        clerkEntity.setJobEmail(clerk.getJobEmail());
        return clerkEntity;
    }

    @Override
    public boolean setSex(Long clerkId, int sex) {

        Clerk clerk = clerkService.get(clerkId);
        AssertUtil.assertNotNull(clerk, "职员不存在." + clerkId);
        clerk.setSex(sex);
        return clerkService.update(clerk);
    }

    @Override
    public boolean setHeadImage(Long clerkId, String headImage) {

        Clerk clerk = clerkService.get(clerkId);
        AssertUtil.assertNotNull(clerk, "职员不存在." + clerkId);
        clerk.setHeadImage(headImage);
        return clerkService.update(clerk);
    }

    @Override
    public boolean setName(Long clerkId, String name) {

        Clerk clerk = clerkService.get(clerkId);
        AssertUtil.assertNotNull(clerk, "职员不存在." + clerkId);
        clerk.setName(name);
        return clerkService.update(clerk);
    }

    @Override
    public boolean setJobEmail(Long clerkId, String jobEmail) {

        Clerk clerk = clerkService.get(clerkId);
        AssertUtil.assertNotNull(clerk, "职员不存在." + clerkId);
        clerk.setJobEmail(jobEmail);
        return clerkService.update(clerk);
    }

    @Override
    public boolean setInside(Long clerkId, String inside) {

        Clerk clerk = clerkService.get(clerkId);
        AssertUtil.assertNotNull(clerk, "职员不存在." + clerkId);
        clerk.setInside(inside);
        return clerkService.update(clerk);
    }

    @Override
    public boolean existByMobile(String mobile) {

        Clerk clerk = clerkService.getByMobile(mobile);
        return clerk == null;
    }

    @Override
    public boolean existByIdCard(String idCard) {

        Clerk clerk = clerkService.getByIdCard(idCard);
        return clerk == null;
    }

    @Override
    public boolean existByJobEmail(String email) {

        Clerk clerk = clerkService.getByJobEmail(email);
        return clerk == null;
    }

    @Override
    public boolean sendMsg(long userId, ClerkMessageType type, String title, String content) {

        return messageClient.sendMsg(TypeEnum.CLERK_MESSAGE_CODE, type.getKey(), userId, title, content);
    }

    @Override
    public Long sendMsgForId(long clerkId, ClerkMessageType type, String title, String content) {

        return messageClient.sendMsgForId(TypeEnum.CLERK_MESSAGE_CODE, type.getKey(), clerkId, title, content);
    }

    @Override
    public List<QPost> listPost() {

        List<Post> postList = postService.listAll();
        List<QPost> list = new ArrayList<QPost>();
        for (Post post : postList) {
            PostEntity postEntity = new PostEntity();
            postEntity.setId(post.getId());
            postEntity.setName(post.getName());
            list.add(postEntity);
        }
        return list;
    }

    @Override
    public boolean frozenClerk(Long clerkId) {

        Clerk clerk = clerkService.get(clerkId);
        AssertUtil.assertNotNull(clerk, "职员不存在.");
        clerk.setEnable(EnableType.DISABLE.getKey());
        return clerkService.update(clerk);
    }

    @Override
    public boolean activateClerk(Long clerkId) {

        Clerk clerk = clerkService.get(clerkId);
        AssertUtil.assertNotNull(clerk, "职员不存在.");
        clerk.setEnable(EnableType.ENABLE.getKey());
        return clerkService.update(clerk);
    }

    @Override
    public Long getClerkIdByMobile(String mobile) {

        Clerk clerk = clerkService.getByMobile(mobile);
        if (clerk == null) {
            return -1L;
        }
        return clerk.getId();
    }

    @Override
    public QDepartment getDepartmentByCode(String code) {

        Department department = departmentService.getByCode(code);
        AssertUtil.assertNotNull(department, "部门不存在." + code);
        return getDepartment(department);
    }

    @Override
    public List<QDepartment> listDepartmantByParent(long parentId) {

        List<Department> list = departmentService.listByParent(parentId);
        List<QDepartment> result = new ArrayList<QDepartment>();
        for (Department department : list) {
            result.add(getDepartment(department));
        }
        return result;
    }

    private DepartmentEntity getDepartment(Department department) {

        if (department == null) {
            return null;
        }
        long parentId = department.getParentId();
        Department parent = departmentService.get(parentId);
        if (parent == null) {
            return new DepartmentEntity(department, null);
        }
        DepartmentEntity pe = getDepartment(parent);
        return new DepartmentEntity(department, pe);
    }

    @Override
    public QDepartment getByManager(long manager) {

        Department department = departmentService.getByManager(manager);
        return getDepartment(department);
    }

    @Override
    public List<String> listDepartmentImages(Long departmentId) {

        List<String> strings = new ArrayList<String>();
        List<DepartmentImage> departmentImages = departmentImageService.listByDepartmentId(departmentId);
        for (DepartmentImage departmentImage : departmentImages) {
            strings.add(fileSDKClient.getFileServerUrl() + departmentImage.getImage());
        }
        return strings;
    }
}