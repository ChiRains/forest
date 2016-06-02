package com.qcloud.component.sellercenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.MerchantDao;
import com.qcloud.component.sellercenter.model.Merchant;
import com.qcloud.component.sellercenter.model.query.MerchantQuery;

@Repository
public class MerchantDaoCacheImpl implements MerchantDao {

    @Autowired
    private MerchantDao merchantDaoMysqlImpl;

    // @Autowired
    // private MerchantDao merchantDaoRedisImpl;
    @Override
    public boolean add(Merchant merchant) {

        return merchantDaoMysqlImpl.add(merchant);
    }

    @Override
    public Merchant get(Long id) {

        return merchantDaoMysqlImpl.get(id);
        // return CacheLoader.get(merchantDaoRedisImpl, merchantDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return merchantDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(Merchant merchant) {

        return merchantDaoMysqlImpl.update(merchant);
    }

    @Override
    public List<Merchant> list(List<Long> idList) {

        return CacheLoader.list(merchantDaoMysqlImpl, idList);
        // return CacheLoader.list(merchantDaoRedisImpl, merchantDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, Merchant> map(Set<Long> idSet) {

        return CacheLoader.map(merchantDaoMysqlImpl, idSet);
        // return CacheLoader.map(merchantDaoRedisImpl, merchantDaoMysqlImpl, idSet);
    }

    @Override
    public Page<Merchant> page(int start, int count) {

        return merchantDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<Merchant> page(MerchantQuery query, int start, int count) {

        return merchantDaoMysqlImpl.page(query, start, count);
    }

    public List<Merchant> listAll() {

        return merchantDaoMysqlImpl.listAll();
    }

    @Override
    public List<Merchant> list(MerchantQuery query, int start, int size) {

        return merchantDaoMysqlImpl.list(query, start, size);
    }

    @Override
    public List<Merchant> listNew(MerchantQuery query, int start, int size) {

        return merchantDaoMysqlImpl.listNew(query, start, size);
    }

    @Override
    public List<Merchant> listHot(MerchantQuery query, int start, int size) {

        return merchantDaoMysqlImpl.listHot(query, start, size);
    }

    @Override
    public List<Merchant> listRecently(MerchantQuery query, int start, int size) {

        return merchantDaoMysqlImpl.listRecently(query, start, size);
    }

    @Override
    public List<Merchant> listFavourable(MerchantQuery query, int start, int size) {

        return merchantDaoMysqlImpl.listFavourable(query, start, size);
    }

    @Override
    public int count(MerchantQuery query) {

        return merchantDaoMysqlImpl.count(query);
    }

    @Override
    public int countNew(MerchantQuery query) {

        return merchantDaoMysqlImpl.countNew(query);
    }

    @Override
    public int countHot(MerchantQuery query) {

        return merchantDaoMysqlImpl.countHot(query);
    }

    @Override
    public int countRecently(MerchantQuery query) {

        return merchantDaoMysqlImpl.countRecently(query);
    }

    @Override
    public int countFavourable(MerchantQuery query) {

        return merchantDaoMysqlImpl.countFavourable(query);
    }

    @Override
    public Page<Merchant> listNeedAudit(String keyword, int start, int size) {

        return merchantDaoMysqlImpl.listNeedAudit(keyword, start, size);
    }

    @Override
    public Page<Merchant> pageMerchant(MerchantQuery query, int start, int size) {

        return merchantDaoMysqlImpl.pageMerchant(query, start, size);
    }

    @Override
    public boolean disableMerchant(long id) {

        return merchantDaoMysqlImpl.disableMerchant(id);
    }

    @Override
    public boolean enableMerchant(long id) {

        return merchantDaoMysqlImpl.enableMerchant(id);
    }

    @Override
    public int count4DeleteByClassifyId(Long classifyId) {

        return merchantDaoMysqlImpl.count4DeleteByClassifyId(classifyId);
    }

    @Override
    public boolean updateMerchantNotify(long id,int notify) {

        return merchantDaoMysqlImpl.updateMerchantNotify(id,notify);
    }

    @Override
    public Merchant getByCode(String code) {

        return merchantDaoMysqlImpl.getByCode(code);
    }

    @Override
    public int countByName(String name) {
      
        return merchantDaoMysqlImpl.countByName(name);
    }
}
