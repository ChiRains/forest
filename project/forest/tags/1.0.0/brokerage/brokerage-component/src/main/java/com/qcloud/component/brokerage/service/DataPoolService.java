package com.qcloud.component.brokerage.service;

import java.util.List;
import com.qcloud.component.brokerage.model.DataPool;
import com.qcloud.component.brokerage.model.FormulaSqlResult;
import com.qcloud.component.brokerage.model.query.DataPoolQuery;
import com.qcloud.pirates.data.Page;

public interface DataPoolService {

    public boolean add(DataPool dataPool);

    public DataPool get(Long id);

    public boolean delete(Long id);

    public boolean update(DataPool dataPool);

    public Page<DataPool> page(DataPoolQuery query, int start, int count);

    public List<DataPool> listAll();

    List<FormulaSqlResult> query(String sql);
}
