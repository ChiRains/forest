package com.qcloud.component.my.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.model.MyCollection;
import com.qcloud.component.my.model.key.TypeEnum.CollectionType;
import com.qcloud.component.my.service.MyCollectionService;
import com.qcloud.component.my.web.handler.MyCollectionHandler;
import com.qcloud.component.my.web.vo.MyStoreCollectionVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = MyStoreCollectionController.DIR)
public class MyStoreCollectionController {

    public static final String     DIR = "/myStoreCollection";

    @Autowired
    private MyCollectionController myCollectionController;

    @Autowired
    private MyCollectionHandler    myCollectionHandler;

    @Autowired
    private MyCollectionService    myCollectionService;

    public static double Distance(double long1, double lat1, double long2, double lat2) {

        double a, b, R;
        R = 6378137; // 地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
        return d;
    }

    /**
     * 展示我的店铺收藏
     * @param request
     * @param pPage
     * @param classifyId
     * @return
     */
    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request, PPage pPage, Long classifyId, Double latitude, Double longitude) {

        List<MyCollection> list = myCollectionController.list(request, pPage, CollectionType.STORE, classifyId);
        List<MyStoreCollectionVO> myStoreCollectionVOs = myCollectionHandler.toStoreMyCollectionVOList(list);
        for (MyStoreCollectionVO myStoreCollectionVO : myStoreCollectionVOs) {
            if (latitude != 0 && longitude != 0) {
                myStoreCollectionVO.setDistance(Distance(longitude, latitude, myStoreCollectionVO.getLongitude(), myStoreCollectionVO.getLatitude()));
            } else if (latitude == 0 || longitude == 0) {
                myStoreCollectionVO.setDistance(0.0);
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的店铺收藏成功.");
        view.addObject("list", myStoreCollectionVOs);
        return view;
    }

    /**
     * 添加店铺收藏
     * @param request
     * @param storeId
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView collect(HttpServletRequest request, Long storeId) {

        return myCollectionController.collect(request, storeId, -1L, CollectionType.STORE);
    }

    /**
     * 是否收藏过该店铺
     * @param request
     * @param merchantId
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView isCollect(HttpServletRequest request, Long storeId) {

        return myCollectionController.isCollect(request, storeId, CollectionType.STORE);
    }

    /**
     * 移除我的店铺收藏
     * @param request
     * @param id
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView remove(HttpServletRequest request, Long id) {

        return myCollectionController.remove(request, id);
    }

    /**
     * 获取详情
     * @param request
     * @param id
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView get(HttpServletRequest request, Long id, Double latitude, Double longitude) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MyCollection myCollection = myCollectionService.get(id, user.getId());
        MyStoreCollectionVO myStoreCollectionVO = myCollectionHandler.toStoreMyCollectionVO(myCollection);
        myStoreCollectionVO.setDistance(Distance(longitude, latitude, myStoreCollectionVO.getLongitude(), myStoreCollectionVO.getLatitude()));
        AssertUtil.notNull(myCollection, "找不到相关收藏");
        if (latitude != 0 && longitude != 0) {
            myStoreCollectionVO.setDistance(Distance(longitude, latitude, myStoreCollectionVO.getLongitude(), myStoreCollectionVO.getLatitude()));
        } else if (latitude == 0 || longitude == 0) {
            myStoreCollectionVO.setDistance(0.0);
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("result", myStoreCollectionVO);
        if (myCollection != null) {
            frontAjaxView.addObject("isCollect", 1);
        } else {
            frontAjaxView.addObject("isCollect", 0);
        }
        return frontAjaxView;
    }

    /**
     * 清楚店铺收藏
     * @param request
     * @param idList
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView removeAll(HttpServletRequest request, Long classifyId) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        myCollectionService.deleteByUser(user.getId(), CollectionType.STORE, classifyId);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        return frontAjaxView;
    }
}
