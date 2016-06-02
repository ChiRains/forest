package com.qcloud.component.brokerage.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.brokerage.model.UpgradeGift;
import com.qcloud.component.brokerage.model.query.UpgradeGiftQuery;

public interface UpgradeGiftDao extends ISimpleDao<UpgradeGift, Long> {

    public boolean add(UpgradeGift upgradeGift);

    public UpgradeGift get(Long id);

    public boolean delete(Long id);

    public boolean update(UpgradeGift upgradeGift);

    public List<UpgradeGift> list(List<Long> idList);

    public Map<Long, UpgradeGift> map(Set<Long> idSet);

    public Page<UpgradeGift> page(UpgradeGiftQuery query, int start, int size);

    public List<UpgradeGift> listAll();

    public List<UpgradeGift> listCanGift(long gradeId);
}
