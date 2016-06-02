package com.qcloud.component.personalcenter.web.handler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.qcloud.component.personalcenter.model.MySignInDay;
import com.qcloud.component.personalcenter.model.MySignInMonth;
import com.qcloud.component.personalcenter.web.handler.MySignInDayHandler;
import com.qcloud.component.personalcenter.web.vo.MySignInDayVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMySignInDayVO;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.StringUtil;

@Component
public class MySignInDayHandlerImpl implements MySignInDayHandler {

    @Override
    public List<MySignInDayVO> toVOList(List<MySignInDay> list, int year, int month) {

        Map<Integer, MySignInDayVO> map = new HashMap<Integer, MySignInDayVO>();
        for (MySignInDay mySignInDay : list) {
            MySignInDayVO vo = new MySignInDayVO();
            vo.setDay(mySignInDay.getDay());
            vo.setSign(true);
            map.put(vo.getDay(), vo);
        }
        return mapToList(map, year, month);
    }

    @Override
    public MySignInDayVO toVO(MySignInDay mySignInDay) {

        String json = Json.toJson(mySignInDay);
        return Json.toObject(json, MySignInDayVO.class, true);
    }

    @Override
    public List<AdminMySignInDayVO> toVOList4Admin(List<MySignInDay> list) {

        List<AdminMySignInDayVO> voList = new ArrayList<AdminMySignInDayVO>();
        for (MySignInDay adminMySignInDay : list) {
            voList.add(toVO4Admin(adminMySignInDay));
        }
        return voList;
    }

    @Override
    public AdminMySignInDayVO toVO4Admin(MySignInDay mySignInDay) {

        String json = Json.toJson(mySignInDay);
        return Json.toObject(json, AdminMySignInDayVO.class, true);
    }

    @Override
    public List<MySignInDayVO> toVOList(MySignInMonth mySignInMonth) {

        if (mySignInMonth == null) {
            return new ArrayList<MySignInDayVO>();
        }
        String record = StringUtil.nullToEmpty(mySignInMonth.getRecord());
        String[] days = record.split(";");
        Map<Integer, MySignInDayVO> map = new HashMap<Integer, MySignInDayVO>();
        for (String str : days) {
            MySignInDayVO vo = new MySignInDayVO();
            vo.setDay(Integer.parseInt(str));
            vo.setSign(true);
            map.put(vo.getDay(), vo);
        }
        return mapToList(map, mySignInMonth.getYear(), mySignInMonth.getMonth());
    }

    private List<MySignInDayVO> mapToList(Map<Integer, MySignInDayVO> map, int year, int month) {

        List<MySignInDayVO> list = new ArrayList<MySignInDayVO>();
        int monthMaxDay = getMonthLastDay(year, month);
        for (int index = 1; index <= monthMaxDay; index++) {
            MySignInDayVO vo = map.get(index);
            if (vo == null) {
                vo = new MySignInDayVO();
                vo.setDay(index);
                vo.setSign(false);
            }
            list.add(vo);
        }
        return list;
    }

    private int getMonthLastDay(int year, int month) {

        switch (month) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            return 31;
        case 4:
        case 6:
        case 9:
        case 11:
            return 30;
        case 2:
            int less4 = year % 4;
            int less100 = year % 100;
            int less400 = year % 400;
            if (less4 == 0 && less100 != 0) {
                return 29;
            } else if (less4 == 0 && less100 == 0 && less400 == 0) {
                return 29;
            } else {
                return 28;
            }
        }
        return 30;
    }
}
