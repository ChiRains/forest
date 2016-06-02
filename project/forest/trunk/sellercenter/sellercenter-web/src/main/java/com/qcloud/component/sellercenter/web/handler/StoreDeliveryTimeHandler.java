package com.qcloud.component.sellercenter.web.handler;

import java.util.List;
import com.qcloud.component.sellercenter.model.StoreDeliveryTime;
import com.qcloud.component.sellercenter.web.vo.admin.AdminStoreDeliveryTimeVO;

public interface StoreDeliveryTimeHandler {

    List<AdminStoreDeliveryTimeVO> toVOList4Admin(List<StoreDeliveryTime> list);

    AdminStoreDeliveryTimeVO toVO4Admin(StoreDeliveryTime storeDeliveryTime);
}
