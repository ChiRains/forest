package com.qcloud.component.my.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.autoid.UniqueCodeGenerator;
import com.qcloud.component.autoid.service.UniqueCodeCalculator;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.my.model.MyShoppingCart;
import com.qcloud.component.my.service.MyShoppingCartService;
import com.qcloud.component.my.web.handler.MyShoppingCartHandler;
import com.qcloud.component.my.web.vo.CombinationListVO;
import com.qcloud.component.my.web.vo.MyShoppingCartClassifyVO;
import com.qcloud.component.my.web.vo.MyShoppingCartCombinationVO;
import com.qcloud.component.my.web.vo.MyShoppingCartMerchantVO;
import com.qcloud.component.my.web.vo.MyShoppingCartVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.piratesship.web.form.ListForm;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = MyShoppingCartController.DIR)
public class MyShoppingCartController {

    public static final String    DIR        = "/myShoppingCart";

    @Autowired
    private MyShoppingCartService myShoppingCartService;

    @Autowired
    private MyShoppingCartHandler myShoppingCartHandler;

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Autowired
    private UniqueCodeGenerator   uniqueCodeGenerator;

    private static final String   group_code = "my-shopping-cart-group-code";

    @PiratesApp
    @RequestMapping
    public FrontAjaxView clear(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        myShoppingCartService.clean(user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("清空购物车成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView reduce(HttpServletRequest request, Long unifiedMerchandiseId, Integer number) {

        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "获取商品信息失败.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MyShoppingCart myShoppingCart = myShoppingCartService.getByUnifiedMerchandise(unifiedMerchandiseId, user.getId());
        if (myShoppingCart != null) {
            myShoppingCart.setTime(new Date());
            // 数量累计
            myShoppingCart.setNumber(myShoppingCart.getNumber() - (number == null || number <= 0 ? 1 : number));
            if (myShoppingCart.getNumber() <= 0) {
                myShoppingCartService.delete(myShoppingCart.getId(), user.getId());
            } else {
                myShoppingCartService.update(myShoppingCart);
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("减少购物车成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView add(HttpServletRequest request, Long unifiedMerchandiseId, Integer number) {

        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "获取商品信息失败.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        synchronized (String.valueOf(user.getId())) {
            MyShoppingCart myShoppingCart = myShoppingCartService.getByUnifiedMerchandise(unifiedMerchandiseId, user.getId());
            if (myShoppingCart == null) {
                myShoppingCart = new MyShoppingCart();
                myShoppingCart.setTime(new Date());
                myShoppingCart.setNumber(number == null || number < 0 ? 1 : number);
                myShoppingCart.setUnifiedMerchandiseId(unifiedMerchandiseId);
                myShoppingCart.setMerchantId(unifiedMerchandise.getMerchantId());
                myShoppingCart.setMerchantClassifyId(unifiedMerchandise.getMerchantClassifyId());
                myShoppingCart.setUserId(user.getId());
                // TODO
                String group = uniqueCodeGenerator.generate(group_code, new HashMap<String, String>());
                myShoppingCart.setGroup(group);
                myShoppingCart.setCombinationMerchandiseId(-1L);
                myShoppingCartService.add(myShoppingCart);
            } else {
                myShoppingCart.setTime(new Date());
                // 数量累计
                myShoppingCart.setNumber((number == null || number < 0 ? 1 : number) + myShoppingCart.getNumber());
                myShoppingCartService.update(myShoppingCart);
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("添加购物车成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView addList(HttpServletRequest request, ListForm list) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        for (Long unifiedMerchandiseId : list.getLongList()) {
            QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
            AssertUtil.assertNotNull(unifiedMerchandise, "获取商品信息失败.");
            MyShoppingCart myShoppingCart = myShoppingCartService.getByUnifiedMerchandise(unifiedMerchandiseId, user.getId());
            if (myShoppingCart == null) {
                myShoppingCart = new MyShoppingCart();
                myShoppingCart.setTime(new Date());
                myShoppingCart.setNumber(1);
                myShoppingCart.setUnifiedMerchandiseId(unifiedMerchandiseId);
                myShoppingCart.setMerchantId(unifiedMerchandise.getMerchantId());
                myShoppingCart.setMerchantClassifyId(unifiedMerchandise.getList().get(0).getMerchantClassifyId());
                myShoppingCart.setUserId(user.getId());
                myShoppingCartService.add(myShoppingCart);
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("添加购物车成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView remove(HttpServletRequest request, Long unifiedMerchandiseId) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MyShoppingCart myShoppingCart = myShoppingCartService.getByUnifiedMerchandise(unifiedMerchandiseId, user.getId());
        AssertUtil.assertNotNull(myShoppingCart, "购物车中商品条目不存在." + unifiedMerchandiseId);
        myShoppingCartService.delete(myShoppingCart.getId(), user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("移除购物车成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView removeList(HttpServletRequest request, ListForm form) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<Long> idList = form.getLongList();
        List<MyShoppingCart> list = new ArrayList<MyShoppingCart>();
        for (Long id : idList) {
            MyShoppingCart myShoppingCart = myShoppingCartService.getByUnifiedMerchandise(id, user.getId());
            AssertUtil.assertNotNull(myShoppingCart, "购物车中商品条目不存在." + id);
            list.add(myShoppingCart);
        }
        for (MyShoppingCart myShoppingCart : list) {
            myShoppingCartService.delete(myShoppingCart.getId(), user.getId());
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("移除购物车成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView editor(HttpServletRequest request, Long unifiedMerchandiseId, Integer number) {

        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "获取商品信息失败.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        synchronized (String.valueOf(user.getId())) {
            MyShoppingCart myShoppingCart = myShoppingCartService.getByUnifiedMerchandise(unifiedMerchandiseId, user.getId());
            if (myShoppingCart == null) {
                myShoppingCart = new MyShoppingCart();
                myShoppingCart.setTime(new Date());
                myShoppingCart.setNumber(number == null || number < 0 ? 1 : number);
                myShoppingCart.setUnifiedMerchandiseId(unifiedMerchandiseId);
                myShoppingCart.setMerchantId(unifiedMerchandise.getMerchantId());
                myShoppingCart.setMerchantClassifyId(unifiedMerchandise.getMerchantClassifyId());
                myShoppingCart.setUserId(user.getId());
                myShoppingCartService.add(myShoppingCart);
            } else {
                myShoppingCart.setTime(new Date());
                // 数量累计
                myShoppingCart.setNumber((number == null || number < 0 ? 1 : number));
                myShoppingCartService.update(myShoppingCart);
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改购物车商品成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listAll(HttpServletRequest request) {

        PPage pPage = new PPage();
        pPage.setPageSize(Integer.MAX_VALUE);
        FrontAjaxView view = list(request, pPage);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyShoppingCart> list = myShoppingCartService.list(user.getId(), pPage.getPageStart(), pPage.getPageSize());
        List<MyShoppingCartVO> voList = myShoppingCartHandler.toVOList(list);
        double sum = 0.0;
        for (MyShoppingCartVO myShoppingCartVO : voList) {
            sum += myShoppingCartVO.getNumber() * myShoppingCartVO.getDiscount();
        }
        int total = myShoppingCartService.count(user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取购物车商品成功.");
        view.addObject("data", voList);
        view.addObject("total", total);
        view.addObject("sum", sum);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listAll4Merchant(HttpServletRequest request) {

        PPage pPage = new PPage();
        pPage.setPageSize(Integer.MAX_VALUE);
        FrontAjaxView view = list4Merchant(request, pPage);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list4Merchant(HttpServletRequest request, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyShoppingCart> list = myShoppingCartService.list(user.getId(), pPage.getPageStart(), pPage.getPageSize());
        List<MyShoppingCartMerchantVO> voList = myShoppingCartHandler.toVOList4Merchant(list);
        double sum = 0.0;
        for (MyShoppingCartMerchantVO myShoppingCartMerchantVO : voList) {
            for (MyShoppingCartVO myShoppingCartVO : myShoppingCartMerchantVO.getMerchandiseList()) {
                sum += myShoppingCartVO.getNumber() * myShoppingCartVO.getDiscount();
            }
        }
        int total = myShoppingCartService.count(user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取购物车商品成功.");
        view.addObject("data", voList);
        view.addObject("total", total);
        view.addObject("sum", sum);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listAll4Classify(HttpServletRequest request) {

        PPage pPage = new PPage();
        pPage.setPageSize(Integer.MAX_VALUE);
        FrontAjaxView view = list4Classify(request, pPage);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list4Classify(HttpServletRequest request, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyShoppingCart> list = myShoppingCartService.list(user.getId(), pPage.getPageStart(), pPage.getPageSize());
        List<MyShoppingCartClassifyVO> voList = myShoppingCartHandler.toVOList4Classify(list);
        double sum = 0.0;
        for (MyShoppingCartClassifyVO myShoppingCartClassifyVO : voList) {
            for (MyShoppingCartVO myShoppingCartVO : myShoppingCartClassifyVO.getMerchandiseList()) {
                sum += myShoppingCartVO.getNumber() * myShoppingCartVO.getDiscount();
            }
        }
        int total = myShoppingCartService.count(user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取购物车商品成功.");
        view.addObject("data", voList);
        view.addObject("total", total);
        view.addObject("sum", sum);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView syncList(HttpServletRequest request, ListForm idList, ListForm numList) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        synchronized (String.valueOf(user.getId())) {
            for (int i = 0; i < idList.getLongList().size(); i++) {
                long unifiedMerchandiseId = idList.getLongList().get(i);
                int number = numList.getIntList().get(i);
                QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
                AssertUtil.assertNotNull(unifiedMerchandise, "获取商品信息失败.");
                MyShoppingCart myShoppingCart = myShoppingCartService.getByUnifiedMerchandise(unifiedMerchandiseId, user.getId());
                if (myShoppingCart == null) {
                    myShoppingCart = new MyShoppingCart();
                    myShoppingCart.setTime(new Date());
                    myShoppingCart.setNumber(number);
                    myShoppingCart.setUnifiedMerchandiseId(unifiedMerchandiseId);
                    myShoppingCart.setMerchantId(unifiedMerchandise.getMerchantId());
                    myShoppingCart.setMerchantClassifyId(unifiedMerchandise.getList().get(0).getMerchantClassifyId());
                    myShoppingCart.setUserId(user.getId());
                    myShoppingCartService.add(myShoppingCart);
                }
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("添加购物车成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView addFreeList(HttpServletRequest request, ListForm list, Long combinationMerchandiseId) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        for (Long unifiedMerchandiseId : list.getLongList()) {
            QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
            AssertUtil.assertNotNull(unifiedMerchandise, "获取商品信息失败.");
            MyShoppingCart myShoppingCart = myShoppingCartService.getByUnifiedMerchandise(unifiedMerchandiseId, user.getId());
            if (myShoppingCart == null) {
                myShoppingCart = new MyShoppingCart();
                myShoppingCart.setTime(new Date());
                myShoppingCart.setNumber(1);
                myShoppingCart.setUnifiedMerchandiseId(unifiedMerchandiseId);
                myShoppingCart.setMerchantId(unifiedMerchandise.getMerchantId());
                myShoppingCart.setMerchantClassifyId(unifiedMerchandise.getList().get(0).getMerchantClassifyId());
                myShoppingCart.setUserId(user.getId());
                myShoppingCartService.add(myShoppingCart);
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("添加购物车成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list4Combination(HttpServletRequest request, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyShoppingCart> list = myShoppingCartService.list(user.getId(), pPage.getPageStart(), pPage.getPageSize());
        List<MyShoppingCartCombinationVO> voList = myShoppingCartHandler.toVOList4Combination(list);
        // double sum = 0.0;
        // for (MyShoppingCartMerchantVO myShoppingCartMerchantVO : voList) {
        // for (MyShoppingCartVO myShoppingCartVO : myShoppingCartMerchantVO.getMerchandiseList()) {
        // sum += myShoppingCartVO.getNumber() * myShoppingCartVO.getDiscount();
        // }
        // }
        int total = myShoppingCartService.count(user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取购物车商品成功.");
        view.addObject("data", voList);
        view.addObject("total", total);
        // view.addObject("sum", sum);
        return view;
    }

    public static void main(String[] args) {

        for (int i = 1; i < 100; i++) {
            if (i < 10) {
                System.out.println("create table my_my_shopping_cart_00" + i + " like my_my_shopping_cart_000;");
            } else {
                System.out.println("create table my_my_shopping_cart_0" + i + " like my_my_shopping_cart_000;");
            }
        }
    }
}
