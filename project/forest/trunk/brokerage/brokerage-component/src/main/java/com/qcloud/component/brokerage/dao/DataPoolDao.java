package com.qcloud.component.brokerage.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.brokerage.model.DataPool;
import com.qcloud.component.brokerage.model.FormulaSqlResult;
import com.qcloud.component.brokerage.model.query.DataPoolQuery;

public interface DataPoolDao extends ISimpleDao<DataPool, Long> {

    public boolean add(DataPool dataPool);

    public DataPool get(Long id);

    public boolean delete(Long id);

    public boolean update(DataPool dataPool);

    public List<DataPool> list(List<Long> idList);

    public Map<Long, DataPool> map(Set<Long> idSet);

    public Page<DataPool> page(DataPoolQuery query, int start, int size);

    public List<DataPool> listAll();

    List<FormulaSqlResult> query(String sql);
}
