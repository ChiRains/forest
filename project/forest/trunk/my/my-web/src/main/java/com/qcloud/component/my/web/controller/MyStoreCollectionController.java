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

    /**
     * 展示我的店铺收藏
     * @param request
     * @param pPage
     * @param classifyId
     * @return
     */
    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request, PPage pPage, Long classifyId) {

        List<MyCollection> list = myCollectionController.list(request, pPage, CollectionType.STORE, classifyId);
        List<MyStoreCollectionVO> myStoreCollectionVOs = myCollectionHandler.toStoreMyCollectionVOList(list);
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
    public FrontAjaxView get(HttpServletRequest request, Long id) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MyCollection myCollection = myCollectionService.get(id, user.getId());
        MyStoreCollectionVO myStoreCollectionVO = myCollectionHandler.toStoreMyCollectionVO(myCollection);
        AssertUtil.notNull(myCollection, "找不到相关收藏");
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("result", myStoreCollectionVO);
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
    public FrontAjaxView removeAll(HttpServletRequest request, List<Long> idList) {

        return myCollectionController.removeList(request, idList);
    }
}
