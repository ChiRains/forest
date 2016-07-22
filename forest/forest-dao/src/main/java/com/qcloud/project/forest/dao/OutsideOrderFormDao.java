package com.qcloud.project.forest.dao;

import com.qcloud.project.forest.model.oms.AbstractResult;
import com.qcloud.project.forest.model.oms.SalesOrderOfflineEntity;

public interface OutsideOrderFormDao {

    public AbstractResult addSalesOrderOffline(SalesOrderOfflineEntity entity);
}
