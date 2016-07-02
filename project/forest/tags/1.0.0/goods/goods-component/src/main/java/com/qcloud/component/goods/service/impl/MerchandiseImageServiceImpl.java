package com.qcloud.component.goods.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.goods.dao.MerchandiseImageDao;
import com.qcloud.component.goods.model.MerchandiseImage;
import com.qcloud.component.goods.model.query.MerchandiseImageQuery;
import com.qcloud.component.goods.service.MerchandiseImageService;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.pirates.data.Page;

@Service
public class MerchandiseImageServiceImpl implements MerchandiseImageService {

    @Autowired
    private MerchandiseImageDao merchandiseImageDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    @Autowired
    private FileSDKClient       fileSDKClient;

    //
    private static final String ID_KEY = "goods_merchandise_image";

    @Override
    public boolean add(MerchandiseImage merchandiseImage) {

        long id = autoIdGenerator.get(ID_KEY);
        merchandiseImage.setId(id);
        return merchandiseImageDao.add(merchandiseImage);
    }

    @Override
    public MerchandiseImage get(Long id) {

        return merchandiseImageDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseImageDao.delete(id);
    }

    @Override
    public boolean update(MerchandiseImage merchandiseImage) {

        return merchandiseImageDao.update(merchandiseImage);
    }

    @Override
    public Page<MerchandiseImage> page(MerchandiseImageQuery query, int start, int count) {

        return merchandiseImageDao.page(query, start, count);
    }

    public List<MerchandiseImage> listAll() {

        return merchandiseImageDao.listAll();
    }

    @Override
    public List<MerchandiseImage> listByMerchandise(Long merchandiseId) {

        return merchandiseImageDao.listByMerchandise(merchandiseId);
    }

    @Override
    public boolean setMerchandiseImages(Long merchandiseId, List<MerchandiseImage> list) {

        List<MerchandiseImage> existList = listByMerchandise(merchandiseId);
        for (MerchandiseImage merchandiseImage : existList) {
            delete(merchandiseImage.getId());
        }
        for (MerchandiseImage merchandiseImage : list) {
            merchandiseImage.setMerchandiseId(merchandiseId);
            //String url = fileSDKClient.uidToUrl(merchandiseImage.getImage());
            merchandiseImage.setImage(merchandiseImage.getImage());
            add(merchandiseImage);
        }
        return true;
    }

    @Override
    public List<MerchandiseImage> listByMerchandiseAndAttribute(Long merchandiseId, Long attributeId, String value) {

        return merchandiseImageDao.listByMerchandiseAndAttribute(merchandiseId, attributeId, value);
    }
}
