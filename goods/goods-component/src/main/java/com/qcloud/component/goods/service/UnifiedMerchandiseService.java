package com.qcloud.component.goods.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum.MerchandiseStateType;
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

    public List<UnifiedMerchandise> listByMerchandise(long merchandiseId, MerchandiseStateType stateType);

    public Long addThenRetrunId(UnifiedMerchandise unifiedMerchandise);

    boolean increaseGoodEvaluation(long unifiedMerchandiseId);

    boolean increaseMiddleEvaluation(long unifiedMerchandiseId);

    boolean increaseLowEvaluation(long unifiedMerchandiseId);

    boolean updateSalesVolume(long id, int number);

    boolean lockStock(long id, int stock);

    boolean updateState(long id, int state);

    public UnifiedMerchandise getByCodeAndMerchant(String code, long merchantId);

    public boolean editMoneyAndStockByList(List<UnifiedMerchandise> merchandiseList);
}
