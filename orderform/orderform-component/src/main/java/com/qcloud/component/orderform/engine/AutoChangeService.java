package com.qcloud.component.orderform.engine;

import java.util.Date;

public interface AutoChangeService {

    void autoChange();

    Date getStateValidTime(int type, int state, Date date);
}
