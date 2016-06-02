package com.qcloud.component.brokerage.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.brokerage.model.AllocationScheme;
import com.qcloud.component.brokerage.model.query.AllocationSchemeQuery;

public interface AllocationSchemeDao extends ISimpleDao<AllocationScheme, Long> {

    public boolean add(AllocationScheme allocationScheme);

    public AllocationScheme get(Long id);

    public boolean delete(Long id);

    public boolean update(AllocationScheme allocationScheme);

    public List<AllocationScheme> list(List<Long> idList);

    public Map<Long, AllocationScheme> map(Set<Long> idSet);

    public Page<AllocationScheme> page(AllocationSchemeQuery query, int start, int size);

    public List<AllocationScheme> listAll();

    List<AllocationScheme> listByFormula(long formulaId);
}
