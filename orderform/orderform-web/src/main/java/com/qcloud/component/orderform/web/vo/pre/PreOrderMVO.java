package com.qcloud.component.orderform.web.vo.pre;

import java.util.ArrayList;
import java.util.List;

public class PreOrderMVO extends AbstractPrepareOrderVO {

    private List<PreMerchantVO> preMerchantList = new ArrayList<PreMerchantVO>();

    public List<PreMerchantVO> getPreMerchantList() {

        return preMerchantList;
    }

    public void setPreMerchantList(List<PreMerchantVO> preMerchantList) {

        this.preMerchantList = preMerchantList;
    }

 
}
