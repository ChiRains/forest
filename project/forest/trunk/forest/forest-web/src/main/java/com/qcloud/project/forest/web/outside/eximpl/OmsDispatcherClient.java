package com.qcloud.project.forest.web.outside.eximpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.project.forest.model.oms.QueryForm;

@Component
public class OmsDispatcherClient {

    @Autowired
    private OmsCanonical standard;

    public void setStandard(OmsCanonical standard) {

        this.standard = standard;
    }

    /**
     * 跳转的方法
     * @param method
     */
    public String requestToXml(QueryForm queryForm) {

        return standard.handleToXml(queryForm);
    }
}
