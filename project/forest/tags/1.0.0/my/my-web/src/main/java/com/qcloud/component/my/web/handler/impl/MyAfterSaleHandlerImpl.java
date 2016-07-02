package com.qcloud.component.my.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.my.AfterSaleType;
import com.qcloud.component.my.model.MyAfterSale;
import com.qcloud.component.my.web.handler.MyAfterSaleHandler;
import com.qcloud.component.my.web.vo.MyAfterSaleMerchandiseVO;
import com.qcloud.component.my.web.vo.MyAfterSaleVO;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.component.orderform.QAfterSaleDetail;
import com.qcloud.component.orderform.QAfterSaleItem;
import com.qcloud.component.orderform.QAfterSaleOrder;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.util.AssertUtil;

@Component
public class MyAfterSaleHandlerImpl implements MyAfterSaleHandler {

    @Autowired
    private OrderformClient    orderformClient;

    @Autowired
    private SellercenterClient sellercenterClient;

    @Autowired
    private FileSDKClient      fileSDKClient;

    @Override
    public List<MyAfterSaleVO> toVOList(List<MyAfterSale> list) {

        List<MyAfterSaleVO> voList = new ArrayList<MyAfterSaleVO>();
        for (MyAfterSale myAfterSale : list) {
            voList.add(toVO(myAfterSale));
        }
        return voList;
    }

