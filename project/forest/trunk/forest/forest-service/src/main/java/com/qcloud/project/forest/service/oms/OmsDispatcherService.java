package com.qcloud.project.forest.service.oms;

import com.qcloud.project.forest.model.oms.AbstractForm;

public interface OmsDispatcherService {

    /**
     * OMS发货
     */
    public boolean deliverOrder(String name);
}
