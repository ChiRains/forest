package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.ShareGift;
import com.qcloud.project.forest.model.query.ShareGiftQuery;

public interface ShareGiftService {

    public boolean add(ShareGift shareGift);

    public ShareGift get(Long id);

    public boolean delete(Long id);

    public boolean update(ShareGift shareGift);

    public Page<ShareGift> page(ShareGiftQuery query, int start, int count);

    public List<ShareGift> listAll();

    public ShareGift getByUserId(Long userId);
}
