package com.qcloud.component.commoditycenter.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.commoditycenter.dao.MerchandiseItemDao;
import com.qcloud.component.commoditycenter.model.MerchandiseItem;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.OrderType;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.QueryItemType;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.QueryType;
import com.qcloud.component.commoditycenter.model.query.MerchandiseItemQuery;
import com.qcloud.component.commoditycenter.service.MerchandiseItemService;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class MerchandiseItemServiceImpl implements MerchandiseItemService {

    @Autowired
    private MerchandiseItemDao  merchandiseItemDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    // @Autowired
    // private MerchandiseService merchandiseService;
    //
    // @Autowired
    // private MerchandiseSpecificationsService merchandiseSpecificationsService;
    //
    // @Autowired
    // private UnifiedMerchandiseService unifiedMerchandiseService;
    private static final String ID_KEY = "commoditycenter_merchandise_item";

    @Autowired
    public PublicdataClient     publicdataClient;

    @Override
    public boolean add(MerchandiseItem merchandiseItem) {

        long id = autoIdGenerator.get(ID_KEY);
        merchandiseItem.setId(id);
        merchandiseItem.setRecordTime(new Date());
        merchandiseItem.setUpdateTime(new Date());
        Classify mallClassify = publicdataClient.getClassify(merchandiseItem.getMallClassifyId());
        if (mallClassify != null) {
            merchandiseItem.setMallClassifyBsid(mallClassify.getBsid());
        }
        Classify merchantClassify = publicdataClient.getClassify(merchandiseItem.getMerchantClassifyId());
        if (merchantClassify != null) {
            merchandiseItem.setMerchantClassifyBsid(merchantClassify.getBsid());
        }
        return merchandiseItemDao.add(merchandiseItem);
    }

    @Override
    public MerchandiseItem get(Long id) {

        return merchandiseItemDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseItemDao.delete(id);
    }

    @Override
    public boolean update(MerchandiseItem merchandiseItem) {

        MerchandiseItem mi = get(merchandiseItem.getId());
        Date ut = mi.getUpdateTime();
        merchandiseItem.setUpdateTime(new Date());
        Classify mallClassify = publicdataClient.getClassify(merchandiseItem.getMallClassifyId());
        if (mallClassify != null) {
            merchandiseItem.setMallClassifyBsid(mallClassify.getBsid());
        }
        Classify merchantClassify = publicdataClient.getClassify(merchandiseItem.getMerchantClassifyId());
        if (merchantClassify != null) {
            merchandiseItem.setMerchantClassifyBsid(merchantClassify.getBsid());
        }
        return merchandiseItemDao.update(merchandiseItem, ut);
    }

    @Override
    public Page<MerchandiseItem> page(MerchandiseItemQuery query, int start, int count) {

        return merchandiseItemDao.page(query, start, count);
    }

    public List<MerchandiseItem> listAll() {

        return merchandiseItemDao.listAll();
    }

    @Override
    public MerchandiseItem getByUnifiedMerchandise(Long unifiedMerchandiseId) {

        return merchandiseItemDao.getByUnifiedMerchandise(unifiedMerchandiseId);
    }

    // @Override
    // public boolean fillUnifiedMerchandiseBySpecifications(long merchandiseId) {
    //
    // Merchandise merchandise = merchandiseService.get(merchandiseId);
    // AssertUtil.assertNotNull(merchandise, "商品不存在");
    // List<MerchandiseSpecifications> specificationsList = merchandiseSpecificationsService.listByMerchandise(merchandiseId);
    // // List<MerchandisePrice> priceList = merchandisePriceService.listByMerchandise(merchandiseId);
    // List<MerchandiseItem> list = merchandiseItemDao.listByMerchandise(merchandiseId);
    // for (MerchandiseItem merchandiseItem : list) {
    // if (merchandiseItem.getMerchandiseSpecificationsId() > 0) {
    // delete(merchandiseItem.getId());
    // }
    // }
    // for (MerchandiseSpecifications merchandiseSpecifications : specificationsList) {
    // // MerchandisePrice merchandisePrice = getPrice(priceList, merchandiseSpecifications);
    // UnifiedMerchandise unifiedMerchandise = new UnifiedMerchandise();
    // unifiedMerchandise.setMerchantId(merchandise.getMerchantId());
    // unifiedMerchandise.setType(UnifiedMerchandiseType.SINGLE.getKey());
    // unifiedMerchandiseService.add(unifiedMerchandise);
    // MerchandiseItem merchandiseItem = new MerchandiseItem();
    // merchandiseItem.setKeywords(merchandise.getKeywords());
    // merchandiseItem.setMerchandiseSpecificationsId(merchandiseSpecifications.getId());
    // merchandiseItem.setMerchantId(merchandise.getMerchantId());
    // merchandiseItem.setName(merchandise.getName());
    // // merchandiseItem.setPriceId(merchandisePrice == null ? -1L : merchandisePrice.getId());
    // merchandiseItem.setState(merchandise.getState());
    // merchandiseItem.setUnifiedMerchandiseId(unifiedMerchandise.getId());
    // merchandiseItem.setMerchandiseId(merchandise.getId());
    // add(merchandiseItem);
    // }
    // return true;
    // }
    // private MerchandisePrice getPrice(List<MerchandisePrice> priceList, MerchandiseSpecifications merchandiseSpecifications) {
    // for (MerchandisePrice merchandisePrice : priceList) {
    // boolean ok0 = merchandisePrice.getAttributeId() == merchandiseSpecifications.getAttributeId0() && merchandiseSpecifications.getValue0().equals(merchandisePrice.getValue());
    // boolean ok1 = merchandisePrice.getAttributeId() == merchandiseSpecifications.getAttributeId1() && merchandiseSpecifications.getValue1().equals(merchandisePrice.getValue());
    // boolean ok2 = merchandisePrice.getAttributeId() == merchandiseSpecifications.getAttributeId2() && merchandiseSpecifications.getValue2().equals(merchandisePrice.getValue());
    // if (ok0 || ok1 || ok2) {
    // return merchandisePrice;
    // }
    // }
    // return null;
    // }
    @Override
    public MerchandiseItem get(Map where) {

        return merchandiseItemDao.get(where);
    }

    @Override
    public List<MerchandiseItem> list(Map where) {

        return merchandiseItemDao.list(where);
    }

    @Override
    public Page<MerchandiseItem> page(Map where, int start, int size) {

        return merchandiseItemDao.page(where, start, size);
    }

    @Override
    public List<MerchandiseItem> listByMerchandise(Long merchandiseId) {

        return merchandiseItemDao.listByMerchandise(merchandiseId);
    }

    @Override
    public Page<MerchandiseItem> page4Price(String name, Long merchantId, Long merchantClassifyId, int start, int size, OrderType orderType, QueryItemType queryItemType) {

        return merchandiseItemDao.page4Price(name, merchantId, merchantClassifyId, start, size, orderType, queryItemType);
    }

    @Override
    public Page<MerchandiseItem> page4Date(String name, Long merchantId, Long merchantClassifyId, int start, int size, OrderType orderType, QueryItemType queryItemType) {

        return merchandiseItemDao.page4Date(name, merchantId, merchantClassifyId, start, size, orderType, queryItemType);
    }

    @Override
    public MerchandiseItem getBySpecifications(Long merchandiseId, Long merchandiseSpecificationsId) {

        return merchandiseItemDao.getBySpecifications(merchandiseId, merchandiseSpecificationsId);
    }

    @Override
    public boolean lockStock(long id, int stock) {

        MerchandiseItem mi = get(id);
        Date d = mi.getUpdateTime();
        mi.setStock(mi.getStock() + stock);
        mi.setUpdateTime(new Date());
        return merchandiseItemDao.update(mi, d);
    }

    @Override
    public Page<MerchandiseItem> query(MerchandiseItemQuery query, int start, int count) {

        QueryType queryType = query.getQueryType();
        AssertUtil.assertNotNull(queryType, "查询类型不能为空.");
        OrderType orderType = query.getOrderType();
        AssertUtil.assertNotNull(orderType, "排序对象不能为空.");
        QueryItemType queryItemType = query.getQueryItemType();
        AssertUtil.assertNotNull(queryItemType, "数据类别对象不能为空.");
        Classify mallClassify = publicdataClient.getClassify(query.getMallClassifyId());
        Classify merchantClassify = publicdataClient.getClassify(query.getMerchantClassifyId());
        if (mallClassify != null) {
            query.setMallClassifyBsid(mallClassify.getBsid());
        } else {
            query.setMallClassifyBsid("");
        }
        if (merchantClassify != null) {
            query.setMerchantClassifyBsid(merchantClassify.getBsid());
        } else {
            query.setMerchantClassifyBsid("");
        }
        return merchandiseItemDao.query(query, start, count);
    }

    @Override
    public boolean updateSalesVolume(long id, int number) {

        MerchandiseItem mi = get(id);
        Date d = mi.getUpdateTime();
        mi.setSalesVolume(mi.getSalesVolume() + number);
        mi.setUpdateTime(new Date());
        return merchandiseItemDao.update(mi, d);
    }

    @Override
    public Page<MerchandiseItem> list4Select4Admin(MerchandiseItemQuery query, int start, int count) {

        return merchandiseItemDao.list4Select4Admin(query, start, count);
    }

    @Override
    public List<MerchandiseItem> merchandiseListByMerchantId(long merchantId) {

        return merchandiseItemDao.merchandiseListByMerchantId(merchantId);
    }

    private Log logger = LogFactory.getLog(getClass());

    @Override
    public boolean increaseGoodEvaluation(long merchandiseId) {

        List<MerchandiseItem> list = listByMerchandise(merchandiseId);
        for (MerchandiseItem mi : list) {
            Date d = mi.getUpdateTime();
            mi.setUpdateTime(new Date());
            mi.setGoodEvaluation(mi.getGoodEvaluation() + 1);
            boolean result = merchandiseItemDao.update(mi, d);
            if (!result) {
                logger.warn("更新评论失败." + mi.getId());
            }
        }
        return true;
    }

    @Override
    public boolean increaseMiddleEvaluation(long merchandiseId) {

        List<MerchandiseItem> list = listByMerchandise(merchandiseId);
        for (MerchandiseItem mi : list) {
            Date d = mi.getUpdateTime();
            mi.setUpdateTime(new Date());
            mi.setMiddleEvaluation(mi.getMiddleEvaluation() + 1);
            boolean result = merchandiseItemDao.update(mi, d);
            if (!result) {
                logger.warn("更新评论失败." + mi.getId());
            }
        }
        return true;
    }

    @Override
    public boolean increaseLowEvaluation(long merchandiseId) {

        List<MerchandiseItem> list = listByMerchandise(merchandiseId);
        for (MerchandiseItem mi : list) {
            Date d = mi.getUpdateTime();
            mi.setUpdateTime(new Date());
            mi.setLowEvaluation(mi.getLowEvaluation() + 1);
            boolean result = merchandiseItemDao.update(mi, d);
            if (!result) {
                logger.warn("更新评论失败." + mi.getId());
            }
        }
        return true;
    }

    @Transactional
    @Override
    public boolean importMerchandiseItem(List<MerchandiseItem> list) {

        for (MerchandiseItem item : list) {
            MerchandiseItem merchandiseItem = get(item.getId());
            if (merchandiseItem == null) {
                continue;
            } else {
                if (item.getDiscount() <= 0) {
                    continue;
                } else {
                    merchandiseItem.setDiscount(item.getDiscount());
                    update(merchandiseItem);
                }
            }
        }
        return true;
    }
}
