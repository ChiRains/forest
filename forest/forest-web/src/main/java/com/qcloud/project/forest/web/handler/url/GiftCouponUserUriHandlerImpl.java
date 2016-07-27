package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class GiftCouponUserUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/giftCouponUser/list.do");
        list.add("/admin/giftCouponUser/toAdd.do");
        list.add("/admin/giftCouponUser/toEdit.do");
        list.add("/admin/giftCouponUser/add.do");
        list.add("/admin/giftCouponUser/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/giftCouponUser/list.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/giftCouponUser/getGiftCouponUseRule.do");
        list.add("/app/giftCouponUser/getGiftCouponUseRule.do");
        list.add("/app/giftCouponUser/getGiftCouponUseHtmlRule.do");
        list.add("/giftCouponUser/getGiftCouponUseHtmlRule.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/giftCouponUser/myGiftCoupon.do");
        list.add("/app/giftCouponUser/get.do");
        list.add("/app/giftCouponUser/delete.do");
        list.add("/app/giftCouponUser/getGiftCouponUseRule.do");
        list.add("/app/giftCouponUser/getGiftCouponUseHtmlRule.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/giftCouponUser/myGiftCoupon.do");
        list.add("/giftCouponUser/get.do");
        list.add("/giftCouponUser/delete.do");
        list.add("/app/giftCouponUser/myGiftCoupon.do");
        list.add("/app/giftCouponUser/get.do");
        list.add("/app/giftCouponUser/delete.do");
        return list;
    }
}
