package com.qcloud.component.orderform.util;

import java.util.Date;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.TableSplitUtil;

public class OrderTableSplitUtil {

    public static String getTableSplitName(String tableName, Date date) {

        // 分表,按一年一张表分
        return TableSplitUtil.getTableSplitName(tableName, date, 1);
    }

    public static String getTableSplitName(String tableName, String orderNumber) {

        String spiltDateStr = orderNumber.trim().substring(4, 12);
        String dateStr = spiltDateStr.substring(0, 4) + "-" + spiltDateStr.substring(4, 6) + "-" + spiltDateStr.substring(6, spiltDateStr.length());
        Date date = DateUtil.str2Date(dateStr, DateUtil.DATE_FORMAT_STRING);
        return TableSplitUtil.getTableSplitName(tableName, date, 1);
    }

    public static Date getTableDate(String orderNumber) {

        String spiltDateStr = orderNumber.trim().substring(4, 12);
        String dateStr = spiltDateStr.substring(0, 4) + "-" + spiltDateStr.substring(4, 6) + "-" + spiltDateStr.substring(6, spiltDateStr.length());
        Date date = DateUtil.str2Date(dateStr, DateUtil.DATE_FORMAT_STRING);
        return date;
    }
}
