package com.qcloud.component.commoditycenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.commoditycenter.model.CombinationMerchandiseItem;
import com.qcloud.component.commoditycenter.model.query.CombinationMerchandiseItemQuery;

/**
 * 组合套餐商品明细
 *
 * @author Zoro
 */
public interface CombinationMerchandiseItemDao extends ISimpleDao<CombinationMerchandiseItem, Long> {

    public boolean add(CombinationMerchandiseItem combinationMerchandiseItem);

    public CombinationMerchandiseItem get(Long id);

    public boolean delete(Long id);

    public boolean update(CombinationMerchandiseItem combinationMerchandiseItem);

    public List<CombinationMerchandiseItem> list(List<Long> idList);

    public Map<Long, CombinationMerchandiseItem> map(Set<Long> idSet);

    public Page<CombinationMerchandiseItem> page(CombinationMerchandiseItemQuery query, int start, int size);

    public List<CombinationMerchandiseItem> listAll();

    List<CombinationMerchandiseItem> listByCombinationMerchandise(long combinationMerchandiseId);

    public List<CombinationMerchandiseItem> list(Map where);

    public boolean delete(Map where);

    List<CombinationMerchandiseItem> listByMerchandiseItem(Long merchandiseItemId, int start, int count);

    int countByMerchandiseItem(Long merchandiseItemId);
}
