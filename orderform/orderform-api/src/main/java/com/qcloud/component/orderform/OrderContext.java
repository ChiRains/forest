package com.qcloud.component.orderform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.my.QMyConsignee;
import com.qcloud.component.my.QMyCoupon;
import com.qcloud.component.my.QMyDelivery;
import com.qcloud.component.my.QMyInvoice;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.sellercenter.QMerchant;

public class OrderContext {

    // 下单用户 必填
    private QUser                                               user         = null;

    // 收货信息 必填
    private QMyConsignee                                        consignee    = null;

    // 发票 可为空
    private QMyInvoice                                          invoice;

    // 物流信息 可为空
    private Map<QMerchant, OrderDelivery>                       deliveryMap  = new HashMap<QMerchant, OrderDelivery>();

    // 下单描述 可为空
    private Map<QMerchant, String>                              explainMap   = new HashMap<QMerchant, String>();

    // 使用优惠劵 可为空
    private Map<QMerchant, List<QMyCoupon>>                     myCouponMap  = new HashMap<QMerchant, List<QMyCoupon>>();

    // 购买物品 必填
    private Map<QUnifiedMerchandise, Integer>                   merchandiseMap;

    private Map<QUnifiedMerchandise, List<QUnifiedMerchandise>> freeMerchandiseMap;

    private int                                                 discount     = 100;

    // 商家列表
    private List<QMerchant>                                     merchantList = new ArrayList<QMerchant>();

    public QUser getUser() {

        return user;
    }

    public void setUser(QUser user) {

        this.user = user;
    }

    public Map<QMerchant, String> getExplainMap() {

        return explainMap;
    }

    public void setExplainMap(Map<QMerchant, String> explainMap) {

        this.explainMap = explainMap;
    }

    public QMyConsignee getConsignee() {

        return consignee;
    }

    public void setConsignee(QMyConsignee consignee) {

        this.consignee = consignee;
    }

    public QMyInvoice getInvoice() {

        return invoice;
    }

    public void setInvoice(QMyInvoice invoice) {

        this.invoice = invoice;
    }

    public Map<QMerchant, List<QMyCoupon>> getMyCouponMap() {

        return myCouponMap;
    }

    public void setMyCouponMap(Map<QMerchant, List<QMyCoupon>> myCouponMap) {

        this.myCouponMap = myCouponMap;
    }

    public Map<QUnifiedMerchandise, Integer> getMerchandiseMap() {

        return merchandiseMap;
    }

    public void setMerchandiseMap(Map<QUnifiedMerchandise, Integer> merchandiseMap) {

        this.merchandiseMap = merchandiseMap;
    }

    public int getDiscount() {

        return discount;
    }

    public void setDiscount(int discount) {

        this.discount = discount;
    }
    //
    public static class OrderDelivery {

        private QMyDelivery delivery;

        private String      sexpressCode;

        public QMyDelivery getDelivery() {

            return delivery;
        }

        public void setDelivery(QMyDelivery delivery) {

            this.delivery = delivery;
        }

        public String getSexpressCode() {

            return sexpressCode;
        }

        public void setSexpressCode(String sexpressCode) {

            this.sexpressCode = sexpressCode;
        }
    }

    public Map<QMerchant, OrderDelivery> getDeliveryMap() {

        return deliveryMap;
    }

    public void setDeliveryMap(Map<QMerchant, OrderDelivery> deliveryMap) {

        this.deliveryMap = deliveryMap;
    }

    public List<QMerchant> getMerchantList() {

        return merchantList;
    }

    public void setMerchantList(List<QMerchant> merchantList) {

        this.merchantList = merchantList;
    }

    public QMerchant getMerchant(long merchantId) {

        for (QMerchant merchant : merchantList) {
            if (merchant.getId() == merchantId) {
                return merchant;
            }
        }
        return null;
    }
}
