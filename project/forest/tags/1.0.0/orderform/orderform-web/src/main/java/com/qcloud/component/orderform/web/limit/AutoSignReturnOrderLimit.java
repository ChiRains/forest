package com.qcloud.component.orderform.web.limit;

import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.AutoChangeTimeLimit;

@Component
public class AutoSignReturnOrderLimit implements AutoChangeTimeLimit {

    @Override
    public int limit() {

        // TODO 7天
        return 10080;
    }
}
