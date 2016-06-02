package com.qcloud.component.brokerage.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.model.AllocationScheme;
import com.qcloud.component.brokerage.model.query.AllocationSchemeQuery;

public interface AllocationSchemeService {

    public boolean add(AllocationScheme allocationScheme);

    public AllocationScheme get(Long id);

    public boolean delete(Long id);

    public boolean update(AllocationScheme allocationScheme);

    public Page<AllocationScheme> page(AllocationSchemeQuery query, int start, int count);

    public List<AllocationScheme> listAll();

    List<AllocationScheme> listByFormula(long formulaId);
}
