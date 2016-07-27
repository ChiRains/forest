package com.qcloud.project.forest.web.outside.eximpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.project.forest.model.oms.QueryForm;

@Component
public class OmsDispatcherClient {

    @Autowired
    private OmsStandard standard;

    public void setStandard(OmsStandard standard) {

        this.standard = standard;
    }

    /**
     * 跳转的方法
     * @param method
     */
    public void request(QueryForm queryForm) {

        standard.handle(queryForm);
    }
}
