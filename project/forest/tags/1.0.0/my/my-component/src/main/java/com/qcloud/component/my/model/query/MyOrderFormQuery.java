package com.qcloud.component.my.model.query;

import java.util.Calendar;
import java.util.Date;
import com.qcloud.component.my.exception.MyException;
import com.qcloud.pirates.util.DateUtil;

public class MyOrderFormQuery {

    // 用户视角的状态 1待付款 2待发货 3待收货 6待评价 7已评价 8待退货
    private int state;

    // 1本周 2本月 3本季度 4半年内 5今年内 6去年 7前年以前 允许用户查其所有订单
    private int dateType;

    public MyOrderFormQuery() {

    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getDateType() {

        if (dateType == 0) {
            dateType = -1;
        }
        return dateType;
    }

    public void setDateType(int dateType) {

        this.dateType = dateType;
    }

    public Date getStartOrderDate() {

        switch (getDateType()) {
        case -1:
            Calendar c0 = Calendar.getInstance();
            c0.setTime(new Date());
            c0.set(Calendar.YEAR, 2000);
            c0.set(Calendar.MONTH, 0);
            c0.set(Calendar.DAY_OF_MONTH, 1);
            c0.set(Calendar.HOUR_OF_DAY, 0);
            c0.set(Calendar.MINUTE, 0);
            c0.set(Calendar.SECOND, 0);
            return c0.getTime();
        case 1:
            Calendar c1 = Calendar.getInstance();
            Date date = new Date();
            c1.setTime(date);
            int day = c1.get(Calendar.DAY_OF_WEEK);
            switch (day) {
            case Calendar.SUNDAY:
                date = DateUtil.addDate(new Date(), -6);
                break;
            default:
                date = DateUtil.addDate(new Date(), 2 - day);
                break;
            }
            c1.setTime(date);
            c1.set(Calendar.HOUR_OF_DAY, 0);
            c1.set(Calendar.MINUTE, 0);
            c1.set(Calendar.SECOND, 0);
            return c1.getTime();
        case 2:
            Calendar c2 = Calendar.getInstance();
            c2.setTime(new Date());
            c2.set(Calendar.DAY_OF_MONTH, 1);
            c2.set(Calendar.HOUR_OF_DAY, 0);
            c2.set(Calendar.MINUTE, 0);
            c2.set(Calendar.SECOND, 0);
            return c2.getTime();
        case 3:
            Calendar c3 = Calendar.getInstance();
            c3.setTime(new Date());
            int month3 = c3.get(Calendar.MONTH);
            if (month3 <= 2) {
                c3.set(Calendar.MONTH, 0);
            } else if (month3 <= 5) {
                c3.set(Calendar.MONTH, 3);
            } else if (month3 <= 8) {
                c3.set(Calendar.MONTH, 6);
            } else {
                c3.set(Calendar.MONTH, 9);
            }
            c3.set(Calendar.DAY_OF_MONTH, 1);
            c3.set(Calendar.HOUR_OF_DAY, 0);
            c3.set(Calendar.MINUTE, 0);
            c3.set(Calendar.SECOND, 0);
            return c3.getTime();
        case 4:
            Calendar c4 = Calendar.getInstance();
            c4.setTime(new Date());
            int month4 = c4.get(Calendar.MONTH);
            if (month4 <= 5) {
                c4.set(Calendar.MONTH, 0);
            } else {
                c4.set(Calendar.MONTH, 6);
            }
            c4.set(Calendar.DAY_OF_MONTH, 1);
            c4.set(Calendar.HOUR_OF_DAY, 0);
            c4.set(Calendar.MINUTE, 0);
            c4.set(Calendar.SECOND, 0);
            return c4.getTime();
        case 5:
            Calendar c5 = Calendar.getInstance();
            c5.setTime(new Date());
            c5.set(Calendar.YEAR, c5.get(Calendar.YEAR));
            c5.set(Calendar.MONTH, 0);
            c5.set(Calendar.DAY_OF_MONTH, 1);
            c5.set(Calendar.HOUR_OF_DAY, 0);
            c5.set(Calendar.MINUTE, 0);
            c5.set(Calendar.SECOND, 0);
            return c5.getTime();
        case 6:
            Calendar c6 = Calendar.getInstance();
            c6.setTime(new Date());
            c6.set(Calendar.YEAR, c6.get(Calendar.YEAR) - 1);
            c6.set(Calendar.MONTH, 0);
            c6.set(Calendar.DAY_OF_MONTH, 1);
            c6.set(Calendar.HOUR_OF_DAY, 0);
            c6.set(Calendar.MINUTE, 0);
            c6.set(Calendar.SECOND, 0);
            return c6.getTime();
        case 7:
            Calendar c7 = Calendar.getInstance();
            c7.setTime(new Date());
            c7.set(Calendar.YEAR, 2000);
            c7.set(Calendar.MONTH, 0);
            c7.set(Calendar.DAY_OF_MONTH, 1);
            c7.set(Calendar.HOUR_OF_DAY, 0);
            c7.set(Calendar.MINUTE, 0);
            c7.set(Calendar.SECOND, 0);
            return c7.getTime();
        }
        throw new MyException("不支持的订单时段查询类型." + dateType);
    }

    public Date getEndOrderDate() {

        switch (getDateType()) {
        case -1:
            return new Date();
        case 1:
            return new Date();
        case 2:
            return new Date();
        case 3:
            return new Date();
        case 4:
            return new Date();
        case 5:
            return new Date();
        case 6:
            Calendar c6 = Calendar.getInstance();
            c6.setTime(new Date());
            c6.set(Calendar.YEAR, c6.get(Calendar.YEAR) - 1);
            c6.set(Calendar.MONTH, 11);
            c6.set(Calendar.DAY_OF_MONTH, 31);
            c6.set(Calendar.HOUR_OF_DAY, 23);
            c6.set(Calendar.MINUTE, 59);
            c6.set(Calendar.SECOND, 59);
            return c6.getTime();
        case 7:
            Calendar c7 = Calendar.getInstance();
            c7.setTime(new Date());
            c7.set(Calendar.YEAR, c7.get(Calendar.YEAR) - 2);
            c7.set(Calendar.MONTH, 11);
            c7.set(Calendar.DAY_OF_MONTH, 31);
            c7.set(Calendar.HOUR_OF_DAY, 23);
            c7.set(Calendar.MINUTE, 59);
            c7.set(Calendar.SECOND, 59);
            return c7.getTime();
        }
        throw new MyException("不支持的订单时段查询类型." + dateType);
    }
}
