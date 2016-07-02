package com.qcloud.component.commoditycenter.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.commoditycenter.model.MerchandiseBrowsingHistory;
import com.qcloud.component.commoditycenter.model.query.MerchandiseBrowsingHistoryQuery;

public interface MerchandiseBrowsingHistoryService {

    public boolean add(MerchandiseBrowsingHistory merchandiseBrowsingHistory);

    public MerchandiseBrowsingHistory get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseBrowsingHistory merchandiseBrowsingHistory);

    public Page<MerchandiseBrowsingHistory> page(MerchandiseBrowsingHistoryQuery query, int start, int count);

    public List<MerchandiseBrowsingHistory> listAll();

    List<MerchandiseBrowsingHistory> listByUser(long userId, int start, int count);
}
