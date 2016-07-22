package com.qcloud.project.forest.web.outside.eximpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void request(String method) {

        standard.handle(method);
    }
}
