package com.qcloud.component.commoditycenter.web.form;

import java.util.List;

public class MerchandiseAttributeForm {
    
    Long merchandiseId;
   
    List<MerchandiseAttributeItemForm> list;

    public Long getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(Long merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public List<MerchandiseAttributeItemForm> getList() {
        return list;
    }

    public void setList(List<MerchandiseAttributeItemForm> list) {
        this.list = list;
    }    
}
