package com.qcloud.component.orderform.web.vo.personal;

import java.util.ArrayList;
import java.util.List;

public class OrderMVO extends AbstractOrderVO {

    List<OrderMerchantVO> merchantList = new ArrayList<OrderMerchantVO>();

    public List<OrderMerchantVO> getMerchantList() {

        return merchantList;
    }

    public void setMerchantList(List<OrderMerchantVO> merchantList) {

        this.merchantList = merchantList;
    }
}
