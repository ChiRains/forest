package com.qcloud.project.forest.service.oms;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.project.forest.model.oms.Item;
import com.qcloud.project.forest.model.oms.Logistics_Company;
import com.qcloud.project.forest.model.oms.Order;
import com.qcloud.project.forest.model.oms.Product;
import com.qcloud.project.forest.model.oms.Sku;
import com.qcloud.project.forest.model.oms.XmlLogisticsCompany;
import com.qcloud.project.forest.model.oms.XmlMerchandise;
import com.qcloud.project.forest.model.oms.XmlMerchandiseBatch;
import com.qcloud.project.forest.model.oms.XmlOrderBatch;
import com.qcloud.project.forest.model.oms.QueryForm;
import com.qcloud.project.forest.model.oms.XmlOrder;
import com.qcloud.project.forest.model.oms.XmlStock;

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
    public XmlOrder getOrder(QueryForm queryForm) {

        XmlOrder xmlOrder = new XmlOrder();
        Order order = new Order();
        // String tid = queryForm.getTid();
        List<Item> itemlist = new ArrayList<Item>();
        Item item1 = new Item();
        item1.setSku_name("商铺名称1");
        Item item2 = new Item();
        item2.setSku_name("商铺名称2");
        itemlist.add(item1);
        itemlist.add(item2);
        order.setItemlist(itemlist);
        xmlOrder.setOrder(order);
        return xmlOrder;
    }

    @Override
    public XmlOrderBatch getBatchOrder(QueryForm queryForm) {

        XmlOrderBatch xmlOrderBatch = new XmlOrderBatch();
        List<Order> list = new ArrayList<Order>();
        Order order1 = new Order();
        order1.setBuyer_cod_fee(1);
        Order order2 = new Order();
        order2.setBuyer_cod_fee(2);
        list.add(order1);
        list.add(order2);
        xmlOrderBatch.setOrder_list(list);
        return xmlOrderBatch;
    }

    @Override
    public XmlStock updateStock(QueryForm queryForm) {

        XmlStock xmlStock = new XmlStock();
        xmlStock.setChanged_at("2016-08-01 16:09:03");
        return xmlStock;
    }

    @Override
    public XmlMerchandise getMerchandise(QueryForm queryForm) {

        XmlMerchandise xmlMerchandise = new XmlMerchandise();
        Product product = new Product();
        List<Sku> sku_list = new ArrayList<Sku>();
        Sku sku1 = new Sku();
        Sku sku2 = new Sku();
        sku_list.add(sku1);
        sku_list.add(sku2);
        product.setSku_list(sku_list);
        xmlMerchandise.setProduct(product);
        return xmlMerchandise;
    }

    @Override
    public XmlMerchandiseBatch listMerchandises(QueryForm queryForm) {

        XmlMerchandiseBatch xmlMerchandiseBatch = new XmlMerchandiseBatch();
        List<Product> products = new ArrayList<Product>();
        Product product = new Product();
        Product product1 = new Product();
        List<Sku> sku_list = new ArrayList<Sku>();
        Sku sku1 = new Sku();
        Sku sku2 = new Sku();
        sku_list.add(sku1);
        sku_list.add(sku2);
        product.setSku_list(sku_list);
        product1.setSku_list(sku_list);
        products.add(product);
        products.add(product1);
        xmlMerchandiseBatch.setProduct_list(products);
        return xmlMerchandiseBatch;
    }

    @Override
    public XmlLogisticsCompany getLogisticsCompany(QueryForm queryForm) {

        XmlLogisticsCompany xmlLogisticsCompany = new XmlLogisticsCompany();
        List<Logistics_Company> list = new ArrayList<Logistics_Company>();
        Logistics_Company logisticsCompanies = new Logistics_Company();
        logisticsCompanies.setCode("00001");
        logisticsCompanies.setId(1111);
        logisticsCompanies.setName("圆通");
        list.add(logisticsCompanies);
        xmlLogisticsCompany.setLogistics_companies(list);
        return xmlLogisticsCompany;
    }
}
