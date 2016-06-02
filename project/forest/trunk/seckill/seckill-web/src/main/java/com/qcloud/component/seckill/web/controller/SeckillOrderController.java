package com.qcloud.component.seckill.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.seckill.model.MerchandiseSeckill;
import com.qcloud.component.seckill.service.MerchandiseSeckillService;
import com.qcloud.component.seckill.service.SeckillConfigService;
import com.qcloud.component.seckill.service.SeckillOrderService;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = SeckillOrderController.DIR)
public class SeckillOrderController {

    public static final String        DIR = "/seckillOrder";

    @Autowired
    private CommoditycenterClient     commoditycenterClient;

    @Autowired
    private SeckillOrderService       seckillOrderService;

    @Autowired
    private MerchandiseSeckillService merchandiseSeckillService;

    @Autowired
    private SeckillConfigService      seckillConfigService;

    @Autowired
    private FileSDKClient             fileSDKClient;

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView order(HttpServletRequest request, Long seckillMerchandiseId) {

        AssertUtil.assertNotNull(seckillMerchandiseId, "秒杀标识不能为空");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        // 锁定秒杀商品
        synchronized (seckillMerchandiseId) {
            MerchandiseSeckill merchandiseSeckill = merchandiseSeckillService.get(seckillMerchandiseId);
            AssertUtil.assertNotNull(merchandiseSeckill, "秒杀记录不存在." + seckillMerchandiseId);
            QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandiseSeckill.getUnifiedMerchandiseId());
            AssertUtil.assertNotNull(unifiedMerchandise, "秒杀商品记录不存在." + merchandiseSeckill.getUnifiedMerchandiseId());
            boolean can = seckillOrderService.canKill(seckillMerchandiseId);
            AssertUtil.assertTrue(can, "已经秒完");
            QOrder order = seckillOrderService.orderSeckill(user, seckillMerchandiseId);
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("秒杀成功.");
            int payMinutes = seckillConfigService.get().getPayMinutes();
            view.addObject("payMinutes", payMinutes);
            view.addObject("payHourDesc", "请您在" + payMinutes + "分钟内支付!");
            view.addObject("name", "【秒】" + unifiedMerchandise.getName());
            view.addObject("image", fileSDKClient.getFileServerUrl() + unifiedMerchandise.getImage());
            view.addObject("price", unifiedMerchandise.getPrice());
            view.addObject("seckillPrice", unifiedMerchandise.getDiscount());
            view.addObject("orderId", order.getId());
            view.addObject("orderDate", DateUtil.date2String(order.getOrderDate(), DateUtil.FORMAT_STRING));
            view.addObject("orderNumber", order.getOrderNumber());
            view.addObject("cash", order.getCash());
            return view;
        }
    }
}
