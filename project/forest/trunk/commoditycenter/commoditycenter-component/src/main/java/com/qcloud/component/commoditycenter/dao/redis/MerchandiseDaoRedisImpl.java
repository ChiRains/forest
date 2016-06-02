package com.qcloud.component.commoditycenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.commoditycenter.dao.MerchandiseDao;
import com.qcloud.component.commoditycenter.model.Merchandise;
import com.qcloud.component.commoditycenter.model.query.MerchandiseQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MerchandiseDaoRedisImpl implements MerchandiseDao {

    // @Resource(name = "redis-commoditycenter")
    // private Redis redis;
    @Override
    public boolean add(Merchandise merchandise) {

        throw new NotImplementedException();
    }

    @Override
    public Merchandise get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(Merchandise merchandise) {

        throw new NotImplementedException();
    }

    @Override
    public List<Merchandise> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Merchandise> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Merchandise> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Merchandise> page(MerchandiseQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<Merchandise> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public Page<Merchandise> page(Map<String, Object> map, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<Merchandise> merchandiseList(Map<String, Object> map) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Merchandise> list4MerchandiseState(Map<String, Object> param) {

        throw new NotImplementedException();
    }

    @Override
    public boolean updateMerchandiseState(Map<String, Object> param) {

        throw new NotImplementedException();
    }

    @Override
    public List<Merchandise> listBySysCode(String code) {

        throw new NotImplementedException();
    }

    @Override
    public List<Merchandise> listByCode(Long merchantId, String code) {

        throw new NotImplementedException();
    }

    @Override
    public List<Merchandise> listByName(String name) {

        throw new NotImplementedException();
    }

    @Override
    public int count4DeleteClassify(Long mallClassifyId) {

        throw new NotImplementedException();
    }

    @Override
    public List<Merchandise> getMerchandiseList(long merchantId) {

        throw new NotImplementedException();
    }

    @Override
    public int countMerchantOnlineNumber(long merchantId) {

        throw new NotImplementedException();
    }

    @Override
    public List<Merchandise> list4Admin(MerchandiseQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public int count4Admin(MerchandiseQuery query) {

        throw new NotImplementedException();
    }
}
