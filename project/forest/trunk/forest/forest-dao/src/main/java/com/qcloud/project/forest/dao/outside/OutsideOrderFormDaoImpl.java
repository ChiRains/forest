package com.qcloud.project.forest.dao.outside;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.project.forest.dao.OutsideOrderFormDao;
import com.qcloud.project.forest.model.outside.entity.AbstractResult;
import com.qcloud.project.forest.model.outside.entity.MessageResult;
import com.qcloud.project.forest.model.outside.entity.SalesOrderInvoiceEntity;
import com.qcloud.project.forest.model.outside.entity.SalesOrderLineEntity;
import com.qcloud.project.forest.model.outside.entity.SalesOrderOfflineEntity;
import com.qcloud.project.forest.model.outside.entity.SalesOrderPayEntity;
import com.qcloud.project.forest.util.OutsideUtil;

/**
 * 访问对外管易订单接口
 */
@Repository
public class OutsideOrderFormDaoImpl implements OutsideOrderFormDao {

    @Value("${forest.outside.host}")
    private String host;

    @Override
    public AbstractResult addSalesOrderOffline(SalesOrderOfflineEntity entity) {

        Map<String, Object> param = new LinkedHashMap<String, Object>();
        param.put("TradeId", entity.getTradeId());
        param.put("Consignee", entity.getConsignee());
        param.put("ShopCode", entity.getShopCode());
        param.put("ShopName", entity.getShopName());
        param.put("PayDate", DateUtil.date2String(entity.getPayDate(), "yyyy-MM-dd'T'HH:mm:ss"));
        param.put("buyer_email", entity.getBuyer_email());
        param.put("buyer_nick", entity.getBuyer_nick());
        param.put("PayAmount", entity.getPayAmount());
        param.put("MemberCode", entity.getMemberCode());
        param.put("MemberName", entity.getMemberName());
        param.put("Quantity", entity.getQuantity());
        param.put("ConsigneeMobile", entity.getConsigneeMobile());
        param.put("ConsigneeTelephone", entity.getConsigneeTelephone());
        param.put("ConsigneeAddress", entity.getConsigneeAddress());
        param.put("ConsigneeProvince", entity.getConsigneeProvince());
        param.put("ConsigneeCity", entity.getConsigneeCity());
        param.put("ConsigneeCounty", entity.getConsigneeCounty());
        param.put("ProductDetail", entity.getProductDetails());
        param.put("PayDetail", entity.getPayDetais());
        param.put("InvoiceDetail", entity.getInvoiceDetails());
        param.put("BuyerMemo", entity.getBuyerMemo());
        param.put("NeedInvoice", entity.isNeedInvoice());
        host = "http://58.63.60.83:30001";
        String str = OutsideUtil.doPost(host + "/PubService.svc/AddSalesOrderOffline", OutsideUtil.transUrlToMap(param));
        System.out.println(str);
        MessageResult messageResult = Json.toObject(str, MessageResult.class, true);
        return messageResult;
    }

    public static void main(String[] args) {

        testAddSalesOrderOffline();
    }

    private static void testAddSalesOrderOffline() {

        SalesOrderOfflineEntity entity = new SalesOrderOfflineEntity();
        OutsideOrderFormDaoImpl impl = new OutsideOrderFormDaoImpl();
        entity.setTradeId("10000006");
        entity.setConsignee("希留");
        entity.setShopCode("GW_01_DSL");
        entity.setShopName("大参林官网");
        entity.setPayDate(new Date());
        entity.setBuyer_email("lhshan@vip.qq.com");
        entity.setBuyer_nick("shiliew");
        entity.setPayAmount(1.0);
        entity.setMemberCode("01");
        entity.setMemberName("谁谁谁");
        entity.setQuantity(5);
        entity.setConsigneeMobile("13750013175");
        entity.setConsigneeTelephone("0754-3399366");
        entity.setConsigneeAddress("珠海南方软件园");
        entity.setConsigneeProvince("310100");
        entity.setConsigneeCity("310100");
        entity.setConsigneeCounty("310000");
        //
        List<SalesOrderLineEntity> productDetails = new ArrayList<SalesOrderLineEntity>();
        SalesOrderLineEntity salesOrderLineEntity = new SalesOrderLineEntity();
        salesOrderLineEntity.setProductCode("DSL123");
        salesOrderLineEntity.setProductName("汇源肾宝");
        salesOrderLineEntity.setSkuCode("zp5522");
        salesOrderLineEntity.setSkuName("白色");
        salesOrderLineEntity.setQuantity(10);
        salesOrderLineEntity.setPriceSelling(4.0);
        productDetails.add(salesOrderLineEntity);
        //
        entity.setProductDetails(productDetails);
        //
        List<SalesOrderPayEntity> payDetais = new ArrayList<SalesOrderPayEntity>();
        //
        SalesOrderPayEntity salesOrderPayEntity = new SalesOrderPayEntity();
        salesOrderPayEntity.setTenderCode("01");
        salesOrderPayEntity.setTenderName("微信支付");
        salesOrderPayEntity.setPayTime(DateUtil.date2String(new Date(), "yyyy-MM-dd'T'HH:mm:ss"));
        salesOrderPayEntity.setAmount(0.01);
        payDetais.add(salesOrderPayEntity);
        //
        entity.setPayDetais(payDetais);
        entity.setInvoiceDetails(new ArrayList<SalesOrderInvoiceEntity>());
        entity.setNeedInvoice(true);
        entity.setBuyerMemo("这是我的备注");
        impl.addSalesOrderOffline(entity);
    }
}
