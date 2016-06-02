package com.qcloud.component.sellercenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.sellercenter.model.Merchant;
import com.qcloud.component.sellercenter.model.query.MerchantQuery;

public interface MerchantDao extends ISimpleDao<Merchant, Long> {

    public boolean add(Merchant merchant);

    public Merchant get(Long id);

    public boolean delete(Long id);

    public boolean update(Merchant merchant);

    public List<Merchant> list(List<Long> idList);

    public Map<Long, Merchant> map(Set<Long> idSet);

    public Page<Merchant> page(MerchantQuery query, int start, int size);

    public List<Merchant> listAll();

    List<Merchant> list(MerchantQuery query, int start, int size);

    List<Merchant> listNew(MerchantQuery query, int start, int size);

    List<Merchant> listHot(MerchantQuery query, int start, int size);

    List<Merchant> listRecently(MerchantQuery query, int start, int size);

    List<Merchant> listFavourable(MerchantQuery query, int start, int size);

    int count(MerchantQuery query);

    int countNew(MerchantQuery query);

    int countHot(MerchantQuery query);

    int countRecently(MerchantQuery query);

    int countFavourable(MerchantQuery query);

    // 分页获取需要审核的商家列表
    Page<Merchant> listNeedAudit(String keyword, int start, int size);

    public Page<Merchant> pageMerchant(MerchantQuery query, int start, int size);

    // 禁用商家
    public boolean disableMerchant(long id);

    // 启用商家
    public boolean enableMerchant(long id);

    /**
     * 删除商家分类时根据分类id获取商家列表
     * @param classifyId   分类id
     * @return
     */
    public int count4DeleteByClassifyId(Long classifyId);

    public boolean updateMerchantNotify(long id, int notify);

    Merchant getByCode(String code);

    int countByName(String name);
}
