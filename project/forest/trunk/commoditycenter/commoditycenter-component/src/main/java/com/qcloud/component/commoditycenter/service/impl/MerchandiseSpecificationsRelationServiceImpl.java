package com.qcloud.component.commoditycenter.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.commoditycenter.dao.MerchandiseSpecificationsRelationDao;
import com.qcloud.component.commoditycenter.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.commoditycenter.service.MerchandiseSpecificationsRelationService;
import com.qcloud.component.commoditycenter.model.query.MerchandiseSpecificationsRelationQuery;

@Service
public class MerchandiseSpecificationsRelationServiceImpl implements MerchandiseSpecificationsRelationService {

    @Autowired
    private MerchandiseSpecificationsRelationDao merchandiseSpecificationsRelationDao;

    @Autowired
    private AutoIdGenerator                      autoIdGenerator;

    private static final String                  ID_KEY = "commoditycenter_merchandise_specifications_relation";

    @Override
    public boolean add(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation) {

        long id = autoIdGenerator.get(ID_KEY);
        merchandiseSpecificationsRelation.setId(id);
        return merchandiseSpecificationsRelationDao.add(merchandiseSpecificationsRelation);
    }

    @Override
    public MerchandiseSpecificationsRelation get(Long id) {

        return merchandiseSpecificationsRelationDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseSpecificationsRelationDao.delete(id);
    }

    @Override
    public boolean update(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation) {

        return merchandiseSpecificationsRelationDao.update(merchandiseSpecificationsRelation);
    }

    @Override
    public Page<MerchandiseSpecificationsRelation> page(MerchandiseSpecificationsRelationQuery query, int start, int count) {

        return merchandiseSpecificationsRelationDao.page(query, start, count);
    }

    public List<MerchandiseSpecificationsRelation> listAll() {

        return merchandiseSpecificationsRelationDao.listAll();
    }

    @Override
    public boolean updateSpecByMap(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation) {

        return merchandiseSpecificationsRelationDao.updateSpecByMap(merchandiseSpecificationsRelation);
    }

    @Override
    public List<MerchandiseSpecificationsRelation> listByMerchandiseId(Long merchandiseId) {

        return merchandiseSpecificationsRelationDao.listByMerchandiseId(merchandiseId);
    }

    @Override
    public boolean deleteByMerchandiseId(Long merchandiseIds) {

        return merchandiseSpecificationsRelationDao.deleteByMerchandiseId(merchandiseIds);
    }

    @Override
    public List<MerchandiseSpecificationsRelation> listByMap(Long merchandiseId, Long attributeId) {

        return merchandiseSpecificationsRelationDao.listByMap(merchandiseId, attributeId);
    }
}
