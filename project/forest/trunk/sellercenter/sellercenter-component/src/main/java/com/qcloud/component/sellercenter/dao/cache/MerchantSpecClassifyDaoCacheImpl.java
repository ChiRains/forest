package com.qcloud.component.sellercenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.MerchantSpecClassifyDao;
import com.qcloud.component.sellercenter.model.MerchantSpecClassify;
import com.qcloud.component.sellercenter.model.query.MerchantSpecClassifyQuery;

@Repository
public class MerchantSpecClassifyDaoCacheImpl implements MerchantSpecClassifyDao {

    @Autowired
    private MerchantSpecClassifyDao merchantSpecClassifyDaoMysqlImpl;

    @Autowired
    private MerchantSpecClassifyDao merchantSpecClassifyDaoRedisImpl;

    @Override
    public boolean add(MerchantSpecClassify merchantSpecClassify) {

        return merchantSpecClassifyDaoMysqlImpl.add(merchantSpecClassify);
    }

    @Override
    public MerchantSpecClassify get(Long id) {

        return merchantSpecClassifyDaoMysqlImpl.get(id);
        // return CacheLoader.get(merchantSpecClassifyDaoRedisImpl, merchantSpecClassifyDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return merchantSpecClassifyDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MerchantSpecClassify merchantSpecClassify) {

        return merchantSpecClassifyDaoMysqlImpl.update(merchantSpecClassify);
    }

    @Override
    public List<MerchantSpecClassify> list(List<Long> idList) {

        return CacheLoader.list(merchantSpecClassifyDaoRedisImpl, merchantSpecClassifyDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MerchantSpecClassify> map(Set<Long> idSet) {

        return CacheLoader.map(merchantSpecClassifyDaoRedisImpl, merchantSpecClassifyDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MerchantSpecClassify> page(int start, int count) {

        return merchantSpecClassifyDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MerchantSpecClassify> page(MerchantSpecClassifyQuery query, int start, int count) {

        return merchantSpecClassifyDaoMysqlImpl.page(query, start, count);
    }

    public List<MerchantSpecClassify> listAll() {

        return merchantSpecClassifyDaoMysqlImpl.listAll();
    }

    @Override
    public List<MerchantSpecClassify> listByMerchant(long merchantId) {

        return merchantSpecClassifyDaoMysqlImpl.listByMerchant(merchantId);
    }
}
