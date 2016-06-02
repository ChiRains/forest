package com.qcloud.component.orderform.engine;

import java.util.List;
import com.qcloud.component.my.QMyCoupon;
import com.qcloud.component.orderform.model.MembershipCardOrder;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.QStore;

public interface ScanCard4IntegralOrderService {

    MembershipCardOrder order(QUser user, QMerchant merchant, QStore store, long memberId, Double sum, List<QMyCoupon> myCouponList);
}
