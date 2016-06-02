package com.qcloud.component.my;

import java.util.Date;

public interface QMyToEvaluation {

    public long getId();

    public long getUserId();

    public long getUnifiedMerchandiseId();

    public long getMerchandiseId();

    public String getName();

    public String getImage();

    public double getDiscount();

    public long getMerchantId();

    public long getOrderId();

    public long getSubOrderId();

    public long getOrderItemId();

    public Date getOrderDate();

    public String getOrderNumber();
}
