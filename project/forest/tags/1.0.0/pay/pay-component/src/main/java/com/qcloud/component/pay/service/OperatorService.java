package com.qcloud.component.pay.service;

import java.util.Date;
import com.qcloud.component.pay.PayObject;

public interface OperatorService {

    PayObject op(String module, Long objectId, Date occurTime);

    boolean notify(String module, Long objectId, Date occurTime);
}
