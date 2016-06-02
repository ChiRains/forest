package com.qcloud.component.brokerage.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.model.UpgradeOrder;
import com.qcloud.component.brokerage.model.query.UpgradeOrderQuery;

public interface UpgradeOrderService {

    public boolean add(UpgradeOrder upgradeOrder);

    public UpgradeOrder get(Long id);

    public boolean delete(Long id);

    public boolean update(UpgradeOrder upgradeOrder);

    public Page<UpgradeOrder> page(UpgradeOrderQuery query, int start, int count);

    public List<UpgradeOrder> listAll();

    // 支付
    boolean pay(long orderId, int paymentMode);
}
