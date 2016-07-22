package com.qcloud.project.forest.service.oms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.OrderformClient;

@Component
public class OmsDispatcherServiceImpl implements OmsDispatcherService {

    @Autowired
    public OrderformClient orderformClient;

    @Override
    public boolean deliverOrder(String name) {

        System.out.println("====================" + name);
        // orderformClient.exchangeOrderState(orderId, orderDate, state, -1L);
        return false;
    }
}
