package com.qcloud.component.goods.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.goods.model.MerchandiseBrowsingHistory;
import com.qcloud.component.goods.model.query.MerchandiseBrowsingHistoryQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface MerchandiseBrowsingHistoryDao extends ISimpleDao<MerchandiseBrowsingHistory, Long> {

    public boolean add(MerchandiseBrowsingHistory merchandiseBrowsingHistory);

    public MerchandiseBrowsingHistory get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseBrowsingHistory merchandiseBrowsingHistory);

    public List<MerchandiseBrowsingHistory> list(List<Long> idList);

    public Map<Long, MerchandiseBrowsingHistory> map(Set<Long> idSet);

    public Page<MerchandiseBrowsingHistory> page(MerchandiseBrowsingHistoryQuery query, int start, int size);

    public List<MerchandiseBrowsingHistory> listAll();

    List<MerchandiseBrowsingHistory> listByUser(long userId, int start, int count);

    public int countByUserId(long userId);
}
