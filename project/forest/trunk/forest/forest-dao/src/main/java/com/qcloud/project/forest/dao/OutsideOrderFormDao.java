package com.qcloud.project.forest.dao;

import com.qcloud.project.forest.outside.entity.AbstractResult;
import com.qcloud.project.forest.outside.entity.SalesOrderOfflineEntity;

public interface OutsideOrderFormDao {

    public AbstractResult addSalesOrderOffline(SalesOrderOfflineEntity entity);
}
