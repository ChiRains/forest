package com.qcloud.component.orderform.engine;

import java.util.Date;
import com.union.common.AcpsdkProperties;

public interface UnionPayService {

    String requestUnionPayHtml(long userId, String ip, Long orderId, Date orderDate);

    String requestUnionPayHtml(long id, String ip, Long orderId, Date orderDate, AcpsdkProperties acpsdkProperties);
}
