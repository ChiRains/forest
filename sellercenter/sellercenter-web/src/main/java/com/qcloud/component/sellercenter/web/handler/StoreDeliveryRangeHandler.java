package com.qcloud.component.sellercenter.web.handler;

import java.util.List;
import com.qcloud.component.sellercenter.model.StoreDeliveryRange;
import com.qcloud.component.sellercenter.web.vo.admin.AdminStoreDeliveryRangeVO;

public interface StoreDeliveryRangeHandler {

    List<AdminStoreDeliveryRangeVO> toVOList4Admin(List<StoreDeliveryRange> list);

    AdminStoreDeliveryRangeVO toVO4Admin(StoreDeliveryRange storeDeliveryRange);
}
