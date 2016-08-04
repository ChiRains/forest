package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.ShareGiftUser;
import com.qcloud.project.forest.model.query.ShareGiftUserQuery;

public interface ShareGiftUserService {

    public boolean add(ShareGiftUser shareGiftUser);

    public ShareGiftUser get(Long id);

    public boolean delete(Long id);

    public boolean update(ShareGiftUser shareGiftUser);

    public Page<ShareGiftUser> page(ShareGiftUserQuery query, int start, int count);

    public List<ShareGiftUser> listAll();

    public List<ShareGiftUser> listByCode(String code);

    public int countByCode(String code);

    public int countCouponByCode(String code);
}
