package com.qcloud.component.pay;

import java.util.Date;

public interface PaymentOperate {

    PayObject getPayObject(Long objectId, Date occurTime);

    boolean paid(Long objectId, Date occurTime);
}
