package com.qcloud.component.personalcenter.web.handler;

import java.util.List;

import com.qcloud.component.personalcenter.model.MySignInMonth;
import com.qcloud.component.personalcenter.web.vo.MySignInMonthVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMySignInMonthVO;

public interface MySignInMonthHandler {

	List<MySignInMonthVO> toVOList(List<MySignInMonth> list);

	MySignInMonthVO toVO(MySignInMonth mySignInMonth);

	List<AdminMySignInMonthVO> toVOList4Admin(List<MySignInMonth> list);

	AdminMySignInMonthVO toVO4Admin(MySignInMonth mySignInMonth);
}
