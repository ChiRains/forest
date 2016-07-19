package com.qcloud.project.forest.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.project.forest.exception.ForestException;
import com.qcloud.project.forest.model.ExpressQueryVO;
import com.qcloud.project.forest.model.IntegralOrder;
import com.qcloud.project.forest.model.key.TypeEnum.IntegralOrderStateType;
import com.qcloud.project.forest.service.ExpressQueryService;
import com.qcloud.project.forest.service.IntegralOrderService;
import com.qcloud.project.forest.web.handler.IntegralOrderHandler;
import com.qcloud.project.forest.web.vo.IntegralOrderListVO;
import com.qcloud.project.forest.web.vo.IntegralOrderVO;

@Controller
@RequestMapping(value = IntegralOrderController.DIR)
public class IntegralOrderController {

    public static final String   DIR = "/integralOrder";

    @Autowired
    private IntegralOrderService integralOrderService;

    @Autowired
    private IntegralOrderHandler integralOrderHandler;

    @Autowired
    private ExpressQueryService  expressQueryService;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView order(HttpServletRequest request, Long unifiedMerchandiseId, Long consigneeId) {

        AssertUtil.greatZero(unifiedMerchandiseId, "积分商品id不能为空.");
        AssertUtil.greatZero(consigneeId, "收货信息不能为空.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        int result = integralOrderService.order(unifiedMerchandiseId, user.getId(), consigneeId);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("积分订单下单成功");
        view.addObject("needPay", result == 0);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<IntegralOrder> orderList = integralOrderService.listByUserAndFront(user.getId(), pPage.getPageStart(), pPage.getPageSize());
        List<IntegralOrderListVO> volist = integralOrderHandler.toListVOList(orderList);
        if (volist.isEmpty()) {
            IntegralOrderListVO order = new IntegralOrderListVO();
            order.setCash(0);
            order.setImage("");
            order.setIntegral(500);
            order.setName("loongma");
            order.setOrderNumber("111");
            order.setCanRemind(true);
            order.setSpecifications("默认规格");
            order.setState(20);
            order.setStateStr("待发货");
            volist.add(order);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("data", volist);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView get(HttpServletRequest request, Long orderId) {

        AssertUtil.greatZero(orderId, "订单id不能为空.");
        IntegralOrder order = integralOrderService.getByOrder(orderId);
        AssertUtil.assertNotNull(order, "订单数据不存在.");
        IntegralOrderVO orderVO = integralOrderHandler.toVO(order);
        // orderVO.setAddress("ss");
        // orderVO.setCash(0);
        // orderVO.setConsignee("aa");
        // orderVO.setEmail("@qq.com");
        // orderVO.setId(1111L);
        // orderVO.setImage("");
        // orderVO.setIntegral(500);
        // orderVO.setMobile("15876333333");
        // orderVO.setName("loongma");
        // orderVO.setOrderNumber("111");
        // orderVO.setPaymentMode(102);
        // orderVO.setPaymentModeStr("微信");
        // orderVO.setCanRemind(true);
        // orderVO.setSpecifications("默认规格");
        // orderVO.setState(20);
        // orderVO.setStateStr("待发货");
        // orderVO.setOrderDateStr(DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
        order.setUnifiedMerchandiseId(11111);
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("order", orderVO);
        view.setMessage("获取积分订单成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView remindOrder(HttpServletRequest request, Long orderId) {

        AssertUtil.greatZero(orderId, "订单id不能为空.");
        IntegralOrder order = integralOrderService.getByOrder(orderId);
        AssertUtil.assertNotNull(order, "订单数据不存在.");
        if (order.getRemind() == 0) {
            order.setRemind(1);
            integralOrderService.update(order);
        } else {
            throw new ForestException("已经提醒过了,请耐心等待哦~");
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("提醒发货成功");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView signOrder(HttpServletRequest request, Long orderId) {

        AssertUtil.greatZero(orderId, "订单id不能为空.");
        IntegralOrder order = integralOrderService.getByOrder(orderId);
        AssertUtil.assertNotNull(order, "订单数据不存在.");
        if (order.getState() == IntegralOrderStateType.TO_SIGN.getKey()) {
            order.setState(IntegralOrderStateType.FINISHED.getKey());
            integralOrderService.update(order);
        } else {
            throw new ForestException("订单状态跳转失败");
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("签收成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getAdvitise(HttpServletRequest request, Long orderId) {

        FrontAjaxView view = new FrontAjaxView();
        view.addObject("image", "");
        view.setMessage("广告图.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView expressByOrder(HttpServletRequest request, Long orderId) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        AssertUtil.greatZero(orderId, "订单id不能为空.");
        IntegralOrder order = integralOrderService.getByOrder(orderId);
        AssertUtil.assertNotNull(order, "订单数据不存在.");
        List<ExpressQueryVO> volist = expressQueryService.getList(user.getId(), order.getExpressCode(), order.getExpressNumber());
        if (CollectionUtils.isEmpty(volist)) {
            ExpressQueryVO vo = new ExpressQueryVO();
            vo.setContext("假数据");
            vo.setLocation("假地址");
            vo.setTime(DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
            volist.add(vo);
            ExpressQueryVO vo2 = new ExpressQueryVO();
            vo2.setContext("假数据2");
            vo2.setLocation("假地址2");
            vo2.setTime(DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
            volist.add(vo2);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取订单物流信息成功");
        view.addObject("image", "");
        view.addObject("list", volist);
        view.addObject("stateStr", "正在派送");
        view.addObject("exist", CollectionUtils.isNotEmpty(volist));
        return view;
    }
}
