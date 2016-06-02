package com.qcloud.component.orderform.web.limit;

import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.AutoChangeTimeLimit;

@Component
public class TradeSuccessLimit implements AutoChangeTimeLimit {

    @Override
    public int limit() {

        // TODO
        return 10080;
    }
}
