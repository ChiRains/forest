package com.qcloud.project.forest.service.oms;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.pirates.util.DateUtil;
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
    private OrderformClient orderformClient;

    @Override
    public boolean deliverOrder(QueryForm queryForm) {

        System.out.println("====================" + queryForm.getSecret());
        // orderformClient.exchangeOrderState(orderId, orderDate, state, -1L);
        return false;
    }

    @Override
    public XmlOrder getOrder(QueryForm queryForm) {

        // 订单号
        String orderNumber = queryForm.getTid();
        QOrder qOrder = orderformClient.getOrder(orderNumber);
        // 订单
        Order order = new Order();
        // order.setTid(qOrder.getOrderNumber());
        // order.setStatus(String.valueOf(qOrder.getState()));
        // order.setModified(DateUtil.date2String(qOrder.getLastUpdateTime()));
        // order.setCreated(created);
        // order.setShipping_type(shipping_type);
        // order.setPost_fee(post_fee);
        // order.setReceiver_name(receiver_name);
        // order.setReceiver_state(receiver_state);
        // order.setReceiver_city(receiver_city);
        // order.setReceiver_district(receiver_district);
        // order.setReceiver_address(receiver_address);
        // order.setReceiver_zip(receiver_zip);
        // order.setReceiver_mobile(receiver_mobile);
        // order.setReceiver_phone(receiver_phone);
        // order.setBuyer_nick(buyer_nick);
        // order.setBuyer_name(buyer_name);
        // order.setIs_tax(is_tax);
        // order.setInvoice_type(invoice_type);
        // order.setInvoice_title(invoice_title);
        // order.setPay_type(pay_type);
        // order.setReal_pay(real_pay);
        // order.setTotal_fee(total_fee);
        // order.setBuyer_message(buyer_message);
        // order.setBuyer_cod_fee(buyer_cod_fee);
        // order.setPoint_fee(point_fee);
        // order.setCoupon_pay(coupon_pay);
        // order.setPaytime(paytime);
        // order.setSeller_memo(seller_memo);
        //
        XmlOrder xmlOrder = new XmlOrder();
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
