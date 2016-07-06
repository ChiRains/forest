package com.qcloud.component.goods.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;

public interface UnifiedMerchandiseService {

    public boolean add(UnifiedMerchandise unifiedMerchandise);

    public UnifiedMerchandise get(Long id);

    public boolean delete(Long id);

    public boolean update(UnifiedMerchandise unifiedMerchandise);

    public Page<UnifiedMerchandise> page(UnifiedMerchandiseQuery query, int start, int count);

    Page<UnifiedMerchandise> select(UnifiedMerchandiseQuery query, int start, int size);

    public List<UnifiedMerchandise> listAll();

    public List<UnifiedMerchandise> listByMerchandise(long merchandiseId);

    public List<UnifiedMerchandise> listByMerchandise(long merchandiseId, int type);

    public List<UnifiedMerchandise> listByMerchandise(long merchandiseId, int type, int state);

    public Long addThenRetrunId(UnifiedMerchandise unifiedMerchandise);

    boolean increaseGoodEvaluation(long merchandiseId);

    boolean increaseMiddleEvaluation(long merchandiseId);

    boolean increaseLowEvaluation(long merchandiseId);

    boolean updateSalesVolume(long id, int number);

    boolean lockStock(long id, int stock);

    boolean updateState(long id, int state);

    public UnifiedMerchandise getByCodeAndMerchant(String code, long merchantId);
}
