package com.qcloud.component.sellercenter.web.controller.app;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.publicservice.MessageClient;
import com.qcloud.component.publicservice.QMessage;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.key.TypeEnum;
import com.qcloud.component.sellercenter.web.vo.MessageVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = AppStoreMessageController.DIR)
public class AppStoreMessageController {

    public static final String         DIR = "/app/storeMessage";

//    @Autowired
//    private UserFilterService          userFilterService;
//
//    @Autowired
//    private TokenClient                tokenClient;

//    @Autowired
//    private StoreMemberService         storeMemberService;

//    @Autowired
//    private OutdatedSellercenterClient outdatedSellercenterClient;

    @Autowired
    private MessageClient              messageClient;

    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request, Integer stateType, Integer pageSize, Integer pageNum) {

        final int PAGE_SIZE = pageSize == null || pageSize <= 0 ? 10 : pageSize;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
//        long memberId = getMemberId(request);
//        Long storeId = getStoreId(memberId);
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.APP_STORE_LOGIN_PARAMETER_KEY);
//        AssertUtil.assertTrue(storeId > 0, "您尚未属于一个门店.");
        List<QMessage> list = messageClient.listByReceiver(TypeEnum.STORE_MESSAGE_CODE, -1, store.getId(), start, PAGE_SIZE);
        List<MessageVO> voList = new ArrayList<MessageVO>();
        for (QMessage message : list) {
            MessageVO me = new MessageVO();
            me.setId(message.getId());
            me.setTitle(message.getTitle());
            me.setContent("");
            me.setTime(DateUtil.date2String(message.getTime(), "yyyy-MM-dd HH:mm:ss"));
            me.setRead(message.isRead());
            voList.add(me);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取门店消息成功.");
        view.addObject("list", voList);
        return view;
    }

    @RequestMapping
    public FrontAjaxView get(HttpServletRequest request, Long id) {

//        long memberId = getMemberId(request);
//        Long storeId = getStoreId(memberId);
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.APP_STORE_LOGIN_PARAMETER_KEY);
//        AssertUtil.assertTrue(storeId > 0, "您尚未属于一个门店.");
        QMessage message = messageClient.get(TypeEnum.STORE_MESSAGE_CODE, store.getId(), id);
        AssertUtil.assertNotNull(message, "消息不存在." + id);
        MessageVO me = new MessageVO();
        me.setId(message.getId());
        me.setTitle(message.getTitle());
        me.setContent(message.getContent());
        me.setTime(DateUtil.date2String(message.getTime(), "yyyy-MM-dd HH:mm:ss"));
        me.setRead(message.isRead());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的一条消息成功.");
        view.addObject("message", me);
        return view;
    }

    @RequestMapping
    public FrontAjaxView countAll(HttpServletRequest request) {

//        long memberId = getMemberId(request);
//        Long storeId = getStoreId(memberId);
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.APP_STORE_LOGIN_PARAMETER_KEY);
//        AssertUtil.assertTrue(storeId > 0, "您尚未属于一个门店.");
        int number = messageClient.countByReceiver(TypeEnum.STORE_MESSAGE_CODE, -1, store.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的消息条数成功.");
        view.addObject("total", number);
        return view;
    }

    @RequestMapping
    public FrontAjaxView countUnread(HttpServletRequest request) {

//        long memberId = getMemberId(request);
//        Long storeId = getStoreId(memberId);
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.APP_STORE_LOGIN_PARAMETER_KEY);
//        AssertUtil.assertTrue(storeId > 0, "您尚未属于一个门店.");
        int number = messageClient.countUnreadByReceiver(TypeEnum.STORE_MESSAGE_CODE, -1, store.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的消息未读条数成功.");
        view.addObject("unreadTotal", number);
        return view;
    }

    @RequestMapping
    public FrontAjaxView read(HttpServletRequest request, Long id) {

//        long memberId = getMemberId(request);
//        Long storeId = getStoreId(memberId);
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.APP_STORE_LOGIN_PARAMETER_KEY);
//        AssertUtil.assertTrue(storeId > 0, "您尚未属于一个门店.");
        messageClient.read(TypeEnum.STORE_MESSAGE_CODE, store.getId(), id);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("设置一条消息为已读成功.");
        return view;
    }

    @RequestMapping
    public FrontAjaxView getNewMsgNumber(HttpServletRequest request) {

//        long memberId = getMemberId(request);
//        Long storeId = getStoreId(memberId);
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.APP_STORE_LOGIN_PARAMETER_KEY);
//        AssertUtil.assertTrue(storeId > 0, "您尚未属于一个门店.");
        int number = messageClient.getNewMsgNumber(TypeEnum.STORE_MESSAGE_CODE, store.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取新短消息成功.");
        view.addObject("result", number);
        return view;
    }

    @RequestMapping
    public FrontAjaxView resetNewMsgNumber(HttpServletRequest request) {

//        long memberId = getMemberId(request);
//        Long storeId = getStoreId(memberId);
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.APP_STORE_LOGIN_PARAMETER_KEY);
//        AssertUtil.assertTrue(storeId > 0, "您尚未属于一个门店.");
        messageClient.resetNewMsgNumber(TypeEnum.STORE_MESSAGE_CODE, store.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("重置新消息条数为0成功.");
        return view;
    }

//    private long getMemberId(HttpServletRequest request) {
//
//        String tokenId = userFilterService.getTokenId(request);
//        AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
//        String idStr = tokenClient.get(tokenId);
//        AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
//        long memberId = Long.parseLong(idStr);
//        return memberId;
//    }
//
//    private long getStoreId(Long memberId) {
//
//        long storeId = -1;
//        HashMap where = new HashMap();
//        where.put("memberId", memberId);
//        StoreMember storeMember = storeMemberService.get(where);
//        if (storeMember != null) {
//            storeId = storeMember.getStoreId();
//        }
//        return storeId;
//    }
//
//    public Long getMerchantId(Long memberId) {
//
//        List<QMerchant> merchantList = outdatedSellercenterClient.listMerchant(memberId);
//        long merchantId = -1;
//        if (!CollectionUtils.isEmpty(merchantList) && merchantList.size() == 1) {
//            merchantId = merchantList.get(0).getId();
//        }
//        return merchantId;
//    }
}
