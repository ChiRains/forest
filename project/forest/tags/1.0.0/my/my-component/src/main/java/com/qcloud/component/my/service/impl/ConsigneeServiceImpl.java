package com.qcloud.component.my.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.dao.ConsigneeDao;
import com.qcloud.component.my.model.Consignee;
import com.qcloud.component.my.model.key.TypeEnum.ConsigneeType;
import com.qcloud.component.my.model.query.ConsigneeQuery;
import com.qcloud.component.my.service.ConsigneeService;
import com.qcloud.pirates.data.Page;

@Service
public class ConsigneeServiceImpl implements ConsigneeService {

    @Autowired
    private ConsigneeDao        consigneeDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "personalcenter_consignee";

    @Override
    public boolean add(Consignee consignee) {

        boolean acquiesce = consignee.getAcquiesce() == ConsigneeType.DEFAULT.getKey();
        Consignee d = getDefault(consignee.getUserId());
        if (d == null) {
            consignee.setAcquiesce(ConsigneeType.DEFAULT.getKey());
        } else {
            consignee.setAcquiesce(ConsigneeType.NOTDEFAULT.getKey());
        }
        long id = autoIdGenerator.get(ID_KEY);
        consignee.setId(id);
        consigneeDao.add(consignee);
        if (acquiesce) {
            setDefault(id);
        }
        return true;
    }

    @Override
    public Consignee get(Long id) {

        return consigneeDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return consigneeDao.delete(id);
    }

    @Override
    public boolean update(Consignee consignee) {

        consigneeDao.update(consignee);
        if (consignee.getAcquiesce() == ConsigneeType.DEFAULT.getKey()) {
            setDefault(consignee.getId());
        }
        return true;
    }

    @Override
    public Page<Consignee> page(ConsigneeQuery query, int start, int count) {

        return consigneeDao.page(query, start, count);
    }

    public List<Consignee> listAll() {

        return consigneeDao.listAll();
    }

    @Override
    public List<Consignee> listByUser(Long userId) {

        return consigneeDao.listByUser(userId);
    }

    @Override
    public Consignee getDefault(Long userId) {

        Consignee consignee = null;
        List<Consignee> list = listByUser(userId);
        for (Consignee c : list) {
            if (c.getAcquiesce() == ConsigneeType.DEFAULT.getKey()) {
                consignee = c;
                break;
            }
        }
        if (consignee == null && list.size() > 0) {
            consignee = list.get(0);
        }
        return consignee;
    }

    @Transactional
    public boolean setDefault(Long id) {

        Consignee consignee = get(id);
        List<Consignee> list = listByUser(consignee.getUserId());
        for (Consignee c : list) {
            if (c.getAcquiesce() == ConsigneeType.DEFAULT.getKey() && c.getId() != consignee.getId()) {
                c.setAcquiesce(ConsigneeType.NOTDEFAULT.getKey());
                update(c);
            }
        }
        if (ConsigneeType.DEFAULT.getKey() == consignee.getAcquiesce()) {
            return true;
        }
        consignee.setAcquiesce(ConsigneeType.DEFAULT.getKey());
        update(consignee);
        return true;
    }
}
