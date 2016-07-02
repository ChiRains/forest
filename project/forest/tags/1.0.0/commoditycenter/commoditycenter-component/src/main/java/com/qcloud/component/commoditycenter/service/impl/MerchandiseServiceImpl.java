package com.qcloud.component.commoditycenter.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.commoditycenter.dao.MerchandiseDao;
import com.qcloud.component.commoditycenter.exception.CommoditycenterException;
import com.qcloud.component.commoditycenter.model.Merchandise;
import com.qcloud.component.commoditycenter.model.MerchandiseItem;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.MerchandiseStateType;
import com.qcloud.component.commoditycenter.model.query.MerchandiseQuery;
import com.qcloud.component.commoditycenter.service.MerchandiseItemService;
import com.qcloud.component.commoditycenter.service.MerchandiseService;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class MerchandiseServiceImpl implements MerchandiseService {

    @Autowired
    private MerchandiseDao         merchandiseDao;

    @Autowired
    private AutoIdGenerator        autoIdGenerator;

    private static final String    ID_KEY = "commoditycenter_merchandise";

    @Autowired
    private FileSDKClient          fileSDKClient;

    // @Autowired
    // private UnifiedMerchandiseService unifiedMerchandiseService;
    @Autowired
    private MerchandiseItemService merchandiseItemService;

    @Transactional
    @Override
    public boolean add(Merchandise merchandise) {

        // 判断名称不能重复
        List<Merchandise> names = merchandiseDao.listByName(merchandise.getName());
        if (names.size() > 0) {
            throw new CommoditycenterException("商品名称不能重复!");
        }
        List<Merchandise> codes = merchandiseDao.listByCode(merchandise.getMerchantId(), merchandise.getCode());
        if (codes.size() > 0) {
            throw new CommoditycenterException("商品编号不能重复!");
        }
        // UnifiedMerchandise unifiedMerchandise = new UnifiedMerchandise();
        // unifiedMerchandise.setMerchantId(merchandise.getMerchantId());
        // unifiedMerchandise.setType(UnifiedMerchandiseType.SINGLE.getKey());
        // boolean result = unifiedMerchandiseService.add(unifiedMerchandise);
        // AssertUtil.assertTrue(result, "（1）录入商品失败.");
        String url = fileSDKClient.uidToUrl(merchandise.getImage());
        merchandise.setImage(url);
        String code = initSystemCode(new Long(merchandise.getMerchantId() % 100000).intValue());
        merchandise.setSysCode(code);
        long id = autoIdGenerator.get(ID_KEY);
        merchandise.setId(id);
        boolean result = merchandiseDao.add(merchandise);
        AssertUtil.assertTrue(result, "录入商品失败.");
        // MerchandiseItem merchandiseItem = new MerchandiseItem();
        // merchandiseItem.setKeywords(merchandise.getKeywords());
        // merchandiseItem.setMerchandiseId(merchandise.getId());
        // merchandiseItem.setMallClassifyId(merchandise.getMallClassifyId());
        // merchandiseItem.setMerchandiseSpecificationsId(-1L);
        // merchandiseItem.setMerchantClassifyId(merchandise.getMerchantClassifyId());
        // merchandiseItem.setMerchantId(merchandise.getMerchantId());
        // merchandiseItem.setName(merchandise.getName());
        // merchandiseItem.setStock(0);
        // merchandiseItem.setPurchase(0.0);
        // merchandiseItem.setDiscount(0.0);
        // merchandiseItem.setPrice(0.0);
        // merchandiseItem.setState(merchandise.getState());
        // merchandiseItem.setUnifiedMerchandiseId(unifiedMerchandise.getId());
        // result = merchandiseItemService.add(merchandiseItem);
        // AssertUtil.assertTrue(result, "（3）录入商品失败.");
        return result;
    }

    private String initSystemCode(int prefix) {

        int index = 0;
        do {
            String code = "SP" + StringUtils.leftPad(String.valueOf(prefix), 5, "0") + getNextCode();
            List<Merchandise> list = merchandiseDao.listBySysCode(code);
            if (CollectionUtils.isEmpty(list)) {
                return code;
            }
            index++;
        } while (index > 100);
        throw new CommoditycenterException("系统计算商品编码失败.");
    }

    private String getNextCode() {

        StringBuffer sb = new StringBuffer();
        for (int index = 0; index < 12; index++) {
            sb.append(new Random().nextInt(10));
        }
        return sb.toString();
    }

    @Override
    public Merchandise get(Long id) {

        return merchandiseDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseDao.delete(id);
    }

    @Transactional
    @Override
    public boolean update(Merchandise merchandise) {

        Merchandise m = get(merchandise.getId());
        merchandise.setState(m.getState());
        merchandise.setSpecClassifyId(m.getSpecClassifyId());
        merchandise.setMerchantId(m.getMerchantId());
        String url = fileSDKClient.uidToUrl(merchandise.getImage());
        merchandise.setImage(url);
        merchandise.setSysCode(m.getSysCode());
        if (StringUtils.isEmpty(merchandise.getSysCode())) {
            String code = initSystemCode(new Long(merchandise.getMerchantId() % 100000).intValue());
            merchandise.setSysCode(code);
        }
        boolean result = merchandiseDao.update(merchandise);
        if (result) {
            List<MerchandiseItem> itemList = merchandiseItemService.listByMerchandise(merchandise.getId());
            for (MerchandiseItem merchandiseItem : itemList) {
                merchandiseItem.setKeywords(merchandise.getKeywords());
                merchandiseItem.setMallClassifyId(merchandise.getMallClassifyId());
                merchandiseItem.setMerchantClassifyId(merchandise.getMerchantClassifyId());
                merchandiseItem.setName(merchandise.getName());
                merchandiseItem.setBrandId(merchandise.getBrandId());
                merchandiseItemService.update(merchandiseItem);
            }
        }
        return result;
    }

    @Override
    public Page<Merchandise> page(MerchandiseQuery query, int start, int count) {

        return merchandiseDao.page(query, start, count);
    }

    public List<Merchandise> listAll() {

        return merchandiseDao.listAll();
    }

    @Override
    public Page<Merchandise> page(Map<String, Object> map, int start, int count) {

        return merchandiseDao.page(map, start, count);
    }

    @Override
    public List<Merchandise> merchandiseList(Map<String, Object> map) {

        return merchandiseDao.merchandiseList(map);
    }

    @Override
    public Page<Merchandise> list4MerchandiseState(int state, Long merchantId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("state", state);
        param.put("merchantId", merchantId);
        param.put("count", count);
        param.put("start", start);
        return merchandiseDao.list4MerchandiseState(param);
    }

    private boolean updateMerchandiseState(int state, Long id) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("state", state);
        param.put("id", id);
        List<MerchandiseItem> list = merchandiseItemService.listByMerchandise(id);
        for (MerchandiseItem merchandiseItem : list) {
            if (merchandiseItem.getState() != MerchandiseStateType.INIT.getKey()) {
                merchandiseItem.setState(state);
                merchandiseItemService.update(merchandiseItem);
            }
        }
        return merchandiseDao.updateMerchandiseState(param);
    }

    @Override
    public boolean toNew(Long id) {

        Merchandise merchandise = get(id);
        AssertUtil.assertNotNull(merchandise, "商品不存在" + id);
        return updateMerchandiseState(MerchandiseStateType.NEW.getKey(), id);
    }

    @Override
    public boolean auditing(Long id) {

        Merchandise merchandise = get(id);
        AssertUtil.assertNotNull(merchandise, "商品不存在" + id);
        AssertUtil.assertTrue(merchandise.getState() == MerchandiseStateType.NEW.getKey(), "商品状态不是新增状态." + id + " " + merchandise.getState());
        return updateMerchandiseState(MerchandiseStateType.AUDITING.getKey(), id);
    }

    @Override
    public boolean online(Long id) {

        Merchandise merchandise = get(id);
        AssertUtil.assertNotNull(merchandise, "商品不存在" + id);
        AssertUtil.assertTrue(merchandise.getState() == MerchandiseStateType.AUDITING.getKey() || merchandise.getState() == MerchandiseStateType.OFFLINE.getKey(), "商品不能上线." + id + " " + merchandise.getState());
        return updateMerchandiseState(MerchandiseStateType.ONLINE.getKey(), id);
    }

    @Override
    public boolean offline(Long id) {

        Merchandise merchandise = get(id);
        AssertUtil.assertNotNull(merchandise, "商品不存在" + id);
        return updateMerchandiseState(MerchandiseStateType.OFFLINE.getKey(), id);
    }

    @Override
    public int count4DeleteClassify(Long mallClassifyId) {

        return merchandiseDao.count4DeleteClassify(mallClassifyId);
    }

    @Override
    public List<Merchandise> getMerchandiseList(long merchantId) {

        return merchandiseDao.getMerchandiseList(merchantId);
    }

    @Override
    public int countMerchantOnlineNumber(long merchantId) {

        return merchandiseDao.countMerchantOnlineNumber(merchantId);
    }

    @Override
    public List<Merchandise> list4Admin(MerchandiseQuery query, int start, int count) {

        return merchandiseDao.list4Admin(query, start, count);
    }

    @Override
    public int count4Admin(MerchandiseQuery query) {

        return merchandiseDao.count4Admin(query);
    }

    @Override
    public long getSalesVolume(long merchandiseId) {

        List<MerchandiseItem> list = merchandiseItemService.listByMerchandise(merchandiseId);
        long salesVolume = 0;
        for (MerchandiseItem merchandiseItem : list) {
            salesVolume += getSalesVolume(merchandiseItem);
        }
        return salesVolume;
    }

    private long getSalesVolume(MerchandiseItem merchandiseItem) {

        if (merchandiseItem == null) {
            return 0;
        } else {
            long salesVolume = merchandiseItem.getSalesVolume() > merchandiseItem.getVirtualSalesVolume() ? merchandiseItem.getSalesVolume() : merchandiseItem.getVirtualSalesVolume();
            return salesVolume;
        }
    }
}
