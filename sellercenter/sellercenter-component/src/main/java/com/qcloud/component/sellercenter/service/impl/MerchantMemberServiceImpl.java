//package com.qcloud.component.sellercenter.service.impl;
//
//import java.util.List;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import com.qcloud.component.autoid.AutoIdGenerator;
//import com.qcloud.component.permission.AccountClient;
//import com.qcloud.component.permission.PermissionClient;
//import com.qcloud.component.permission.model.Account;
//import com.qcloud.component.sellercenter.dao.MerchantMemberDao;
//import com.qcloud.component.sellercenter.model.Member;
//import com.qcloud.component.sellercenter.model.Merchant;
//import com.qcloud.component.sellercenter.model.MerchantMember;
//import com.qcloud.component.sellercenter.model.query.MerchantMemberQuery;
//import com.qcloud.component.sellercenter.service.MemberService;
//import com.qcloud.component.sellercenter.service.MerchantMemberService;
//import com.qcloud.component.sellercenter.service.MerchantService;
//import com.qcloud.pirates.data.Page;
//
//@Service
//public class MerchantMemberServiceImpl implements MerchantMemberService {
//
//    @Autowired
//    private MerchantMemberDao   merchantMemberDao;
//
//    @Autowired
//    private AutoIdGenerator     autoIdGenerator;
//
//    @Autowired
//    private MemberService       memberService;
//
//    private static final String ID_KEY = "sellercenter_merchant_member";
//
//    @Autowired
//    private AccountClient       accountClient;
//
//    @Autowired
//    private PermissionClient    permissionClient;
//
//    @Value("${pirates.seller.merchantMemberRoleId}")
//    private String              merchantMemberRoleId;
//
//    @Autowired
//    private MerchantService     merchantService;
//
//    @Override
//    public boolean add(MerchantMember merchantMember) {
//
//        long id = autoIdGenerator.get(ID_KEY);
//        merchantMember.setId(id);
//        merchantMemberDao.add(merchantMember);
//        Member member = memberService.get(merchantMember.getMemberId());
//        Merchant merchant = merchantService.get(merchantMember.getMerchantId());
//        if (StringUtils.isNotEmpty(merchantMemberRoleId) && merchant.getMerchantType() == 1) {
//            String perAccountCode = memberService.getMemberPermissionAccountCode(member);
//            Account account = accountClient.getAccount(perAccountCode);
//            if (account != null) {
//                permissionClient.grant(account.getId(), Long.parseLong(merchantMemberRoleId.trim()));
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public MerchantMember get(Long id) {
//
//        return merchantMemberDao.get(id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        return merchantMemberDao.delete(id);
//    }
//
//    @Override
//    public boolean update(MerchantMember merchantMember) {
//
//        return merchantMemberDao.update(merchantMember);
//    }
//
//    @Override
//    public Page<MerchantMember> page(MerchantMemberQuery query, int start, int count) {
//
//        return merchantMemberDao.page(query, start, count);
//    }
//
//    public List<MerchantMember> listAll() {
//
//        return merchantMemberDao.listAll();
//    }
//
//    @Override
//    public List<MerchantMember> listByMerchant(Long merchantId) {
//
//        return merchantMemberDao.listByMerchant(merchantId);
//    }
//
//    @Override
//    public MerchantMember get(Long memberId, Long merchantId) {
//
//        return merchantMemberDao.get(memberId, merchantId);
//    }
//
//    @Override
//    public List<MerchantMember> listByMember(Long memberId) {
//
//        return merchantMemberDao.listByMember(memberId);
//    }
//
//    @Transactional
//    @Override
//    public boolean add(Member member, Long merchantId) {
//
//        boolean result = memberService.add(member, merchantId);
//        if (result) {
//            MerchantMember merchantMember = new MerchantMember();
//            merchantMember.setMemberId(member.getId());
//            merchantMember.setMerchantId(merchantId);
//            result = add(merchantMember);
//        }
//        return result;
//    }
//}
