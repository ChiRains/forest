package com.qcloud.component.sellercenter.service.impl;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.component.sellercenter.dao.StoreDao;
import com.qcloud.component.sellercenter.model.Store;
import com.qcloud.component.sellercenter.model.query.StoreQuery;
import com.qcloud.component.sellercenter.service.StoreService;
import com.qcloud.pirates.data.Page;
@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreDao            storeDao;
    @Autowired
    private AutoIdGenerator     autoIdGenerator;
    private static final String ID_KEY = "sellercenter_store";
    @Autowired
    private FileSDKClient       fileSDKClient;

    @Override
    public boolean add(Store store) {
        List<Store> list = storeDao.listChildren(store.getParentId());
        String bsid = calculateBsid(list);
        Store p = storeDao.get(store.getParentId());
        if (p == null) {
            store.setParentId(-1);
            store.setBsid(bsid);
        } else {
            store.setBsid(p.getBsid() + bsid);
        }
        long id = autoIdGenerator.get(ID_KEY);
        store.setId(id);
        if (StringUtils.isNotEmpty(store.getLogo())) {
            store.setLogo(fileSDKClient.uidToUrl(store.getLogo()));
        }
        store.setEnable(EnableType.ENABLE.getKey());
        return storeDao.add(store);
    }

    @Override
    public Store get(Long id) {
        return storeDao.get(id);
    }

    @Override
    public boolean delete(Long id) {
        return storeDao.delete(id);
    }

    @Override
    public boolean update(Store store) {
        List<Store> list = storeDao.listChildren(store.getParentId());
        String bsid = calculateBsid(list);
        Store p = storeDao.get(store.getParentId());
        if (p == null) {
            store.setParentId(-1);
            store.setBsid(bsid);
        } else {
            store.setBsid(p.getBsid() + bsid);
        }
        if (StringUtils.isNotEmpty(store.getLogo())) {
            store.setLogo(fileSDKClient.uidToUrl(store.getLogo()));
        }
        // 商家ID不能改
        Store s = get(store.getId());
        store.setMerchantId(s.getMerchantId());
        return storeDao.update(store);
    }

    @Override
    public Page<Store> page(StoreQuery query, int start, int count) {
        return storeDao.page(query, start, count);
    }

    public List<Store> listAll() {
        return storeDao.listAll();
    }

    private String calculateBsid(List<Store> list) {
        int bsid = 0;
        for (Store store : list) {
            String bsidStr = store.getBsid();
            bsidStr = bsidStr.substring(bsidStr.length() - 5, bsidStr.length());
            int brother = Integer.valueOf(bsidStr);
            if (brother > bsid) {
                bsid = brother;
            }
        }
        return StringUtils.leftPad(String.valueOf(bsid + 1), 5, "0");
    }

    @Override
    public List<Store> listByMerchant(Long merchantId) {
        return storeDao.listByMerchant(merchantId);
    }

    @Override
    public List<Store> listByAddress(String province, String city, String district) {

        return storeDao.listByAddress(province, city, district);
    }

    @Override
    public List<Store> listByParentId(Long parentId) {

        return storeDao.listByParentId(parentId);
    }
}
