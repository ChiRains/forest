package com.qcloud.component.goods.service;

import java.util.List;
import java.util.Map;
import com.qcloud.component.goods.model.CombinationMerchandise;
import com.qcloud.component.goods.model.query.CombinationMerchandiseQuery;
import com.qcloud.pirates.data.Page;

public interface CombinationMerchandiseService {

    public boolean add(CombinationMerchandise combinationMerchandise);

    public CombinationMerchandise get(Long id);

    public CombinationMerchandise getByUnifiedMerchandise(Long unifiedMerchandiseId);

    public boolean delete(Long id);

    public boolean update(CombinationMerchandise combinationMerchandise);

    public Page<CombinationMerchandise> page(CombinationMerchandiseQuery query, int start, int count);

    public List<CombinationMerchandise> listAll();

    public Page<CombinationMerchandise> page(Map where, int start, int size);

    public List<CombinationMerchandise> list(Map where);

    public CombinationMerchandise get(Map where);

    boolean lockStock(long unifiedMerchandiseId, int stock);

//    int countStock4MerchandiseItem(long merchandiseItemId);
}
