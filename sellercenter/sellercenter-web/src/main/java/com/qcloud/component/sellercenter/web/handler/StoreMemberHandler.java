package com.qcloud.component.sellercenter.web.handler;

import java.util.List;
import com.qcloud.component.sellercenter.model.StoreMember;
import com.qcloud.component.sellercenter.web.vo.admin.AdminStoreMemberVO;
public interface StoreMemberHandler {

    List<AdminStoreMemberVO> toVOList4Admin(List<StoreMember> list);

    AdminStoreMemberVO toVO4Admin(StoreMember storeMember);
}
