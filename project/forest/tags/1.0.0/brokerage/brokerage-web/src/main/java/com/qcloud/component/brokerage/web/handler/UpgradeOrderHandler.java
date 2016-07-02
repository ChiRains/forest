package com.qcloud.component.brokerage.web.handler;

import java.util.List;
import com.qcloud.component.brokerage.model.UpgradeOrder;
import com.qcloud.component.brokerage.web.vo.admin.AdminUpgradeOrderVO;

public interface UpgradeOrderHandler {

    List<AdminUpgradeOrderVO> toVOList4Admin(List<UpgradeOrder> list);

    AdminUpgradeOrderVO toVO4Admin(UpgradeOrder upgradeOrder);
}
