package com.qcloud.component.sellercenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.sellercenter.dao.MerchantDao;
import com.qcloud.component.sellercenter.model.Merchant;
import com.qcloud.component.sellercenter.model.query.MerchantQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MerchantDaoRedisImpl implements MerchantDao {

    // @Resource(name = "redis-sellercenter")
    // private Redis redis;
    @Override
    public boolean add(Merchant merchant) {

        throw new NotImplementedException();
    }

    @Override
    public Merchant get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(Merchant merchant) {

        throw new NotImplementedException();
    }

    @Override
    public List<Merchant> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Merchant> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Merchant> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Merchant> page(MerchantQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<Merchant> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<Merchant> list(MerchantQuery query, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public List<Merchant> listNew(MerchantQuery query, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public List<Merchant> listHot(MerchantQuery query, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public List<Merchant> listRecently(MerchantQuery query, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public List<Merchant> listFavourable(MerchantQuery query, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public int count(MerchantQuery query) {

        throw new NotImplementedException();
    }

    @Override
    public int countNew(MerchantQuery query) {

        throw new NotImplementedException();
    }

    @Override
    public int countHot(MerchantQuery query) {

        throw new NotImplementedException();
    }

    @Override
    public int countRecently(MerchantQuery query) {

        throw new NotImplementedException();
    }

    @Override
    public int countFavourable(MerchantQuery query) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Merchant> listNeedAudit(String keyword, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Merchant> pageMerchant(MerchantQuery query, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public boolean disableMerchant(long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean enableMerchant(long id) {

        throw new NotImplementedException();
    }

    @Override
    public int count4DeleteByClassifyId(Long classifyId) {

        throw new NotImplementedException();
    }

    @Override
    public boolean updateMerchantNotify(long id,int notify) {

        throw new NotImplementedException();
    }

    @Override
    public Merchant getByCode(String code) {
        
        throw new NotImplementedException();
    }

    @Override
    public int countByName(String name) {

        throw new NotImplementedException();
    }
}
