package com.qcloud.component.sellercenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.MerchantWealthDao;
import com.qcloud.component.sellercenter.model.MerchantWealth;
import com.qcloud.component.sellercenter.model.query.MerchantWealthQuery;

@Repository
public class MerchantWealthDaoCacheImpl implements MerchantWealthDao {

    @Autowired
    private MerchantWealthDao merchantWealthDaoMysqlImpl;

    @Autowired
    private MerchantWealthDao merchantWealthDaoRedisImpl;

    @Override
    public boolean add(MerchantWealth merchantWealth) {

        return merchantWealthDaoMysqlImpl.add(merchantWealth);
    }

    @Override
    public MerchantWealth get(Long id) {

        // return CacheLoader.get(merchantWealthDaoRedisImpl, merchantWealthDaoMysqlImpl, id);
        return merchantWealthDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchantWealthDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MerchantWealth merchantWealth) {

        return merchantWealthDaoMysqlImpl.update(merchantWealth);
    }

    @Override
    public List<MerchantWealth> list(List<Long> idList) {

        return CacheLoader.list(merchantWealthDaoRedisImpl, merchantWealthDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MerchantWealth> map(Set<Long> idSet) {

        return CacheLoader.map(merchantWealthDaoRedisImpl, merchantWealthDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MerchantWealth> page(int start, int count) {

        return merchantWealthDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MerchantWealth> page(MerchantWealthQuery query, int start, int count) {

        return merchantWealthDaoMysqlImpl.page(query, start, count);
    }

    public List<MerchantWealth> listAll() {

        return merchantWealthDaoMysqlImpl.listAll();
    }

    @Override
    public MerchantWealth getByMerchant(long merchantId) {

        return merchantWealthDaoMysqlImpl.getByMerchant(merchantId);
    }

    @Override
    public boolean updateLock(MerchantWealth merchantWealth) {

        return merchantWealthDaoMysqlImpl.updateLock(merchantWealth);
    }
}
