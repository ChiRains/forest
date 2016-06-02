package com.qcloud.component.sellercenter.web.handler;

import java.util.List;
import com.qcloud.component.sellercenter.model.Member;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMember4MerchantVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMemberVO;

public interface MemberHandler {

    List<AdminMemberVO> toVOList4Admin(List<Member> list);

    AdminMemberVO toVO4Admin(Member member);

    List<AdminMember4MerchantVO> toVOList4Merchant(List<Member> list, List<Long> keyList);
}
