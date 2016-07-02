package com.qcloud.component.brokerage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.brokerage.dao.UpgradeGiftDao;
import com.qcloud.component.brokerage.entity.UpgradeGiftEntity;
import com.qcloud.component.brokerage.model.UpgradeGift;
import com.qcloud.component.brokerage.model.query.UpgradeGiftQuery;
import com.qcloud.component.brokerage.service.UpgradeGiftService;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.DateUtil;

@Service
public class UpgradeGiftServiceImpl implements UpgradeGiftService {

    @Autowired
    private UpgradeGiftDao      upgradeGiftDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "brokerage_upgrade_gift";

    @Override
    public boolean add(UpgradeGift upgradeGift) {

        long id = autoIdGenerator.get(ID_KEY);
        upgradeGift.setId(id);
        return upgradeGiftDao.add(upgradeGift);
    }

    @Override
    public UpgradeGift get(Long id) {

        return upgradeGiftDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return upgradeGiftDao.delete(id);
    }

    @Override
    public boolean update(UpgradeGift upgradeGift) {

        return upgradeGiftDao.update(upgradeGift);
    }

    @Override
    public Page<UpgradeGift> page(UpgradeGiftQuery query, int start, int count) {

        return upgradeGiftDao.page(query, start, count);
    }

    public List<UpgradeGift> listAll() {

        return upgradeGiftDao.listAll();
    }

    @Override
    public List<UpgradeGiftEntity> listCanGift(long gradeId) {

        List<UpgradeGift> gifts = upgradeGiftDao.listCanGift(gradeId);
        List<UpgradeGiftEntity> entitys = new ArrayList<UpgradeGiftEntity>();
        Date current = DateUtil.str2Date(DateUtil.date2String(new Date(), "yyyy-MM-dd") + " 00:00:00");
        for (UpgradeGift upgradeGift : gifts) {
            if (current.before(upgradeGift.getLimitTime())) {
                UpgradeGiftEntity entity = new UpgradeGiftEntity();
                entity.setCouponId(upgradeGift.getCouponId());
                entity.setNumber(upgradeGift.getNumber());
                entitys.add(entity);
            }
        }
        return entitys;
    }
}
