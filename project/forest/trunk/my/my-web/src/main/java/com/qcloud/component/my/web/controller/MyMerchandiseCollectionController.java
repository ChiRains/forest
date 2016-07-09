package com.qcloud.component.my.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.my.model.MyCollection;
import com.qcloud.component.my.model.key.TypeEnum.CollectionType;
import com.qcloud.component.my.web.handler.MyCollectionHandler;
import com.qcloud.component.my.web.vo.MyMerchandiseCollectionVO;
import com.qcloud.component.piratesship.web.form.ListForm;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;

@Controller
@RequestMapping(value = MyMerchandiseCollectionController.DIR)
public class MyMerchandiseCollectionController {

    public static final String     DIR = "/myMerchandiseCollection";

    @Autowired
    private MyCollectionHandler    myCollectionHandler;

    @Autowired
    private MyCollectionController myCollectionController;

    @Autowired
    private CommoditycenterClient  commoditycenterClient;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView collect(HttpServletRequest request, Long unifiedMerchandiseId) {

        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        return myCollectionController.collect(request, unifiedMerchandiseId, unifiedMerchandise.getMallClassifyId(), CollectionType.MERCHANDISE);
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView isCollect(HttpServletRequest request, Long unifiedMerchandiseId) {

        return myCollectionController.isCollect(request, unifiedMerchandiseId, CollectionType.MERCHANDISE);
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView remove(HttpServletRequest request, Long id) {

        return myCollectionController.remove(request, id);
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView removeList(HttpServletRequest request, ListForm form) {

        List<Long> idList = form.getLongList();
        return myCollectionController.removeList(request, idList);
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView removeByUnifiedMerchandise(HttpServletRequest request, Long unifiedMerchandiseId) {

        return myCollectionController.removeByObject(request, unifiedMerchandiseId, CollectionType.MERCHANDISE);
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView removeByUnifiedMerchandiseList(HttpServletRequest request, ListForm form) {

        List<Long> unifiedMerchandiseIdList = form.getLongList();
        return myCollectionController.removeByObjectList(request, unifiedMerchandiseIdList, CollectionType.MERCHANDISE);
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request, PPage pPage, Long classifyId) {

        List<MyCollection> list = myCollectionController.list(request, pPage, CollectionType.MERCHANDISE, classifyId);
        List<MyMerchandiseCollectionVO> voList = myCollectionHandler.toMerchandiseMyCollectionVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的商品收藏成功.");
        view.addObject("list", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView clearMyCollect(HttpServletRequest request, PPage pPage) {

        pPage.setPageNum(1);
        pPage.setPageSize(Integer.MAX_VALUE);
        List<MyCollection> list = myCollectionController.list(request, pPage, CollectionType.MERCHANDISE, 0L);
        for (MyCollection myCollection : list) {
            myCollectionController.remove(request, myCollection.getId());
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("清空我的商品收藏成功.");
        return view;
    }
}
