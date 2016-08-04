package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.ShareGiftUser;
import com.qcloud.project.forest.model.query.ShareGiftUserQuery;

public interface ShareGiftUserDao extends ISimpleDao<ShareGiftUser, Long> {

    public boolean add(ShareGiftUser shareGiftUser);

    public ShareGiftUser get(Long id);

    public boolean delete(Long id);

    public boolean update(ShareGiftUser shareGiftUser);

    public List<ShareGiftUser> list(List<Long> idList);

    public Map<Long, ShareGiftUser> map(Set<Long> idSet);

    public Page<ShareGiftUser> page(ShareGiftUserQuery query, int start, int size);

    public List<ShareGiftUser> listAll();

    public List<ShareGiftUser> listByCode(String code);

    public int countByCode(String code);

    public int countCouponByCode(String code);
}
