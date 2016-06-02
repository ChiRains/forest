package com.qcloud.component.sellercenter.web.handler;

import java.util.List;
import com.qcloud.component.sellercenter.model.MerchantMember;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantMemberVO;

public interface MerchantMemberHandler {

    List<AdminMerchantMemberVO> toVOList4Admin(List<MerchantMember> list);

    AdminMerchantMemberVO toVO4Admin(MerchantMember merchantMember);
}
