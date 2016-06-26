package com.qcloud.component.goods.service;

import java.util.List;
import java.util.Map;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.query.MerchandiseQuery;

public interface MerchandiseService {

    public boolean add(Merchandise merchandise);

    public Merchandise get(Long id);

    public boolean delete(Long id);

    public boolean update(Merchandise merchandise);

    public Page<Merchandise> page(MerchandiseQuery query, int start, int count);

    public List<Merchandise> listAll();

    // Page<Merchandise> query(MerchandiseQuery query, int start, int count);
    public Page<Merchandise> page(Map<String, Object> map, int start, int count);

    public List<Merchandise> merchandiseList(Map<String, Object> map);

    // 按状态商品列表
    public Page<Merchandise> list4MerchandiseState(int state, Long merchantId, int start, int count);

    // 拒绝审核
    boolean toNew(Long id);

    // 提交审核
    public boolean auditing(Long id);

    // 上线
    public boolean online(Long id);

    // 上线
    public boolean offline(Long id);

    public int count4DeleteClassify(Long mallClassifyId);

    public List<Merchandise> getMerchandiseList(long merchantId);

    public int countMerchantOnlineNumber(long merchantId);
    
    //管理员使用
    public List<Merchandise>  list4Admin(MerchandiseQuery query,int start ,int count);
    
    public int count4Admin(MerchandiseQuery query);
    
    long getSalesVolume(long merchandiseId);
}
