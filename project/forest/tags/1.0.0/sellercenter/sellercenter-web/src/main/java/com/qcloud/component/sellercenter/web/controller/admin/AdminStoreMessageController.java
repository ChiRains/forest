package com.qcloud.component.sellercenter.web.controller.admin;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.publicdata.IntKeyValue;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicservice.MessageClient;
import com.qcloud.component.publicservice.QMessage;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.key.TypeEnum;
import com.qcloud.component.sellercenter.model.query.StoreQuery;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMessageVO;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

/**
 * 后台商家消息
 * @author admin
 *
 */
@Controller
@RequestMapping(value = "/" + AdminStoreMessageController.DIR)
public class AdminStoreMessageController {

    public static final String DIR = "admin/storeMessage";

    @Autowired
    private MessageClient      messageClient;

    @Autowired
    private PublicdataClient   publicdataClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer clasify, Integer pageNum, StoreQuery query) {

        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);        
        clasify = clasify == null || clasify == 0 ? -1 : clasify;
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        List<QMessage> Qlist = messageClient.listByReceiver(TypeEnum.STORE_MESSAGE_CODE, clasify, store.getId(), start, PAGE_SIZE);
        int count = messageClient.countByReceiver(TypeEnum.STORE_MESSAGE_CODE, clasify, store.getId());
        List<AdminMessageVO> list = new ArrayList<AdminMessageVO>();
        for (QMessage message : Qlist) {
            AdminMessageVO me = new AdminMessageVO();
            me.setId(message.getId());
            me.setTitle(message.getTitle());
            me.setContent(message.getContent());
            me.setTime(DateUtil.date2String(message.getTime(), "yyyy-MM-dd HH:mm:ss"));
            me.setRead(message.isRead());
            list.add(me);
        }
        String param = "clasify=" + clasify;
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-StoreMessage-list", DIR + "/list?" + param, pageNum, PAGE_SIZE, count);
        pagingView.addObject("result", list);
        List<IntKeyValue> classifyList = messageClient.listMessageClassify(TypeEnum.STORE_MESSAGE_CODE);
        List<KeyValueVO> classifyVOList = publicdataClient.exchageObj(classifyList, clasify, "selected");
        pagingView.addObject("classifyList", classifyVOList);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toShow(HttpServletRequest request, StoreQuery query, long id) {

        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);        
        QMessage message = messageClient.get(TypeEnum.STORE_MESSAGE_CODE, store.getId(), id);
        AdminMessageVO me = new AdminMessageVO();
        me.setId(message.getId());
        me.setTitle(message.getTitle());
        me.setContent(message.getContent());
        me.setTime(DateUtil.date2String(message.getTime(), "yyyy-MM-dd HH:mm:ss"));
        me.setRead(message.isRead());
        if (!me.isRead()) {
            messageClient.read(TypeEnum.STORE_MESSAGE_CODE, store.getId(), id);// 设置已读
        }
        ModelAndView model = new ModelAndView("/admin/sellercenter-StoreMessage-showMessage");
        model.addObject("message", me);
        return model;
    }

//    private long getMemberId(HttpServletRequest request) {
//
//        String tokenId = adminFilterService.getTokenId(request);
//        AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
//        String idStr = tokenClient.get(tokenId);
//        AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
//        long memberId = Long.parseLong(idStr);
//        return memberId;
//    }
//
//    private long getStoreId(Long memberId) {
//
//        long storeId = 0;
//        HashMap where = new HashMap();
//        where.put("memberId", memberId);
//        StoreMember storeMember = storeMemberService.get(where);
//        if (storeMember != null) {
//            storeId = storeMember.getStoreId();
//        } else {
//            AssertUtil.assertTrue(false, "当前登录用户不属于门店!");
//        }
//        return storeId;
//    }
}
