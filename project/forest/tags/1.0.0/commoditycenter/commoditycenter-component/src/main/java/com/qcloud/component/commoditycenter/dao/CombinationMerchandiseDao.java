package com.qcloud.component.commoditycenter.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.commoditycenter.model.CombinationMerchandise;
import com.qcloud.component.commoditycenter.model.query.CombinationMerchandiseQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

/**
 * 组合商品套餐
 *
 * @author Zoro
 */
public interface CombinationMerchandiseDao extends ISimpleDao<CombinationMerchandise, Long> {

    public boolean add(CombinationMerchandise combinationMerchandise);

    public CombinationMerchandise get(Long id);

    public boolean delete(Long id);
    
    public boolean update(CombinationMerchandise combinationMerchandise);

    public boolean update(CombinationMerchandise combinationMerchandise, Date lastUpdateTime);

    public List<CombinationMerchandise> list(List<Long> idList);

    public Map<Long, CombinationMerchandise> map(Set<Long> idSet);

    public Page<CombinationMerchandise> page(CombinationMerchandiseQuery query, int start, int size);

    public List<CombinationMerchandise> listAll();

    CombinationMerchandise getByUnifiedMerchandise(Long unifiedMerchandiseId);

    public List<CombinationMerchandise> list(Map where);

    public CombinationMerchandise get(Map where);

    public Page<CombinationMerchandise> page(Map where, int start, int size);
}
