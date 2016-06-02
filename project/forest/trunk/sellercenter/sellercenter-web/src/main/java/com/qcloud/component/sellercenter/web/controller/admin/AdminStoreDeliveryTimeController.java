package com.qcloud.component.sellercenter.web.controller.admin;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.publicdata.IntKeyValue;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.StrKeyValue;
import com.qcloud.component.sellercenter.model.StoreDeliveryTime;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryTimeQuery;
import com.qcloud.component.sellercenter.service.StoreDeliveryTimeService;
import com.qcloud.component.sellercenter.web.handler.StoreDeliveryTimeHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminStoreDeliveryTimeVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminStoreDeliveryTimeController.DIR)
public class AdminStoreDeliveryTimeController {

    public static final String       DIR = "admin/storeDeliveryTime";

    @Autowired
    private StoreDeliveryTimeService storeDeliveryTimeService;

    @Autowired
    private StoreDeliveryTimeHandler storeDeliveryTimeHandler;

    @Autowired
    private PublicdataClient         publicdataClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, StoreDeliveryTimeQuery query) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<StoreDeliveryTime> page = storeDeliveryTimeService.page(query, start, PAGE_SIZE);
        List<AdminStoreDeliveryTimeVO> list = storeDeliveryTimeHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-StoreDeliveryTime-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/sellercenter-StoreDeliveryTime-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(StoreDeliveryTime storeDeliveryTime) {

        storeDeliveryTimeService.add(storeDeliveryTime);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        StoreDeliveryTime storeDeliveryTime = storeDeliveryTimeService.get(id);
        AdminStoreDeliveryTimeVO adminStoreDeliveryTimeVO = storeDeliveryTimeHandler.toVO4Admin(storeDeliveryTime);
        ModelAndView model = new ModelAndView("/admin/sellercenter-StoreDeliveryTime-edit");
        model.addObject("storeDeliveryTime", adminStoreDeliveryTimeVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(StoreDeliveryTime storeDeliveryTime) {

        StoreDeliveryTime s = storeDeliveryTimeService.getByStore(storeDeliveryTime.getStoreId());
        if (s == null) {
            storeDeliveryTimeService.add(storeDeliveryTime);
        } else {
            storeDeliveryTimeService.update(storeDeliveryTime);
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/toEditByStore?storeId=" + storeDeliveryTime.getStoreId());
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEditByStore(Long storeId) {

        List<StrKeyValue> pickupStartTimeList = initPickupStartTimeList();
        List<StrKeyValue> pickupEndTimeList = initPickupEndTimeList();
        List<StrKeyValue> deliveryStartTimeList = initDeliveryStartTimeList();
        List<StrKeyValue> deliveryEndTimeList = initDeliveryEndTimeList();
        List<IntKeyValue> deliveryFrequencyList = initDeliveryFrequencyList();
        List<IntKeyValue> deliveryDurationList = initDeliveryDurationList();
        AssertUtil.assertNotNull(storeId, "storeId不能为空");
        StoreDeliveryTime storeDeliveryTime = storeDeliveryTimeService.getByStore(storeId);
        if (storeDeliveryTime == null) {
            storeDeliveryTime = new StoreDeliveryTime();
            storeDeliveryTime.setId(-1);
            storeDeliveryTime.setStoreId(storeId);
            storeDeliveryTime.setPickupStartTime("08:00");
            storeDeliveryTime.setDeliveryStartTime("08:00");
            storeDeliveryTime.setPickupEndTime("22:00");
            storeDeliveryTime.setDeliveryEndTime("21:00");
            storeDeliveryTime.setDeliveryFrequency(60);
            storeDeliveryTime.setDeliveryDuration(120);
        }
        List<KeyValueVO> pickupStartTimeVOList = publicdataClient.exchageObj(pickupStartTimeList, storeDeliveryTime.getPickupStartTime(), "selected");
        List<KeyValueVO> pickupEndTimeVOList = publicdataClient.exchageObj(pickupEndTimeList, storeDeliveryTime.getPickupEndTime(), "selected");
        List<KeyValueVO> deliveryStartTimeVOList = publicdataClient.exchageObj(deliveryStartTimeList, storeDeliveryTime.getDeliveryStartTime(), "selected");
        List<KeyValueVO> deliveryEndTimeVOList = publicdataClient.exchageObj(deliveryEndTimeList, storeDeliveryTime.getDeliveryEndTime(), "selected");
        List<KeyValueVO> deliveryFrequencyVOList = publicdataClient.exchageObj(deliveryFrequencyList, storeDeliveryTime.getDeliveryFrequency(), "selected");
        List<KeyValueVO> deliveryDurationVOList = publicdataClient.exchageObj(deliveryDurationList, storeDeliveryTime.getDeliveryDuration(), "selected");
        AdminStoreDeliveryTimeVO adminStoreDeliveryTimeVO = storeDeliveryTimeHandler.toVO4Admin(storeDeliveryTime);
        ModelAndView model = new ModelAndView("/admin/sellercenter-StoreDeliveryTime-edit");
        model.addObject("storeDeliveryTime", adminStoreDeliveryTimeVO);
        model.addObject("pickupStartTimeList", pickupStartTimeVOList);
        model.addObject("pickupEndTimeList", pickupEndTimeVOList);
        model.addObject("deliveryStartTimeList", deliveryStartTimeVOList);
        model.addObject("deliveryEndTimeList", deliveryEndTimeVOList);
        model.addObject("deliveryFrequencyList", deliveryFrequencyVOList);
        model.addObject("deliveryDurationList", deliveryDurationVOList);
        return model;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        storeDeliveryTimeService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    // 门店自提开始时间
    private List<StrKeyValue> initPickupStartTimeList() {

        List<StrKeyValue> timeList = new ArrayList<StrKeyValue>();
        TimeStrKeyValue six = new TimeStrKeyValue(6, 0);
        TimeStrKeyValue seven = new TimeStrKeyValue(7, 0);
        TimeStrKeyValue eight = new TimeStrKeyValue(8, 0);
        TimeStrKeyValue nine = new TimeStrKeyValue(9, 0);
        TimeStrKeyValue ten = new TimeStrKeyValue(10, 0);
        TimeStrKeyValue eleven = new TimeStrKeyValue(11, 0);
        TimeStrKeyValue twelve = new TimeStrKeyValue(12, 0);
        timeList.add(six);
        timeList.add(seven);
        timeList.add(eight);
        timeList.add(nine);
        timeList.add(ten);
        timeList.add(eleven);
        timeList.add(twelve);
        return timeList;
    }

    // 门店自提结束时间
    private List<StrKeyValue> initPickupEndTimeList() {

        List<StrKeyValue> timeList = new ArrayList<StrKeyValue>();
        TimeStrKeyValue thirteen = new TimeStrKeyValue(13, 0);
        TimeStrKeyValue fourteen = new TimeStrKeyValue(14, 0);
        TimeStrKeyValue fifteen = new TimeStrKeyValue(15, 0);
        TimeStrKeyValue sixteen = new TimeStrKeyValue(16, 0);
        TimeStrKeyValue seventeen = new TimeStrKeyValue(17, 0);
        TimeStrKeyValue eighteen = new TimeStrKeyValue(18, 0);
        TimeStrKeyValue nineteen = new TimeStrKeyValue(19, 0);
        TimeStrKeyValue twenty = new TimeStrKeyValue(20, 0);
        TimeStrKeyValue twentyOne = new TimeStrKeyValue(21, 0);
        TimeStrKeyValue twentyTwo = new TimeStrKeyValue(22, 0);
        TimeStrKeyValue twentyThree = new TimeStrKeyValue(23, 0);
        TimeStrKeyValue twentyFour = new TimeStrKeyValue(24, 0);
        timeList.add(thirteen);
        timeList.add(fourteen);
        timeList.add(fifteen);
        timeList.add(sixteen);
        timeList.add(seventeen);
        timeList.add(eighteen);
        timeList.add(nineteen);
        timeList.add(twenty);
        timeList.add(twentyOne);
        timeList.add(twentyTwo);
        timeList.add(twentyThree);
        timeList.add(twentyFour);
        return timeList;
    }

    // 送货开始时间
    private List<StrKeyValue> initDeliveryStartTimeList() {

        List<StrKeyValue> timeList = new ArrayList<StrKeyValue>();
        TimeStrKeyValue six = new TimeStrKeyValue(6, 0);
        TimeStrKeyValue seven = new TimeStrKeyValue(7, 0);
        TimeStrKeyValue eight = new TimeStrKeyValue(8, 0);
        TimeStrKeyValue nine = new TimeStrKeyValue(9, 0);
        TimeStrKeyValue ten = new TimeStrKeyValue(10, 0);
        TimeStrKeyValue eleven = new TimeStrKeyValue(11, 0);
        TimeStrKeyValue twelve = new TimeStrKeyValue(12, 0);
        timeList.add(six);
        timeList.add(seven);
        timeList.add(eight);
        timeList.add(nine);
        timeList.add(ten);
        timeList.add(eleven);
        timeList.add(twelve);
        return timeList;
    }

    // 送货结束时间
    private List<StrKeyValue> initDeliveryEndTimeList() {

        List<StrKeyValue> timeList = new ArrayList<StrKeyValue>();
        TimeStrKeyValue thirteen = new TimeStrKeyValue(13, 0);
        TimeStrKeyValue fourteen = new TimeStrKeyValue(14, 0);
        TimeStrKeyValue fifteen = new TimeStrKeyValue(15, 0);
        TimeStrKeyValue sixteen = new TimeStrKeyValue(16, 0);
        TimeStrKeyValue seventeen = new TimeStrKeyValue(17, 0);
        TimeStrKeyValue eighteen = new TimeStrKeyValue(18, 0);
        TimeStrKeyValue nineteen = new TimeStrKeyValue(19, 0);
        TimeStrKeyValue twenty = new TimeStrKeyValue(20, 0);
        TimeStrKeyValue twentyOne = new TimeStrKeyValue(21, 0);
        TimeStrKeyValue twentyTwo = new TimeStrKeyValue(22, 0);
        timeList.add(thirteen);
        timeList.add(fourteen);
        timeList.add(fifteen);
        timeList.add(sixteen);
        timeList.add(seventeen);
        timeList.add(eighteen);
        timeList.add(nineteen);
        timeList.add(twenty);
        timeList.add(twentyOne);
        timeList.add(twentyTwo);
        return timeList;
    }

    // 送货间隔
    private List<IntKeyValue> initDeliveryFrequencyList() {

        List<IntKeyValue> minuteList = new ArrayList<IntKeyValue>();
        MinuteStrKeyValue thirty = new MinuteStrKeyValue(30);
        MinuteStrKeyValue sixty = new MinuteStrKeyValue(60);
        minuteList.add(thirty);
        minuteList.add(sixty);
        return minuteList;
    }

    // 多久送达
    private List<IntKeyValue> initDeliveryDurationList() {

        List<IntKeyValue> minuteList = new ArrayList<IntKeyValue>();
        MinuteStrKeyValue thirty = new MinuteStrKeyValue(30);
        MinuteStrKeyValue sixty = new MinuteStrKeyValue(60);
        MinuteStrKeyValue twoHours = new MinuteStrKeyValue(120);
        MinuteStrKeyValue threeHours = new MinuteStrKeyValue(180);
        minuteList.add(thirty);
        minuteList.add(sixty);
        minuteList.add(twoHours);
        minuteList.add(threeHours);
        return minuteList;
    }
    private class TimeStrKeyValue implements StrKeyValue {

        private int hour;

        private int minute;

        public TimeStrKeyValue(int hour, int minute) {

            super();
            this.hour = hour;
            this.minute = minute;
        }

        @Override
        public String getKey() {

            return StringUtils.leftPad(String.valueOf(hour), 2, "0") + ":" + StringUtils.leftPad(String.valueOf(minute), 2, "0");
        }

        @Override
        public String getValue() {

            return hour + "点" + minute + "分";
        }
    }
    private class MinuteStrKeyValue implements IntKeyValue {

        private int minute;

        public MinuteStrKeyValue(int minute) {

            super();
            this.minute = minute;
        }

        @Override
        public long getKey() {

            return minute;
        }

        @Override
        public String getValue() {

            return minute + "分钟";
        }
    }
}
