package com.qcloud.project.forest.web.outside.eximpl;

import com.qcloud.project.forest.model.oms.QueryForm;

public interface OmsStandard {

    /**
     * 方法分发处理
     */
    public void handle(QueryForm queryForm);
}
