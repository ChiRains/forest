package com.qcloud.component.goods.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.goods.dao.UnifiedMerchandiseDao;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum.MerchandiseStateType;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class UnifiedMerchandiseServiceImpl implements UnifiedMerchandiseService {

    @Autowired
    private UnifiedMerchandiseDao unifiedMerchandiseDao;

    @Autowired
    private AutoIdGenerator       autoIdGenerator;

    private static final String   ID_KEY = "goods_unified_merchandise";

    @Autowired
    public PublicdataClient       publicdataClient;

    @Override
    public boolean add(UnifiedMerchandise unifiedMerchandise) {

        long id = autoIdGenerator.get(ID_KEY);
        unifiedMerchandise.setId(id);
        unifiedMerchandise.setRecordTime(new Date());
        unifiedMerchandise.setUpdateTime(new Date());
        Classify mallClassify = publicdataClient.getClassify(unifiedMerchandise.getMallClassifyId());
        if (mallClassify != null) {
            unifiedMerchandise.setMallClassifyBsid(mallClassify.getBsid());
        }
        Classify merchantClassify = publicdataClient.getClassify(unifiedMerchandise.getMerchantClassifyId());
        if (merchantClassify != null) {
            unifiedMerchandise.setMerchantClassifyBsid(merchantClassify.getBsid());
        }
        return unifiedMerchandiseDao.add(unifiedMerchandise);
    }

    @Override
    public UnifiedMerchandise get(Long id) {

        return unifiedMerchandiseDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return unifiedMerchandiseDao.delete(id);
    }

    @Override
    public boolean update(UnifiedMerchandise unifiedMerchandise) {

        unifiedMerchandise.setUpdateTime(new Date());
        Classify mallClassify = publicdataClient.getClassify(unifiedMerchandise.getMallClassifyId());
        if (mallClassify != null) {
            unifiedMerchandise.setMallClassifyBsid(mallClassify.getBsid());
        }
        Classify merchantClassify = publicdataClient.getClassify(unifiedMerchandise.getMerchantClassifyId());
        if (merchantClassify != null) {
            unifiedMerchandise.setMerchantClassifyBsid(merchantClassify.getBsid());
        }
        return unifiedMerchandiseDao.update(unifiedMerchandise);
    }

    @Override
    public Page<UnifiedMerchandise> page(UnifiedMerchandiseQuery query, int start, int count) {

        return unifiedMerchandiseDao.page(query, start, count);
    }

    public List<UnifiedMerchandise> listAll() {

        return unifiedMerchandiseDao.listAll();
    }

    @Override
    public Long addThenRetrunId(UnifiedMerchandise unifiedMerchandise) {

        long id = autoIdGenerator.get(ID_KEY);
        unifiedMerchandise.setId(id);
        return unifiedMerchandiseDao.add(unifiedMerchandise) ? id : -1L;
    }

    @Override
    public List<UnifiedMerchandise> listByMerchandise(long merchandiseId, int type) {

        return unifiedMerchandiseDao.listByMerchandise(merchandiseId, type);
    }

    @Override
    public List<UnifiedMerchandise> listByMerchandise(long merchandiseId, int type, int state) {

        return unifiedMerchandiseDao.listByMerchandise(merchandiseId, type, state);
    }

    @Override
    public boolean increaseGoodEvaluation(long merchandiseId) {

        List<UnifiedMerchandise> list = listByMerchandise(merchandiseId);
        for (UnifiedMerchandise um : list) {
            unifiedMerchandiseDao.increaseGoodEvaluation(um.getId());
        }
        return true;
    }

    @Override
    public boolean increaseMiddleEvaluation(long merchandiseId) {

        List<UnifiedMerchandise> list = listByMerchandise(merchandiseId);
        for (UnifiedMerchandise um : list) {
            unifiedMerchandiseDao.increaseMiddleEvaluation(um.getId());
        }
        return true;
    }

    @Override
    public boolean increaseLowEvaluation(long merchandiseId) {

        List<UnifiedMerchandise> list = listByMerchandise(merchandiseId);
        for (UnifiedMerchandise um : list) {
            unifiedMerchandiseDao.increaseLowEvaluation(um.getId());
        }
        return true;
    }

    @Override
    public List<UnifiedMerchandise> listByMerchandise(long merchandiseId) {

        return unifiedMerchandiseDao.listByMerchandise(merchandiseId);
    }

    @Override
    public boolean updateSalesVolume(long id, int number) {

        return unifiedMerchandiseDao.updateSalesVolume(id, number);
    }

    @Override
    public boolean lockStock(long id, int stock) {

        return unifiedMerchandiseDao.lockStock(id, stock);
    }

    @Override
    public Page<UnifiedMerchandise> select(UnifiedMerchandiseQuery query, int start, int size) {

        return unifiedMerchandiseDao.select(query, start, size);
    }

    @Override
    public boolean updateState(long id, int state) {

        UnifiedMerchandise unifiedMerchandise = get(id);
        AssertUtil.assertNotNull(unifiedMerchandise, "统一商品不存在.");
        unifiedMerchandise.setState(state);
        return update(unifiedMerchandise);
    }

    @Override
    public UnifiedMerchandise getByCodeAndMerchant(String code, long merchantId) {

        return unifiedMerchandiseDao.getByCodeAndMerchant(code, merchantId);
    }

    @Override
    public List<UnifiedMerchandise> listByMerchandise(long merchandiseId, MerchandiseStateType stateType) {

        return unifiedMerchandiseDao.listByMerchandise(merchandiseId, stateType);
    }
}
