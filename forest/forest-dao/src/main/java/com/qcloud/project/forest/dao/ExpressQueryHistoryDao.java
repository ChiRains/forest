package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.ExpressQueryHistory;
import com.qcloud.project.forest.model.query.ExpressQueryHistoryQuery;

public interface ExpressQueryHistoryDao extends ISimpleDao<ExpressQueryHistory, Long> {

    public boolean add(ExpressQueryHistory expressQueryHistory);

    public ExpressQueryHistory get(Long id);

    public boolean delete(Long id);

    public boolean update(ExpressQueryHistory expressQueryHistory);

    public List<ExpressQueryHistory> list(List<Long> idList);

    public Map<Long, ExpressQueryHistory> map(Set<Long> idSet);

    public Page<ExpressQueryHistory> page(ExpressQueryHistoryQuery query, int start, int size);

    public List<ExpressQueryHistory> listAll();

    public List<ExpressQueryHistory> listByUserId(String userId);

    public ExpressQueryHistory getByUserIdAndExpressNum(ExpressQueryHistoryQuery query);
}
