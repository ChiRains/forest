//package com.qcloud.component.sellercenter.web.login;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.qcloud.component.admin.LoginVerification;
//import com.qcloud.component.sellercenter.model.Member;
//import com.qcloud.component.sellercenter.service.MemberService;
//@Component
//public class LoginVerificationImpl implements LoginVerification {
//    @Autowired
//    private MemberService memberService;
//
//    @Override
//    public boolean allow(String account, String password) {
//        return memberService.isMember(account, password);
//    }
//
//    @Override
//    public String[] getAccountCodes(String account) {
//        return new String[] { memberService.getMemberPermissionAccountCode(account)};
//    }
//
//    @Override
//    public String getIdentificationKey(String account) {
//        Member member = memberService.getByAccount(account);
//        if (member == null) {
//            return null;
//        }
//        return String.valueOf(member.getId());
//    }
//}
