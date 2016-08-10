package com.qcloud.component.pay;

import java.util.Date;

public interface QPayRecord {

    long getId();

    long getObjectId();

    Date getOccurTime();

    String getTradeId();

    String getModule();

    String getType();

    String getClient();

    String getAttach();

    Date getTime();
}
