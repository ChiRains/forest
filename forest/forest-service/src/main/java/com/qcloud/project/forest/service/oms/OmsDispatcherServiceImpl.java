package com.qcloud.project.forest.service.oms;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.my.DeliveryModeType;
import com.qcloud.component.my.InvoiceType;
import com.qcloud.component.my.NeedInvoiceType;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.component.orderform.PaymentModeType;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.pay.PayRecordClient;
import com.qcloud.component.pay.QPayRecord;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.util.AssertUtil;
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
    private OrderformClient       orderformClient;

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Autowired
    private PayRecordClient       payClient;

    @Autowired
    private PersonalcenterClient  personalcenterClient;

    @Override
    public boolean deliverOrder(QueryForm queryForm) {

        System.out.println("====================" + queryForm.getSecret());
        // orderformClient.exchangeOrderState(orderId, orderDate, state, -1L);
        return false;
    }

    @Override
    public XmlOrder getOrder(QueryForm queryForm) {

        XmlOrder xmlOrder = new XmlOrder();
        // 订单号
        String orderNumber = queryForm.getTid();
        QOrder qOrder = orderformClient.getOrder(orderNumber);
        Order order = toOrder(qOrder);
        order.setItemlist(toItemList(qOrder));
        xmlOrder.setOrder(order);
        return xmlOrder;
    }

    private Order toOrder(QOrder qOrder) {

        // 订单
        QMerchantOrder qMerchantOrder = qOrder.getMerchantOrderList().get(0);
        QUser qUser = personalcenterClient.getUser(qOrder.getMobile());
        Order order = new Order();
        order.setTid(qOrder.getOrderNumber());
        order.setStatus(String.valueOf(qOrder.getState()));
        order.setModified(DateUtil.date2String(qOrder.getLastUpdateTime()));
        order.setCreated(DateUtil.date2String(qOrder.getOrderDate()));
        order.setShipping_type(DeliveryModeType.get(qMerchantOrder.getDeliveryMode()).getName());
        order.setPost_fee(qOrder.getPostage());
        order.setReceiver_name(qOrder.getConsignee());
        order.setReceiver_state(qOrder.getProvince());
        order.setReceiver_city(qOrder.getCity());
        order.setReceiver_district(qOrder.getDistrict());
        order.setReceiver_address(qOrder.getAddress());
        // 无邮编
        order.setReceiver_zip("-");
        order.setReceiver_mobile(qOrder.getMobile());
        order.setReceiver_phone(qOrder.getMobile());
        order.setBuyer_nick(qOrder.getMobile());
        order.setBuyer_name(qUser.getNickname());
        order.setIs_tax(NeedInvoiceType.get(qOrder.getNeedInvoiceType()).getName());
        order.setInvoice_type(InvoiceType.get(qOrder.getInvoiceType()).getName());
        order.setInvoice_title(qOrder.getInvoice());
        order.setPay_type(PaymentModeType.get(qOrder.getPaymentMode()).getName());
        order.setReal_pay(qOrder.getCash());
        order.setTotal_fee(qOrder.getSum());
        List<QMerchantOrder> merchantOrderList = qOrder.getMerchantOrderList();
        order.setBuyer_message(merchantOrderList.size() > 0 ? merchantOrderList.get(0).getExplain() : "");
        // "无买家货到付款服务费"，货到付款第二期考虑
        order.setBuyer_cod_fee(0);
        // 无积分支付,下一期考虑积分
        order.setPoint_fee(0);
        order.setCoupon_pay(qOrder.getCoupon());
        QPayRecord qPayRecord = payClient.getQPayRecord(qOrder.getId());
        AssertUtil.assertNotNull(qPayRecord, "订单对应支付记录不存在." + qOrder.getId());
        order.setPaytime(DateUtil.date2String(qPayRecord.getTime()));
        // 无卖家备注
        order.setSeller_memo("-");
        return order;
    }

    private List<Item> toItemList(QOrder qOrder) {

        List<Item> itemlist = new ArrayList<Item>();
        List<QMerchantOrder> qMerchantOrders = qOrder.getMerchantOrderList();
        for (QMerchantOrder qMerchantOrder : qMerchantOrders) {
            List<QOrderItem> qOrderItems = qMerchantOrder.getOrderItemList();
            for (QOrderItem qOrderItem : qOrderItems) {
                long unifiedMerchandiseId = qOrderItem.getUnifiedMerchandiseId();
                QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
                AssertUtil.assertNotNull(qUnifiedMerchandise, "统一商品不存在." + unifiedMerchandiseId);
                Item item = new Item();
                item.setProduct_id(String.valueOf(unifiedMerchandiseId));
                // 商家商品编码(什么鬼)
                item.setShop_product_id(qUnifiedMerchandise.getCode());
                item.setTitle(qOrderItem.getName());
                // 无规格id，组合商品如何给规格id
                item.setSku_id(qUnifiedMerchandise.getCode());
                // 无商家规格编码id，组合商品如何给规格id
                item.setShop_sku_id(qUnifiedMerchandise.getCode());
                item.setSku_name(qUnifiedMerchandise.getSpecifications());
                item.setQty_ordered(qOrderItem.getNumber());
                // 单价，折后？
                item.setPrice(qOrderItem.getDiscount());
                // 子订单号,没卵用.
                item.setOid(-1);
                itemlist.add(item);
            }
        }
        return itemlist;
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
