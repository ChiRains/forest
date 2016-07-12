package com.qcloud.project.forest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ExpressQueryHistoryDao;
import com.qcloud.project.forest.model.ExpressQueryHistory;
import com.qcloud.project.forest.model.query.ExpressQueryHistoryQuery;
import com.qcloud.project.forest.service.ExpressQueryHistoryService;

@Service
public class ExpressQueryHistoryServiceImpl implements ExpressQueryHistoryService {

    @Autowired
    private ExpressQueryHistoryDao expressQueryHistoryDao;

    @Autowired
    private AutoIdGenerator        autoIdGenerator;

    private static final String    ID_KEY = "forest_express_query_history";

    @Override
    public boolean add(ExpressQueryHistory expressQueryHistory) {

        long id = autoIdGenerator.get(ID_KEY);
        expressQueryHistory.setId(id);
        return expressQueryHistoryDao.add(expressQueryHistory);
    }

    @Override
    public ExpressQueryHistory get(Long id) {

        return expressQueryHistoryDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return expressQueryHistoryDao.delete(id);
    }

    @Override
    public boolean update(ExpressQueryHistory expressQueryHistory) {

        return expressQueryHistoryDao.update(expressQueryHistory);
    }

    public List<ExpressQueryHistory> listByUserId(String userId) {

        return expressQueryHistoryDao.listByUserId(userId);
    }

    @Override
    public Page<ExpressQueryHistory> page(ExpressQueryHistoryQuery query, int start, int count) {

        return expressQueryHistoryDao.page(query, start, count);
    }

    public List<ExpressQueryHistory> listAll() {

        return expressQueryHistoryDao.listAll();
    }

    @Override
    public ExpressQueryHistory getByUserIdAndExpressNum(ExpressQueryHistoryQuery query) {

        return expressQueryHistoryDao.getByUserIdAndExpressNum(query);
    }
}
