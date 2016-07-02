package com.qcloud.component.pay.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.pay.model.PayRequest;
import com.qcloud.component.pay.model.query.PayRequestQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface PayRequestDao extends ISimpleDao<PayRequest, Long> {

    public boolean add(PayRequest payRequest);

    public PayRequest get(Long id);

    public boolean delete(Long id);

    public boolean update(PayRequest payRequest);

    public List<PayRequest> list(List<Long> idList);

    public Map<Long, PayRequest> map(Set<Long> idSet);

    public Page<PayRequest> page(PayRequestQuery query, int start, int size);

    public List<PayRequest> listAll();

    PayRequest getByObj(String module, Long objId, String type, String client);

    PayRequest getByAttach(String module, String attach, String type, String client);
}
