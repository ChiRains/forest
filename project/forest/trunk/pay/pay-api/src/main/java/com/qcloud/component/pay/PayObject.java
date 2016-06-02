package com.qcloud.component.pay;

import java.util.Date;

public interface PayObject {

    boolean canPay();

    Long getObjectId();

    Date getOccurTime();

    String getObjectNumber();

    double getCash();
}
