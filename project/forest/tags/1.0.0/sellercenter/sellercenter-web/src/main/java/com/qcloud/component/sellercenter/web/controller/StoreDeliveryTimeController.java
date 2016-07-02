package com.qcloud.component.sellercenter.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.sellercenter.model.StoreDeliveryTime;
import com.qcloud.component.sellercenter.service.StoreDeliveryTimeService;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;

@Controller
@RequestMapping(value = StoreDeliveryTimeController.DIR)
public class StoreDeliveryTimeController {

    public static final String       DIR = "/storeDeliveryTime";

    @Autowired
    private StoreDeliveryTimeService storeDeliveryTimeService;

    @RequestMapping
    public FrontAjaxView listPickupTimeList(Long storeId) {

        return listPickupTimeListByDate(DateUtil.addDate(new Date(), 1), storeId);
    }

    @RequestMapping
    public FrontAjaxView listPickupTimeListByDate(Date date, Long storeId) {

        StoreDeliveryTime storeDeliveryTime = storeDeliveryTimeService.getByStore(storeId);
        AssertUtil.assertNotNull(storeDeliveryTime, "没有设置门店配送时间." + storeId);
        String startTime = storeDeliveryTime.getPickupStartTime();
        String endTime = storeDeliveryTime.getPickupEndTime();
        Date start = strToDate(startTime);
        int deliveryDuration = 60;
        if (isToday(date)) {
            start = getTodayStartTime(start, deliveryDuration);
        }
        Date end = strToDate(endTime);
        List<String> list = new ArrayList<String>();
        if (start != null && end != null && start.before(end)) {
            Date begin = start;
            while (true) {
                list.add(dataToStr(begin));
                begin = DateUtil.addTime(begin, deliveryDuration);
                if (begin.after(end)) {
                    break;
                }
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取自提时间成功");
        view.addObject("data", list);
        return view;
    }

    @RequestMapping
    public FrontAjaxView listDeliveryTimeList(Long storeId) {

        StoreDeliveryTime storeDeliveryTime = storeDeliveryTimeService.getByStore(storeId);
        AssertUtil.assertNotNull(storeDeliveryTime, "没有设置门店配送时间." + storeId);
        String startTime = storeDeliveryTime.getDeliveryStartTime();
        Date start = strToDate(startTime);
        String endTime = storeDeliveryTime.getDeliveryEndTime();
        Date end = strToDate(endTime);
        List<String> list = new ArrayList<String>();
        if (start != null && end != null && start.before(end)) {
            int deliveryFrequency = storeDeliveryTime.getDeliveryFrequency();
            int deliveryDuration = storeDeliveryTime.getDeliveryDuration();
            Date begin = start;
            while (true) {
                Date perEnd = DateUtil.addTime(begin, deliveryDuration);
                if (perEnd.after(end)) {
                    break;
                }
                list.add(dataToStr(begin) + " 至 " + dataToStr(perEnd));
                begin = DateUtil.addTime(begin, deliveryFrequency);
                if (begin.after(end)) {
                    break;
                }
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取配送时间成功");
        view.addObject("data", list);
        return view;
    }

    @RequestMapping
    public FrontAjaxView getPickupDesc(Long storeId) {

        StoreDeliveryTime storeDeliveryTime = storeDeliveryTimeService.getByStore(storeId);
        AssertUtil.assertNotNull(storeDeliveryTime, "没有设置门店配送时间." + storeId);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取配送描述成功");
        view.addObject("pickupDesc", storeDeliveryTime.getPickupDesc());
        return view;
    }

    private Date getTodayStartTime(Date startDate, int deliveryDuration) {

        Date temp = startDate;
        Date today = new Date();
        Date exp = strToDate(DateUtil.date2String(today, "HH:mm"));
        while (temp.before(exp)) {
            temp = DateUtil.addTime(temp, deliveryDuration);
        }
        return temp;
    }

    private boolean isToday(Date date) {

        if (date == null) {
            return false;
        }
        Date today = new Date();
        String dateStr = DateUtil.date2String(date, DateUtil.DATE_FORMAT_STRING);
        String todayStr = DateUtil.date2String(today, DateUtil.DATE_FORMAT_STRING);
        return dateStr.equals(todayStr);
    }

    private Date strToDate(String str) {

        if (str == null) {
            return null;
        }
        return DateUtil.str2Date(str, "HH:mm");
    }

    private String dataToStr(Date date) {

        if (date == null) {
            return "";
        }
        return DateUtil.date2String(date, "HH时mm分");
    }
}
