package com.qcloud.component.my.web.handler;

import java.util.List;
import com.qcloud.component.my.model.DeliveryMode;
import com.qcloud.component.my.web.vo.DeliveryModeVO;

public interface DeliveryModeHandler {

    List<DeliveryModeVO> toVOList(List<DeliveryMode> list);

    DeliveryModeVO toVO(DeliveryMode deliveryMode);
}
