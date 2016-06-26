package com.qcloud.component.goods.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.MerchandiseDealRecord;
import com.qcloud.component.goods.model.query.MerchandiseDealRecordQuery;

public interface MerchandiseDealRecordService {

    public boolean add(MerchandiseDealRecord merchandiseDealRecord);

    public MerchandiseDealRecord get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseDealRecord merchandiseDealRecord);

    public Page<MerchandiseDealRecord> page(MerchandiseDealRecordQuery query, int start, int count);

    public List<MerchandiseDealRecord> listAll();

    List<MerchandiseDealRecord> listByMerchandise(Long merchandiseId, int start, int count);
}
