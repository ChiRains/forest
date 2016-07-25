package com.qcloud.component.goods.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.goods.UnifiedMerchandiseType;
import com.qcloud.component.goods.dao.UnifiedMerchandiseDao;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum.MerchandiseStateType;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class UnifiedMerchandiseServiceImpl implements UnifiedMerchandiseService {

    @Autowired
    private UnifiedMerchandiseDao unifiedMerchandiseDao;

    @Autowired
    private AutoIdGenerator       autoIdGenerator;

    private static final String   ID_KEY                  = "goods_unified_merchandise";

    @Autowired
    public PublicdataClient       publicdataClient;

    private static final String   unifiedMerhcandise_type = "Goods-UnifiedMerchandise-Type";

    @Override
    public boolean add(UnifiedMerchandise unifiedMerchandise) {

        AssertUtil.assertTrue(validUnifiedMerchandiseType(unifiedMerchandise.getType()), "商品类型尚未配置： key ==>" + unifiedMerchandise.getType());
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

    public boolean validUnifiedMerchandiseType(int type) {

        UnifiedMerchandiseType unifiedMerchandiseType = UnifiedMerchandiseType.Factory.get(type);
        if (unifiedMerchandiseType == null) {
            Xml xml = XmlFactory.get(unifiedMerhcandise_type);
            AssertUtil.assertNotNull(xml, "商品类型尚未配置 code : Goods-UnifiedMerchandise-Type");
            List<XmlItem> itemList = xml.getItemList();
            AssertUtil.assertNotEmpty(itemList, "商品类型尚未配置 code : Goods-UnifiedMerchandise-Type");
            for (XmlItem xmlItem : itemList) {
                int key = Integer.parseInt(String.valueOf(xmlItem.getAttrMap().get("key")));
                if (type == key) {
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
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
    public boolean increaseGoodEvaluation(long unifiedMerchandiseId) {

        return unifiedMerchandiseDao.increaseGoodEvaluation(unifiedMerchandiseId);
    }

    @Override
    public boolean increaseMiddleEvaluation(long unifiedMerchandiseId) {

        return unifiedMerchandiseDao.increaseMiddleEvaluation(unifiedMerchandiseId);
    }

    @Override
    public boolean increaseLowEvaluation(long unifiedMerchandiseId) {

        return unifiedMerchandiseDao.increaseLowEvaluation(unifiedMerchandiseId);
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

    @Transactional
    @Override
    public boolean editMoneyAndStockByList(List<UnifiedMerchandise> merchandiseList) {

        for (UnifiedMerchandise um : merchandiseList) {
            UnifiedMerchandise unifiedMerchandise = get(um.getId());
            AssertUtil.assertNotNull(unifiedMerchandise, "统一商品不存在.");
            AssertUtil.assertTrue(um.getDiscount() >= 0 && um.getPrice() >= 0 && um.getPurchase() >= 0 && um.getStock() >= 0, "进货价/原价/折扣价/库存数量必须大于或者等于 0");
            unifiedMerchandise.setDiscount(um.getDiscount());
            unifiedMerchandise.setPurchase(um.getPurchase());
            unifiedMerchandise.setPrice(um.getPrice());
            unifiedMerchandise.setStock(um.getStock());
            update(unifiedMerchandise);
        }
        return true;
    }

    @Override
    public Page<UnifiedMerchandise> page4Back(UnifiedMerchandiseQuery query, int start, int size) {

        return unifiedMerchandiseDao.page4Back(query, start, size);
    }
}
