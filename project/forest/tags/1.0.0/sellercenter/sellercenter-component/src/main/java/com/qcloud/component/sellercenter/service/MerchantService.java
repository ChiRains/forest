package com.qcloud.component.sellercenter.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.model.Merchant;
import com.qcloud.component.sellercenter.model.query.MerchantQuery;

public interface MerchantService {

    public boolean add(Merchant merchant);

    public Merchant get(Long id);

    public Merchant getByCode(String code);

    public boolean delete(Long id);

    public boolean updateByAdmin(Merchant merchant);

    public boolean updateByMerchant(Merchant merchant);

    public Page<Merchant> page(MerchantQuery query, int start, int count);

    public List<Merchant> listAll();

    public List<Merchant> list(List<Long> idList);

    List<Merchant> list(MerchantQuery query, int start, int count);

    int count(MerchantQuery query);

    // 获取需要审核的商家列表
    public Page<Merchant> listNeedAudit(String keyword, int start, int size);

    public Page<Merchant> pageMerchant(MerchantQuery query, int start, int size);

    public boolean disableMerchant(long id);

    public boolean enableMerchant(long id);

    /**
     * 删除商家分类时根据分类id获取商家列表
     * @param classifyId   分类id
     * @return
     */
    public int count4DeleteByClassifyId(Long classifyId);

    public boolean updateMerchantNotify(long id, int notify);
    
    int countByName(String name);
}
