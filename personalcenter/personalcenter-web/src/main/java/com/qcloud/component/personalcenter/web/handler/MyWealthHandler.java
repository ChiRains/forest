package com.qcloud.component.personalcenter.web.handler;

import java.util.List;

import com.qcloud.component.personalcenter.model.MyWealth;
import com.qcloud.component.personalcenter.web.vo.MyWealthVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMyWealthVO;

public interface MyWealthHandler {

	List<MyWealthVO> toVOList(List<MyWealth> list);

	MyWealthVO toVO(MyWealth myWealth);

	List<AdminMyWealthVO> toVOList4Admin(List<MyWealth> list);

	AdminMyWealthVO toVO4Admin(MyWealth myWealth);
}
