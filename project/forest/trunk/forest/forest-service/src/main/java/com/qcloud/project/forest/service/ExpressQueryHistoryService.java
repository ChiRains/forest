package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.ExpressQueryHistory;
import com.qcloud.project.forest.model.query.ExpressQueryHistoryQuery;

public interface ExpressQueryHistoryService {

    public boolean add(ExpressQueryHistory expressQueryHistory);

    public ExpressQueryHistory get(Long id);

    public boolean delete(Long id);

    public boolean update(ExpressQueryHistory expressQueryHistory);

    public List<ExpressQueryHistory> listByUserId(String userId);

    public Page<ExpressQueryHistory> page(ExpressQueryHistoryQuery query, int start, int count);

    public List<ExpressQueryHistory> listAll();

    public ExpressQueryHistory getByUserIdAndExpressNum(ExpressQueryHistoryQuery query);
}
