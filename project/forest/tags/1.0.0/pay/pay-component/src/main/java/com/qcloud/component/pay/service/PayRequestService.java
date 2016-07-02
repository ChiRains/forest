package com.qcloud.component.pay.service;

import java.util.List;
import com.qcloud.component.pay.model.PayRequest;
import com.qcloud.component.pay.model.key.TypeEnum.PayClientType;
import com.qcloud.component.pay.model.key.TypeEnum.PayMethodType;
import com.qcloud.component.pay.model.query.PayRequestQuery;
import com.qcloud.pirates.data.Page;

public interface PayRequestService {

    public boolean add(PayRequest payRequest);

    public PayRequest get(Long id);

    PayRequest getByObj(String module, Long objId, PayMethodType type, PayClientType client);

    PayRequest getByAttach(String module, String attach, PayMethodType type, PayClientType client);

    public boolean delete(Long id);

    public boolean update(PayRequest payRequest);

    public Page<PayRequest> page(PayRequestQuery query, int start, int count);

    public List<PayRequest> listAll();
}
