package com.qcloud.project.forest.service.oms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.project.forest.model.oms.AbstractForm;
import com.qcloud.project.forest.model.oms.QueryForm;

@Component
public class OmsDispatcherServiceImpl implements OmsDispatcherService {

    @Autowired
    public OrderformClient orderformClient;

    @Override
    public boolean deliverOrder(QueryForm queryForm) {

        System.out.println("====================" + queryForm.getSecret());
        // orderformClient.exchangeOrderState(orderId, orderDate, state, -1L);
        return false;
    }
}
