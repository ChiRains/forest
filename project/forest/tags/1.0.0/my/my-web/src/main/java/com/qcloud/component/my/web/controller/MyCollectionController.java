package com.qcloud.component.my.web.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.my.model.MyCollection;
import com.qcloud.component.my.model.key.TypeEnum.CollectionType;
import com.qcloud.component.my.service.MyCollectionService;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Component
public class MyCollectionController {

    @Autowired
    private MyCollectionService myCollectionService;

    public FrontAjaxView collect(HttpServletRequest request, Long objId, Long classifyId, CollectionType collectionType) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MyCollection myCollection = myCollectionService.getByObject(objId, user.getId(), collectionType);
        if (myCollection == null) {
            myCollection = new MyCollection();
            myCollection.setTime(new Date());
            myCollection.setObjId(objId);
            myCollection.setClassifyId(classifyId);
            myCollection.setUserId(user.getId());
            myCollection.setType(collectionType.getKey());
            myCollectionService.add(myCollection);
        } else {
            myCollection.setTime(new Date());
            myCollectionService.update(myCollection);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("收藏成功.");
        return view;
    }

    public FrontAjaxView isCollect(HttpServletRequest request, Long objId, CollectionType collectionType) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MyCollection myCollection = myCollectionService.getByObject(objId, user.getId(), collectionType);
        boolean collect = myCollection != null;
        FrontAjaxView view = new FrontAjaxView();
        if (collect) {
            view.addObject("id", myCollection.getId());
            view.setMessage("已经收藏.");
        } else {
            view.addObject("id", -1L);
            view.setMessage("尚未收藏.");
        }
        view.addObject("collect", String.valueOf(collect));
        return view;
    }

    public FrontAjaxView remove(HttpServletRequest request, Long id) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MyCollection myCollection = myCollectionService.get(id, user.getId());
        AssertUtil.assertNotNull(myCollection, "收藏条目不存在." + id);
        myCollectionService.delete(myCollection.getId(), user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("取消收藏成功.");
        return view;
    }

    public FrontAjaxView removeList(HttpServletRequest request, List<Long> idList) {

        AssertUtil.assertNotEmpty(idList, "ID列表不能为空.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        for (Long id : idList) {
            if (id == null || id <= 0) {
                continue;
            }
            MyCollection myCollection = myCollectionService.get(id, user.getId());
            if (myCollection != null) {
                myCollectionService.delete(myCollection.getId(), user.getId());
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("取消收藏成功.");
        return view;
    }

    public FrontAjaxView removeByObject(HttpServletRequest request, Long objId, CollectionType collectionType) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MyCollection myCollection = myCollectionService.getByObject(objId, user.getId(), collectionType);
        AssertUtil.assertNotNull(myCollection, "收藏不存在." + objId);
        myCollectionService.delete(myCollection.getId(), user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("取消收藏成功.");
        return view;
    }

    public FrontAjaxView removeByObjectList(HttpServletRequest request, List<Long> objIdList, CollectionType collectionType) {

        AssertUtil.assertNotEmpty(objIdList, "ID列表不能为空.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        for (Long objId : objIdList) {
            if (objId == null || objId <= 0) {
                continue;
            }
            MyCollection myCollection = myCollectionService.getByObject(objId, user.getId(), collectionType);
            if (myCollection != null) {
                myCollectionService.delete(myCollection.getId(), user.getId());
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("取消收藏成功.");
        return view;
    }

    public List<MyCollection> list(HttpServletRequest request, PPage pPage, CollectionType collectionType, Long classifyId) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyCollection> list = myCollectionService.list(user.getId(), collectionType, classifyId, pPage.getPageStart(), pPage.getPageSize());
        return list;
    }
}
