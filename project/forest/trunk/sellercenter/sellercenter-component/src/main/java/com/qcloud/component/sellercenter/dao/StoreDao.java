//package com.qcloud.component.sellercenter.dao;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.data.api.ISimpleDao;
//import com.qcloud.component.sellercenter.model.Store;
//import com.qcloud.component.sellercenter.model.query.StoreQuery;
//
//public interface StoreDao extends ISimpleDao<Store, Long> {
//
//    public boolean add(Store store);
//
//    public Store get(Long id);
//
//    public boolean delete(Long id);
//
//    public boolean update(Store store);
//
//    public List<Store> list(List<Long> idList);
//
//    public Map<Long, Store> map(Set<Long> idSet);
//
//    public Page<Store> page(StoreQuery query, int start, int size);
//
//    public List<Store> listAll();
//
//    List<Store> listChildren(Long parentId);
//
//    List<Store> listByMerchant(Long merchantId);
//
//    public List<Store> listByAddress(String province, String city, String district);
//
//    public List<Store> listByParentId(Long parentId);
//}
