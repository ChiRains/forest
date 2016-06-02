package com.qcloud.component.personalcenter.web.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.personalcenter.model.MyWealthDetail;
import com.qcloud.component.personalcenter.web.handler.MyWealthDetailHandler;
import com.qcloud.component.personalcenter.web.vo.MyWealthDetailVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMyWealthDetailVO;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;

@Component
public class MyWealthDetailHandlerImpl implements MyWealthDetailHandler {

    @Autowired
    private PersonalcenterClient personalcenterClient;

    @Override
    public List<MyWealthDetailVO> toVOList(List<MyWealthDetail> list) {

        List<MyWealthDetailVO> voList = new ArrayList<MyWealthDetailVO>();
        for (MyWealthDetail myWealthDetail : list) {
            voList.add(toVO(myWealthDetail));
        }
        return voList;
    }

    @Override
    public MyWealthDetailVO toVO(MyWealthDetail myWealthDetail) {

        String json = Json.toJson(myWealthDetail);
        MyWealthDetailVO vo = Json.toObject(json, MyWealthDetailVO.class, true);
        vo.setTimeStr(DateUtil.date2String(myWealthDetail.getTime()));
        return vo;
    }

    @Override
    public List<AdminMyWealthDetailVO> toVOList4Admin(List<MyWealthDetail> list) {

        List<AdminMyWealthDetailVO> voList = new ArrayList<AdminMyWealthDetailVO>();
        for (MyWealthDetail adminMyWealthDetail : list) {
            voList.add(toVO4Admin(adminMyWealthDetail));
        }
        return voList;
    }

    @Override
    public AdminMyWealthDetailVO toVO4Admin(MyWealthDetail myWealthDetail) {

        String json = Json.toJson(myWealthDetail);
        AdminMyWealthDetailVO vo = Json.toObject(json, AdminMyWealthDetailVO.class, true);
        QUser user = personalcenterClient.getUser(myWealthDetail.getUserId());
        AssertUtil.assertNotNull(user, "用户不存在");
        vo.setUserName(user.getName());
        return vo;
    }

    @Override
    public List<MyWealthDetailVO> toVOList4Stat(List<MyWealthDetail> list, Date begin, Date end) {

        Map<String, MyWealthDetail> map = new HashMap<String, MyWealthDetail>();
        for (MyWealthDetail myWealthDetail : list) {
            String dateStr = DateUtil.date2String(myWealthDetail.getTime(), DateUtil.DATE_FORMAT_STRING);
            MyWealthDetail mw = map.get(dateStr);
            if (mw == null) {
                map.put(dateStr, myWealthDetail);
            } else if (myWealthDetail.getTime().after(mw.getTime())) {
                map.put(dateStr, myWealthDetail);
            }
        }
        Date cycleDate = begin;
        List<MyWealthDetailVO> voList = new ArrayList<MyWealthDetailVO>();
        MyWealthDetail last = null;
        for (; cycleDate.before(end);) {
            String dateStr = DateUtil.date2String(cycleDate, DateUtil.DATE_FORMAT_STRING);
            MyWealthDetail mw = map.get(dateStr);
            if (last == null) {
                last = mw;
            }
            if (mw == null) {
                mw = last;
            }
            MyWealthDetailVO vo = new MyWealthDetailVO();
            vo.setTimeStr(DateUtil.date2String(cycleDate, "MM-dd"));
            if (mw == null) {
                vo.setDesc("");
                vo.setPoint(0.0);
                vo.setRemainder(0.0);
            } else {
                vo.setDesc(mw.getDesc());
                vo.setPoint(mw.getPoint());
                vo.setRemainder(mw.getRemainder());
            }
            if (mw != null) {
                last = mw;
            }
            voList.add(vo);
            cycleDate = DateUtil.addDate(cycleDate, 1);
        }
        return voList;
    }
}
