package com.qcloud.project.forest.service.oms;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.project.forest.model.oms.Item;
import com.qcloud.project.forest.model.oms.Order;
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

    @Override
    public Order getOrder(QueryForm queryForm) {

        Order order = new Order();
        String tid = queryForm.getTid();
        List<Item> itemlist = new ArrayList<Item>();
        Item item1 = new Item();
        item1.setSku_name("商铺名称1");
        Item item2 = new Item();
        item2.setSku_name("商铺名称2");
        itemlist.add(item1);
        itemlist.add(item2);
        order.setItemlist(itemlist);
        return order;
    }
}
