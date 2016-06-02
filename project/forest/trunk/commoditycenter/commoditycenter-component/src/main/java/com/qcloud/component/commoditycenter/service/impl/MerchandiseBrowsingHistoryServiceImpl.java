package com.qcloud.component.commoditycenter.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.commoditycenter.dao.MerchandiseBrowsingHistoryDao;
import com.qcloud.component.commoditycenter.model.MerchandiseBrowsingHistory;
import com.qcloud.component.commoditycenter.service.MerchandiseBrowsingHistoryService;
import com.qcloud.component.commoditycenter.model.query.MerchandiseBrowsingHistoryQuery;

@Service
public class MerchandiseBrowsingHistoryServiceImpl implements MerchandiseBrowsingHistoryService {

    @Autowired
    private MerchandiseBrowsingHistoryDao merchandiseBrowsingHistoryDao;

    @Autowired
    private AutoIdGenerator               autoIdGenerator;

    private static final String           ID_KEY = "commoditycenter_merchandise_browsing_history";

    @Override
    public boolean add(MerchandiseBrowsingHistory merchandiseBrowsingHistory) {

        long id = autoIdGenerator.get(ID_KEY);
        merchandiseBrowsingHistory.setId(id);
        return merchandiseBrowsingHistoryDao.add(merchandiseBrowsingHistory);
    }

    @Override
    public MerchandiseBrowsingHistory get(Long id) {

        return merchandiseBrowsingHistoryDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseBrowsingHistoryDao.delete(id);
    }

    @Override
    public boolean update(MerchandiseBrowsingHistory merchandiseBrowsingHistory) {

        return merchandiseBrowsingHistoryDao.update(merchandiseBrowsingHistory);
    }

    @Override
    public Page<MerchandiseBrowsingHistory> page(MerchandiseBrowsingHistoryQuery query, int start, int count) {

        return merchandiseBrowsingHistoryDao.page(query, start, count);
    }

    public List<MerchandiseBrowsingHistory> listAll() {

        return merchandiseBrowsingHistoryDao.listAll();
    }

    @Override
    public List<MerchandiseBrowsingHistory> listByUser(long userId, int start, int count) {

        return merchandiseBrowsingHistoryDao.listByUser(userId, start, count);
    }
}
