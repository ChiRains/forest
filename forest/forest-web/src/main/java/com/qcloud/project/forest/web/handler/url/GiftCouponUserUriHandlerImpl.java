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
        list.add("/giftCouponUser/myGiftCoupon.do");
        list.add("/giftCouponUser/get.do");
        return list;
    }
}
