package com.qcloud.component.commoditycenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.commoditycenter.model.Merchandise;
import com.qcloud.component.commoditycenter.model.query.MerchandiseQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

/**
 * 商品基本信息
 * 
 * @author Zoro
 *
 */
public interface MerchandiseDao extends ISimpleDao<Merchandise, Long> {

    public boolean add(Merchandise merchandise);

    public Merchandise get(Long id);

    public boolean delete(Long id);

    public boolean update(Merchandise merchandise);

    public List<Merchandise> list(List<Long> idList);

    public Map<Long, Merchandise> map(Set<Long> idSet);

    public Page<Merchandise> page(MerchandiseQuery query, int start, int size);

    public List<Merchandise> listAll();

    public Page<Merchandise> page(Map<String, Object> map, int start, int count);

    public List<Merchandise> merchandiseList(Map<String, Object> map);

    // 按状态商品列表
    public Page<Merchandise> list4MerchandiseState(Map<String, Object> param);

    // 修改商品的状态 ： 1未审核 2待审核 3审核通过 4审核失败
    public boolean updateMerchandiseState(Map<String, Object> param);

    public List<Merchandise> listByName(String name);

    public List<Merchandise> listBySysCode(String code);

    public List<Merchandise> listByCode(Long merchantId, String code);
    
    public int count4DeleteClassify(Long mallClassifyId);
    
    public List<Merchandise> getMerchandiseList(long merchantId);
    
    int countMerchantOnlineNumber(long merchantId);
  //管理员使用
    public List<Merchandise>  list4Admin(MerchandiseQuery query,int start ,int count);
    
    public int count4Admin(MerchandiseQuery query);
}


