package com.qcloud.component.pay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.pay.dao.PayRequestDao;
import com.qcloud.component.pay.model.PayRequest;
import com.qcloud.component.pay.service.PayRequestService;
import com.qcloud.component.pay.model.key.TypeEnum.PayClientType;
import com.qcloud.component.pay.model.key.TypeEnum.PayMethodType;
import com.qcloud.component.pay.model.query.PayRequestQuery;

@Service
public class PayRequestServiceImpl implements PayRequestService {

    @Autowired
    private PayRequestDao       payRequestDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "pay_pay_request";

    @Override
    public boolean add(PayRequest payRequest) {

        long id = autoIdGenerator.get(ID_KEY);
        payRequest.setId(id);
        return payRequestDao.add(payRequest);
    }

    @Override
    public PayRequest get(Long id) {

        return payRequestDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return payRequestDao.delete(id);
    }

    @Override
    public boolean update(PayRequest payRequest) {

        return payRequestDao.update(payRequest);
    }

    @Override
    public Page<PayRequest> page(PayRequestQuery query, int start, int count) {

        return payRequestDao.page(query, start, count);
    }

    public List<PayRequest> listAll() {

        return payRequestDao.listAll();
    }

    @Override
    public PayRequest getByObj(String module, Long objId, PayMethodType type, PayClientType client) {

        return payRequestDao.getByObj(module, objId, type.getKey(), client.getKey());
    }

    @Override
    public PayRequest getByAttach(String module, String attach, PayMethodType type, PayClientType client) {

        return payRequestDao.getByAttach(module, attach, type.getKey(), client.getKey());
    }
}
