package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.ShareGift;
import com.qcloud.project.forest.model.query.ShareGiftQuery;

public interface ShareGiftDao extends ISimpleDao<ShareGift, Long> {

    public boolean add(ShareGift shareGift);

    public ShareGift get(Long id);

    public boolean delete(Long id);

    public boolean update(ShareGift shareGift);

    public List<ShareGift> list(List<Long> idList);

    public Map<Long, ShareGift> map(Set<Long> idSet);

    public Page<ShareGift> page(ShareGiftQuery query, int start, int size);

    public List<ShareGift> listAll();

    public ShareGift getByUserId(Long userId);
}
