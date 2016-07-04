//package com.qcloud.component.sellercenter.dao;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.data.api.ISimpleDao;
//import com.qcloud.component.sellercenter.model.MerchantWealth;
//import com.qcloud.component.sellercenter.model.query.MerchantWealthQuery;
//
//public interface MerchantWealthDao extends ISimpleDao<MerchantWealth, Long> {
//
//    public boolean add(MerchantWealth merchantWealth);
//
//    public MerchantWealth get(Long id);
//
//    public boolean delete(Long id);
//
//    public boolean update(MerchantWealth merchantWealth);
//
//    public List<MerchantWealth> list(List<Long> idList);
//
//    public Map<Long, MerchantWealth> map(Set<Long> idSet);
//
//    public Page<MerchantWealth> page(MerchantWealthQuery query, int start, int size);
//
//    public List<MerchantWealth> listAll();
//
//    public MerchantWealth getByMerchant(long merchantId);
//
//    public boolean updateLock(MerchantWealth merchantWealth);
//}
