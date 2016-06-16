package com.qcloud.component.my.web.handler;

import java.util.List;
import com.qcloud.component.my.model.MyOrderForm;
import com.qcloud.component.my.web.vo.MyOrderFormListVO;
import com.qcloud.component.my.web.vo.MyOrderFormMerchandiseVO;
import com.qcloud.component.my.web.vo.MyOrderFormMerchantVO;
import com.qcloud.component.my.web.vo.MyOrderFormSimpleVO;
import com.qcloud.component.my.web.vo.admin.AdminMyOrderFormVO;

public interface MyOrderFormHandler {

    // List<MyOrderFormVO> toVOList(List<MyOrderForm> list);
    //
    // MyOrderFormVO toVO(MyOrderForm myOrderForm);
    //
    List<MyOrderFormSimpleVO> toVOList4Simple(List<MyOrderForm> list);

    MyOrderFormSimpleVO toVO4Simple(MyOrderForm myOrderForm);

    List<MyOrderFormMerchantVO> toVOList4Merchant(List<MyOrderForm> list);

    List<MyOrderFormMerchandiseVO> toVOList4Merchandise(List<MyOrderForm> list);

    List<MyOrderFormListVO> toVOList4List(List<MyOrderForm> list);

    // MyOrderFormMerchantVO toVO4Merchant(MyOrderForm myOrderForm);
    //
    // MyOrderFormMerchandiseVO toVO4Merchandise(MyOrderForm myOrderForm);
    List<AdminMyOrderFormVO> toVOList4Admin(List<MyOrderForm> list);

    AdminMyOrderFormVO toVO4Admin(MyOrderForm myOrderForm);
}
