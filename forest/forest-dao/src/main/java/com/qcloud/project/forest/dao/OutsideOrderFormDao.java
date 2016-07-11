package com.qcloud.project.forest.dao;

import com.qcloud.project.forest.model.outside.entity.AbstractResult;
import com.qcloud.project.forest.model.outside.entity.SalesOrderOfflineEntity;

public interface OutsideOrderFormDao {

    public AbstractResult addSalesOrderOffline(SalesOrderOfflineEntity entity);
}
