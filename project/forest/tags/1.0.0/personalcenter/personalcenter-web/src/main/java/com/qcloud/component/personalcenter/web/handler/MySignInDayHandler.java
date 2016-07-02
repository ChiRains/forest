package com.qcloud.component.personalcenter.web.handler;

import java.util.List;
import com.qcloud.component.personalcenter.model.MySignInDay;
import com.qcloud.component.personalcenter.model.MySignInMonth;
import com.qcloud.component.personalcenter.web.vo.MySignInDayVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMySignInDayVO;

public interface MySignInDayHandler {

    List<MySignInDayVO> toVOList(List<MySignInDay> list, int year, int month);

    MySignInDayVO toVO(MySignInDay mySignInDay);

    List<MySignInDayVO> toVOList(MySignInMonth mySignInMonth);

    List<AdminMySignInDayVO> toVOList4Admin(List<MySignInDay> list);

    AdminMySignInDayVO toVO4Admin(MySignInDay mySignInDay);
}
