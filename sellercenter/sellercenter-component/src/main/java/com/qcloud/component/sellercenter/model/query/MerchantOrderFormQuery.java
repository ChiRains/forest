package com.qcloud.component.sellercenter.model.query;

import java.util.Calendar;
import java.util.Date;
import com.qcloud.component.sellercenter.exception.SellerCenterException;

public class MerchantOrderFormQuery {

    // 1本月 2本季度 3今年内 4去年 5前年 允许商家查最近三年的订单
    private int    dateType;

    private int    state;

    // 总单号
    private String orderNumber;

    public MerchantOrderFormQuery() {

    }

    public int getDateType() {

        if (dateType == 0) {
            dateType = 1;
        }
        return dateType;
    }

    public void setDateType(int dateType) {

        this.dateType = dateType;
    }

    public int getState() {

        if (state == 0) {
            state = 4;
        }
        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public Date getStartOrderDate() {

        switch (getDateType()) {
        case 1:
            Calendar c1 = Calendar.getInstance();
            c1.setTime(new Date());
            c1.set(Calendar.DAY_OF_MONTH, 1);
            c1.set(Calendar.HOUR_OF_DAY, 0);
            c1.set(Calendar.MINUTE, 0);
            c1.set(Calendar.SECOND, 0);
            return c1.getTime();
        case 2:
            Calendar c2 = Calendar.getInstance();
            c2.setTime(new Date());
            int month = c2.get(Calendar.MONTH);
            if (month <= 2) {
                c2.set(Calendar.MONTH, 0);
            } else if (month <= 5) {
                c2.set(Calendar.MONTH, 3);
            } else if (month <= 8) {
                c2.set(Calendar.MONTH, 6);
            } else {
                c2.set(Calendar.MONTH, 9);
            }
            c2.set(Calendar.DAY_OF_MONTH, 1);
            c2.set(Calendar.HOUR_OF_DAY, 0);
            c2.set(Calendar.MINUTE, 0);
            c2.set(Calendar.SECOND, 0);
            return c2.getTime();
        case 3:
            Calendar c3 = Calendar.getInstance();
            c3.setTime(new Date());
            c3.set(Calendar.YEAR, c3.get(Calendar.YEAR));
            c3.set(Calendar.MONTH, 0);
            c3.set(Calendar.DAY_OF_MONTH, 1);
            c3.set(Calendar.HOUR_OF_DAY, 0);
            c3.set(Calendar.MINUTE, 0);
            c3.set(Calendar.SECOND, 0);
            return c3.getTime();
        case 4:
            Calendar c4 = Calendar.getInstance();
            c4.setTime(new Date());
            c4.set(Calendar.YEAR, c4.get(Calendar.YEAR) - 1);
            c4.set(Calendar.MONTH, 0);
            c4.set(Calendar.DAY_OF_MONTH, 1);
            c4.set(Calendar.HOUR_OF_DAY, 0);
            c4.set(Calendar.MINUTE, 0);
            c4.set(Calendar.SECOND, 0);
            return c4.getTime();
        case 5:
            Calendar c5 = Calendar.getInstance();
            c5.setTime(new Date());
            c5.set(Calendar.YEAR, c5.get(Calendar.YEAR) - 2);
            c5.set(Calendar.MONTH, 0);
            c5.set(Calendar.DAY_OF_MONTH, 1);
            c5.set(Calendar.HOUR_OF_DAY, 0);
            c5.set(Calendar.MINUTE, 0);
            c5.set(Calendar.SECOND, 0);
            return c5.getTime();
        }
        throw new SellerCenterException("不支持的订单时段查询类型." + dateType);
    }

    public Date getEndOrderDate() {

        switch (getDateType()) {
        case 1:
            return new Date();
        case 2:
            return new Date();
        case 3:
            return new Date();
        case 4:
            Calendar c4 = Calendar.getInstance();
            c4.setTime(new Date());
            c4.set(Calendar.YEAR, c4.get(Calendar.YEAR) - 1);
            c4.set(Calendar.MONTH, 11);
            c4.set(Calendar.DAY_OF_MONTH, 31);
            c4.set(Calendar.HOUR_OF_DAY, 23);
            c4.set(Calendar.MINUTE, 59);
            c4.set(Calendar.SECOND, 59);
            return c4.getTime();
        case 5:
            Calendar c5 = Calendar.getInstance();
            c5.setTime(new Date());
            c5.set(Calendar.YEAR, c5.get(Calendar.YEAR) - 2);
            c5.set(Calendar.MONTH, 11);
            c5.set(Calendar.DAY_OF_MONTH, 31);
            c5.set(Calendar.HOUR_OF_DAY, 23);
            c5.set(Calendar.MINUTE, 59);
            c5.set(Calendar.SECOND, 59);
            return c5.getTime();
        }
        throw new SellerCenterException("不支持的订单时段查询类型." + dateType);
    }

    public String getOrderNumber() {

        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {

        this.orderNumber = orderNumber;
    }
}