    @Override
    public MyAfterSaleVO toVO(MyAfterSale myAfterSale) {

        AfterSaleType afterSaleType = AfterSaleType.get(myAfterSale.getType());
        QAfterSaleOrder afterSaleOrder = orderformClient.getAfterSaleOrder(myAfterSale.getAfterSaleId(), afterSaleType);
        AssertUtil.assertNotNull(afterSaleOrder, "售后单不存在:" + myAfterSale.getAfterSaleId() + " " + myAfterSale.getType());
        MyAfterSaleVO myAfterSaleVO = new MyAfterSaleVO();
        myAfterSaleVO.setAfterSaleId(myAfterSale.getAfterSaleId());
        myAfterSaleVO.setCash(afterSaleOrder.getSum());
        myAfterSaleVO.setMerchantId(afterSaleOrder.getMerchantId());
        QMerchant merchant = sellercenterClient.getMerchant(afterSaleOrder.getMerchantId());
        myAfterSaleVO.setMerchantName(merchant.getName());
        myAfterSaleVO.setMerchantImage(fileSDKClient.getFileServerUrl() + merchant.getImage());
        myAfterSaleVO.setType(afterSaleOrder.getAfterSaleType().getKey());
        List<MyAfterSaleMerchandiseVO> voList = new ArrayList<MyAfterSaleMerchandiseVO>();
        int number = 0;
        if (AfterSaleType.EXCHANGE.equals(afterSaleType)) {
            List<QAfterSaleDetail> list = afterSaleOrder.listItem();
            for (QAfterSaleDetail detail : list) {
                MyAfterSaleMerchandiseVO myAfterSaleMerchandiseVO = new MyAfterSaleMerchandiseVO();
                myAfterSaleMerchandiseVO.setDiscount(detail.getOrderItemDetail().getOrderItem().getDiscount());
                myAfterSaleMerchandiseVO.setImage(fileSDKClient.getFileServerUrl() + detail.getOrderItemDetail().getImage());
                myAfterSaleMerchandiseVO.setName(detail.getOrderItemDetail().getName());
                myAfterSaleMerchandiseVO.setNumber(detail.getNumber());
                myAfterSaleMerchandiseVO.setSpecifications(detail.getOrderItemDetail().getSpecifications());
                voList.add(myAfterSaleMerchandiseVO);
                number += detail.getNumber();
            }
        } else {
            List<QAfterSaleItem> list = afterSaleOrder.listItem();
            for (QAfterSaleItem item : list) {
                MyAfterSaleMerchandiseVO myAfterSaleMerchandiseVO = new MyAfterSaleMerchandiseVO();
                myAfterSaleMerchandiseVO.setDiscount(item.getOrderItem().getDiscount());
                myAfterSaleMerchandiseVO.setImage(fileSDKClient.getFileServerUrl() + item.getOrderItem().getImage());
                myAfterSaleMerchandiseVO.setName(item.getOrderItem().getName());
                myAfterSaleMerchandiseVO.setNumber(item.getNumber());
                myAfterSaleMerchandiseVO.setSpecifications(item.getOrderItem().getSpecifications());
                voList.add(myAfterSaleMerchandiseVO);
                number += item.getNumber();
            }
        }
        myAfterSaleVO.setList(voList);
        myAfterSaleVO.setNumber(number);
        myAfterSaleVO.setState(afterSaleOrder.getUserState());
        myAfterSaleVO.setStateStr(afterSaleOrder.getUserStateStr());
        myAfterSaleVO.setAfterSaleOrderNumber(afterSaleOrder.getAfterSaleOrderNumber());
        return myAfterSaleVO;
    }
    //
    // // private int getState(QAfterSaleOrder afterSaleOrder) {
    // //
    // // int state = -1;
    // // if (AfterSaleType.REFUND.equals(afterSaleOrder.getAfterSaleType())) {
    // // switch (afterSaleOrder.getState()) {
    // // case 710:
    // // state = 1;
    // // break;
    // // case 720:
    // // state = 2;
    // // break;
    // // case 730:
    // // state = 3;
    // // break;
    // // case 740:
    // // state = 4;
    // // break;
    // // case 750:
    // // state = 5;
    // // break;
    // // case 760:
    // // state = 6;
    // // break;
    // // case 770:
    // // state = 7;
    // // break;
    // // case 780:
    // // state = 8;
    // // break;
    // // default:
    // // break;
    // // }
    // // } else if (AfterSaleType.RETURN.equals(afterSaleOrder.getAfterSaleType())) {
    // // switch (afterSaleOrder.getState()) {
    // // case 310:
    // // state = 11;
    // // break;
    // // case 320:
    // // state = 12;
    // // break;
    // // case 330:
    // // state = 13;
    // // break;
    // // case 340:
    // // state = 14;
    // // break;
    // // case 350:
    // // state = 15;
    // // break;
    // // case 360:
    // // state = 16;
    // // break;
    // // case 370:
    // // state = 17;
    // // break;
    // // case 380:
    // // state = 18;
    // // break;
    // // case 390:
    // // state = 19;
    // // break;
    // // case 400:
    // // state = 20;
    // // break;
    // // default:
    // // break;
    // // }
    // // } else if (AfterSaleType.EXCHANGE.equals(afterSaleOrder.getAfterSaleType())) {
    // // switch (afterSaleOrder.getState()) {
    // // case 510:
    // // state = 21;
    // // break;
    // // case 520:
    // // state = 22;
    // // break;
    // // case 530:
    // // state = 23;
    // // break;
    // // case 540:
    // // state = 24;
    // // break;
    // // case 550:
    // // state = 25;
    // // break;
    // // case 560:
    // // state = 26;
    // // break;
    // // case 570:
    // // state = 27;
    // // break;
    // // case 580:
    // // state = 28;
    // // break;
    // // case 590:
    // // state = 29;
    // // break;
    // // case 600:
    // // state = 30;
    // // break;
    // // default:
    // // break;
    // // }
    // // }
    // // return state;
    // // }
    //
    // private String getStateStr(int state) {
    //
    // String str = "";
    // switch (state) {
    // case 1:
    // str = "申请退款";
    // break;
    // case 2:
    // str = "不同意退款";
    // break;
    // case 3:
    // str = "不同意退款";
    // break;
    // case 4:
    // str = "同意退款";
    // break;
    // case 5:
    // str = "商家退款";
    // break;
    // case 6:
    // str = "退款成功";
    // break;
    // case 7:
    // str = "退款成功";
    // break;
    // case 8:
    // str = "重新申请退款";
    // break;
    // case 11:
    // str = "申请退货";
    // break;
    // case 12:
    // str = "不同意退货";
    // break;
    // case 13:
    // str = "不同意退货";
    // break;
    // case 14:
    // str = "同意退货";
    // break;
    // case 15:
    // str = "用户发货";
    // break;
    // case 16:
    // str = "商家签收";
    // break;
    // case 17:
    // str = "商家退款";
    // break;
    // case 18:
    // str = "退货成功";
    // break;
    // case 19:
    // str = "退货成功";
    // break;
    // case 20:
    // str = "重新申请退货";
    // break;
    // case 21:
    // str = "申请换货";
    // break;
    // case 22:
    // str = "不同意换货";
    // break;
    // case 23:
    // str = "不同意换货";
    // break;
    // case 24:
    // str = "同意换货";
    // break;
    // case 25:
    // str = "用户发货";
    // break;
    // case 26:
    // str = "商家签收";
    // break;
    // case 27:
    // str = "商家发货";
    // break;
    // case 28:
    // str = "换货成功";
    // break;
    // case 29:
    // str = "换货成功";
    // break;
    // case 30:
    // str = "重新申请换货";
    // break;
    // default:
    // break;
    // }
    // return str;
    // }
}
