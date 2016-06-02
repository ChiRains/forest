package com.qcloud.component.commoditycenter.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.commoditycenter.dao.MerchandiseMarketingDao;
import com.qcloud.component.commoditycenter.model.MerchandiseMarketing;
import com.qcloud.component.commoditycenter.model.query.MerchandiseMarketingQuery;
import com.qcloud.component.commoditycenter.service.MerchandiseMarketingService;
import com.qcloud.pirates.data.Page;

@Service
public class MerchandiseMarketingServiceImpl implements MerchandiseMarketingService {

    @Autowired
    private MerchandiseMarketingDao merchandiseMarketingDao;

    @Autowired
    private AutoIdGenerator         autoIdGenerator;

    private static final String     ID_KEY = "commoditycenter_merchandise_marketing";

    @Override
    public boolean add(MerchandiseMarketing merchandiseMarketing) {

        long id = autoIdGenerator.get(ID_KEY);
        merchandiseMarketing.setId(id);
        merchandiseMarketing.setUpdateTime(new Date());
        return merchandiseMarketingDao.add(merchandiseMarketing);
    }

    @Override
    public MerchandiseMarketing get(Long id) {

        return merchandiseMarketingDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseMarketingDao.delete(id);
    }

    @Override
    public boolean update(MerchandiseMarketing merchandiseMarketing) {

        MerchandiseMarketing mm = get(merchandiseMarketing.getId());
        Date d = mm.getUpdateTime();
        merchandiseMarketing.setUpdateTime(new Date());
        return merchandiseMarketingDao.update(merchandiseMarketing, d);
    }

    @Override
    public Page<MerchandiseMarketing> page(MerchandiseMarketingQuery query, int start, int count) {

        return merchandiseMarketingDao.page(query, start, count);
    }

    public List<MerchandiseMarketing> listAll() {

        return merchandiseMarketingDao.listAll();
    }

    @Override
    public MerchandiseMarketing getByUnifiedMerchandise(Long unifiedMerchandiseId) {

        return merchandiseMarketingDao.getByUnifiedMerchandise(unifiedMerchandiseId);
    }

    @Override
    public boolean lockStock(long unifiedMerchandiseId, int stock) {

        MerchandiseMarketing merchandiseMarketing = getByUnifiedMerchandise(unifiedMerchandiseId);
        Date d = merchandiseMarketing.getUpdateTime();
        merchandiseMarketing.setStock(merchandiseMarketing.getStock() + stock);
        merchandiseMarketing.setUpdateTime(new Date());
        return merchandiseMarketingDao.update(merchandiseMarketing, d);
    }

    @Override
    public boolean setEnable(Long id, int enable) {

        return merchandiseMarketingDao.setEnable(id, enable);
    }

    @Override
    public List<MerchandiseMarketing> list(int sence, String keywords, double discountRange, int start, int count) {

        return merchandiseMarketingDao.list(sence, discountRange, start, count);
    }
}
