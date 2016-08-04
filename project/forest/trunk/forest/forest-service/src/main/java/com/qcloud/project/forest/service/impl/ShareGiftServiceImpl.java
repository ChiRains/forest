package com.qcloud.project.forest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ShareGiftDao;
import com.qcloud.project.forest.model.ShareGift;
import com.qcloud.project.forest.model.query.ShareGiftQuery;
import com.qcloud.project.forest.service.ShareGiftService;

@Service
public class ShareGiftServiceImpl implements ShareGiftService {

    @Autowired
    private ShareGiftDao        shareGiftDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "forest_share_gift";

    @Override
    public boolean add(ShareGift shareGift) {

        long id = autoIdGenerator.get(ID_KEY);
        shareGift.setId(id);
        return shareGiftDao.add(shareGift);
    }

    @Override
    public ShareGift get(Long id) {

        return shareGiftDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return shareGiftDao.delete(id);
    }

    @Override
    public boolean update(ShareGift shareGift) {

        return shareGiftDao.update(shareGift);
    }

    @Override
    public Page<ShareGift> page(ShareGiftQuery query, int start, int count) {

        return shareGiftDao.page(query, start, count);
    }

    public List<ShareGift> listAll() {

        return shareGiftDao.listAll();
    }

    @Override
    public ShareGift getByUserId(Long userId) {

        return shareGiftDao.getByUserId(userId);
    }
}
