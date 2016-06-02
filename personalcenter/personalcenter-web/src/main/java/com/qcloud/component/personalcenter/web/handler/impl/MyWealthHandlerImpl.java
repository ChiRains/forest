package com.qcloud.component.personalcenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.personalcenter.model.MyWealth;
import com.qcloud.component.personalcenter.web.handler.MyWealthHandler;
import com.qcloud.component.personalcenter.web.vo.MyWealthVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMyWealthVO;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.AssertUtil;

@Component
public class MyWealthHandlerImpl implements MyWealthHandler {
    @Autowired
    private PersonalcenterClient personalcenterClient;
    
	@Override
	public List<MyWealthVO> toVOList(List<MyWealth> list){
		List<MyWealthVO> voList = new ArrayList<MyWealthVO>();
		for (MyWealth myWealth : list) {
			voList.add(toVO(myWealth));
		}
		return voList;
	}

	@Override
	public MyWealthVO toVO(MyWealth myWealth){
		String json = Json.toJson(myWealth);
		return Json.toObject(json, MyWealthVO.class, true);

	}

	@Override
	public List<AdminMyWealthVO> toVOList4Admin(List<MyWealth> list){
		List<AdminMyWealthVO> voList = new ArrayList<AdminMyWealthVO>();
		for (MyWealth adminMyWealth : list) {
			voList.add(toVO4Admin(adminMyWealth));
		}
		return voList;
	}

	@Override
	public AdminMyWealthVO toVO4Admin(MyWealth myWealth){
		String json = Json.toJson(myWealth);
		AdminMyWealthVO vo=Json.toObject(json, AdminMyWealthVO.class, true);
		QUser user=personalcenterClient.getUser(myWealth.getUserId());
		AssertUtil.assertNotNull(user, "用户不存在");
		vo.setUserName(user.getName());
		return vo;
	}
}
