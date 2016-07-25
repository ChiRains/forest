package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.UnifiedMerchandiseDao;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum.MerchandiseStateType;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;

@Repository
public class UnifiedMerchandiseDaoCacheImpl implements UnifiedMerchandiseDao {

    @Autowired
    private UnifiedMerchandiseDao unifiedMerchandiseDaoMysqlImpl;

    // @Autowired
    // private UnifiedMerchandiseDao unifiedMerchandiseDaoRedisImpl;
    @Override
    public boolean add(UnifiedMerchandise unifiedMerchandise) {

        return unifiedMerchandiseDaoMysqlImpl.add(unifiedMerchandise);
    }

    @Override
    public UnifiedMerchandise get(Long id) {

        return unifiedMerchandiseDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return unifiedMerchandiseDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(UnifiedMerchandise unifiedMerchandise) {

        return unifiedMerchandiseDaoMysqlImpl.update(unifiedMerchandise);
    }

    @Override
    public List<UnifiedMerchandise> list(List<Long> idList) {

        return CacheLoader.list(unifiedMerchandiseDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, UnifiedMerchandise> map(Set<Long> idSet) {

        return CacheLoader.map(unifiedMerchandiseDaoMysqlImpl, idSet);
    }

    @Override
    public Page<UnifiedMerchandise> page(int start, int count) {

        return unifiedMerchandiseDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<UnifiedMerchandise> page(UnifiedMerchandiseQuery query, int start, int count) {

        return unifiedMerchandiseDaoMysqlImpl.page(query, start, count);
    }

    public List<UnifiedMerchandise> listAll() {

        return unifiedMerchandiseDaoMysqlImpl.listAll();
    }

    @Override
    public List<UnifiedMerchandise> listByMerchandise(long merchandiseId) {

        return unifiedMerchandiseDaoMysqlImpl.listByMerchandise(merchandiseId);
    }

    @Override
    public List<UnifiedMerchandise> listByMerchandise(long merchandiseId, int type) {

        return unifiedMerchandiseDaoMysqlImpl.listByMerchandise(merchandiseId, type);
    }

    @Override
    public List<UnifiedMerchandise> listByMerchandise(long merchandiseId, int type, int state) {

        return unifiedMerchandiseDaoMysqlImpl.listByMerchandise(merchandiseId, type, state);
    }

    @Override
    public boolean increaseGoodEvaluation(long id) {

        return unifiedMerchandiseDaoMysqlImpl.increaseGoodEvaluation(id);
    }

    @Override
    public boolean increaseMiddleEvaluation(long id) {

        return unifiedMerchandiseDaoMysqlImpl.increaseMiddleEvaluation(id);
    }

    @Override
    public boolean increaseLowEvaluation(long id) {

        return unifiedMerchandiseDaoMysqlImpl.increaseLowEvaluation(id);
    }

    @Override
    public boolean updateSalesVolume(long id, int number) {

        return unifiedMerchandiseDaoMysqlImpl.updateSalesVolume(id, number);
    }

    @Override
    public boolean lockStock(long id, int stock) {

        return unifiedMerchandiseDaoMysqlImpl.lockStock(id, stock);
    }

    @Override
    public Page<UnifiedMerchandise> select(UnifiedMerchandiseQuery query, int start, int size) {

        return unifiedMerchandiseDaoMysqlImpl.select(query, start, size);
    }

    @Override
    public UnifiedMerchandise getByCodeAndMerchant(String code, long merchantId) {

        return unifiedMerchandiseDaoMysqlImpl.getByCodeAndMerchant(code, merchantId);
    }

    @Override
    public List<UnifiedMerchandise> listByMerchandise(long merchandiseId, MerchandiseStateType stateType) {

        return unifiedMerchandiseDaoMysqlImpl.listByMerchandise(merchandiseId, stateType);
    }

    @Override
    public Page<UnifiedMerchandise> page4Back(UnifiedMerchandiseQuery query, int start, int size) {

        return unifiedMerchandiseDaoMysqlImpl.page4Back(query, start, size);
    }
}
