package com.qcloud.component.orderform.web.limit;

import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.AutoChangeTimeLimit;

@Component
public class NormalOrderInvalidLimit implements AutoChangeTimeLimit {

    @Override
    public int limit() {

        // TODO
        return 60;
    }
}
