package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseDao;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.query.MerchandiseQuery;

@Repository
public class MerchandiseDaoCacheImpl implements MerchandiseDao {

    @Autowired
    private MerchandiseDao merchandiseDaoMysqlImpl;

    // @Autowired
    // private MerchandiseDao merchandiseDaoRedisImpl;
    @Override
    public boolean add(Merchandise merchandise) {

        return merchandiseDaoMysqlImpl.add(merchandise);
    }

    @Override
    public Merchandise get(Long id) {

        return merchandiseDaoMysqlImpl.get(id);
        // return CacheLoader.get(merchandiseDaoRedisImpl, merchandiseDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(Merchandise merchandise) {

        return merchandiseDaoMysqlImpl.update(merchandise);
    }

    @Override
    public List<Merchandise> list(List<Long> idList) {

        return CacheLoader.list(merchandiseDaoMysqlImpl, idList);
        // return CacheLoader.list(merchandiseDaoRedisImpl, merchandiseDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, Merchandise> map(Set<Long> idSet) {

        return CacheLoader.map(merchandiseDaoMysqlImpl, idSet);
        // return CacheLoader.map(merchandiseDaoRedisImpl, merchandiseDaoMysqlImpl, idSet);
    }

    @Override
    public Page<Merchandise> page(int start, int count) {

        return merchandiseDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<Merchandise> page(MerchandiseQuery query, int start, int count) {

        return merchandiseDaoMysqlImpl.page(query, start, count);
    }

    public List<Merchandise> listAll() {

        return merchandiseDaoMysqlImpl.listAll();
    }

    @Override
    public Page<Merchandise> page(Map<String, Object> map, int start, int count) {

        return merchandiseDaoMysqlImpl.page(map, start, count);
    }

    @Override
    public List<Merchandise> merchandiseList(Map<String, Object> map) {

        return merchandiseDaoMysqlImpl.merchandiseList(map);
    }

    @Override
    public Page<Merchandise> list4MerchandiseState(Map<String, Object> param) {

        return merchandiseDaoMysqlImpl.list4MerchandiseState(param);
    }

    @Override
    public boolean updateMerchandiseState(Map<String, Object> param) {

        return merchandiseDaoMysqlImpl.updateMerchandiseState(param);
    }

    @Override
    public List<Merchandise> listByName(String name) {

        return merchandiseDaoMysqlImpl.listByName(name);
    }

    @Override
    public List<Merchandise> listBySysCode(String code) {
        return merchandiseDaoMysqlImpl.listBySysCode(code);
    }

    @Override
    public List<Merchandise> listByCode(Long merchantId, String code) {      
        return merchandiseDaoMysqlImpl.listByCode(merchantId, code);
    }

    @Override
    public int count4DeleteClassify(Long mallClassifyId) {

        return merchandiseDaoMysqlImpl.count4DeleteClassify(mallClassifyId);
    }

    @Override
    public List<Merchandise> getMerchandiseList(long merchantId) {

        return merchandiseDaoMysqlImpl.getMerchandiseList(merchantId);
    }

    @Override
    public int countMerchantOnlineNumber(long merchantId) {

        return merchandiseDaoMysqlImpl.countMerchantOnlineNumber(merchantId);
    }

    @Override
    public List<Merchandise> list4Admin(MerchandiseQuery query, int start, int count) {

        return merchandiseDaoMysqlImpl.list4Admin(query, start, count);
    }

    @Override
    public int count4Admin(MerchandiseQuery query) {

        return merchandiseDaoMysqlImpl.count4Admin(query);
    }
}
