package com.qcloud.component.brokerage.service;

import java.util.List;
import com.qcloud.component.brokerage.entity.UpgradeGiftEntity;
import com.qcloud.component.brokerage.model.UpgradeGift;
import com.qcloud.component.brokerage.model.query.UpgradeGiftQuery;
import com.qcloud.pirates.data.Page;

public interface UpgradeGiftService {

    public boolean add(UpgradeGift upgradeGift);

    public UpgradeGift get(Long id);

    public boolean delete(Long id);

    public boolean update(UpgradeGift upgradeGift);

    public Page<UpgradeGift> page(UpgradeGiftQuery query, int start, int count);

    public List<UpgradeGift> listAll();

    public List<UpgradeGiftEntity> listCanGift(long gradeId);
}
