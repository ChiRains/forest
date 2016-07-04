//package com.qcloud.component.sellercenter.service.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.qcloud.component.account.UnifiedAccountClient;
//import com.qcloud.component.autoid.AutoIdGenerator;
//import com.qcloud.component.autoid.UniqueCodeGenerator;
//import com.qcloud.component.parameter.ParameterClient;
//import com.qcloud.component.permission.AccountClient;
//import com.qcloud.component.permission.model.Account;
//import com.qcloud.component.publicdata.EnableType;
//import com.qcloud.component.sellercenter.dao.MemberDao;
//import com.qcloud.component.sellercenter.exception.SellerCenterException;
//import com.qcloud.component.sellercenter.model.Member;
//import com.qcloud.component.sellercenter.model.Merchant;
//import com.qcloud.component.sellercenter.model.query.MemberQuery;
//import com.qcloud.component.sellercenter.service.MemberService;
//import com.qcloud.component.sellercenter.service.MerchantService;
//import com.qcloud.pirates.data.Page;
//
//@Service
//public class MemberServiceImpl implements MemberService {
//
//    @Autowired
//    private MemberDao            memberDao;
//
//    @Autowired
//    private AutoIdGenerator      autoIdGenerator;
//
//    private static final String  ID_KEY                      = "sellercenter_member";
//
//    @Autowired
//    private ParameterClient      parameterClient;
//
//    //
//    private static final String  MEMBER_PASSWORD_KEY         = "sellercenter-member-default-password";
//
//    @Autowired
//    private AccountClient        accountClient;
//
//    @Autowired
//    private MerchantService      merchantService;
//
//    @Autowired
//    private UniqueCodeGenerator  uniqueCodeGenerator;
//
//    private static final String  sellercenter_store_code_key = "sellercenter-member-code";
//
//    //
//    private final String         memberPrefixCode            = "member-";
//
//    private String               detaulPasswrod              = "654321";
//
//    @Autowired
//    private UnifiedAccountClient unifiedAccountClient;
//
//    @PostConstruct
//    public void init() {
//
//        final String password = parameterClient.get(MEMBER_PASSWORD_KEY);
//        if (password == null) {
//            throw new SellerCenterException("请初始化参数：" + MEMBER_PASSWORD_KEY);
//        }
//        detaulPasswrod = password;
//    }
//
//    @Override
//    public Member getByAccount(String account) {
//
//        return memberDao.getByAccount(account);
//    }
//
//    @Override
//    public boolean add(Member member, Long merchantId) {
//
//        Merchant merchant = merchantService.get(merchantId);
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("merchant_code", merchant.getCode());        
//        member.setAccount(uniqueCodeGenerator.generate(sellercenter_store_code_key, map));
//        Member m = getByAccount(member.getAccount());
//        if (m != null) {
//            throw new SellerCenterException("账号已经使用." + member.getAccount());
//        }
//        final String password = parameterClient.get(MEMBER_PASSWORD_KEY);
//        String psw = getEncodePsw(password);
//        member.setPassword(psw);
//        member.setEnable(EnableType.ENABLE.getKey());
//        long id = autoIdGenerator.get(ID_KEY);
//        member.setId(id);
//        boolean result = memberDao.add(member);
//        if (result) {
//            String perAccountCode = getMemberPermissionAccountCode(member);
//            Account account = accountClient.getAccount(perAccountCode);
//            if (account == null) {
//                account = new Account();
//                account.setCode(perAccountCode);
//                account.setName(member.getName());
//                accountClient.addAccount(account);
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public String getMemberPermissionAccountCode(Member member) {
//
//        return getMemberPermissionAccountCode(member.getAccount());
//    }
//
//    @Override
//    public String getMemberPermissionAccountCode(String account) {
//
//        return memberPrefixCode + account;
//    }
//
//    @Override
//    public boolean isMember(String account, String password) {
//
//        Member member = memberDao.getByAccount(account);
//        if (member != null && member.getEnable() == EnableType.DISABLE.getKey()) {
//            throw new SellerCenterException("职工已禁用." + member.getAccount());
//        }
//        return member != null && getEncodePsw(password).equals(member.getPassword());
//    }
//
//    @Override
//    public String getEncodePsw(String psw) {
//
//        return unifiedAccountClient.encodePwd(psw);
//    }
//
//    @Override
//    public Member get(Long id) {
//
//        return memberDao.get(id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        return memberDao.delete(id);
//    }
//
//    @Override
//    public boolean update(Member member) {
//
//        Member m = get(member.getId());
//        member.setAccount(m.getAccount());
//        return memberDao.update(member);
//    }
//
//    @Override
//    public Page<Member> page(MemberQuery query, int start, int count) {
//
//        return memberDao.page(query, start, count);
//    }
//
//    public List<Member> listAll() {
//
//        return memberDao.listAll();
//    }
//
//    @Override
//    public String getEncodeDefaultPwd() {
//
//        return getEncodePsw(this.detaulPasswrod);
//    }
//}
