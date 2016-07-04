//package com.qcloud.component.sellercenter.service.impl;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.qcloud.component.autoid.AutoIdGenerator;
//import com.qcloud.component.filesdk.FileSDKClient;
//import com.qcloud.component.sellercenter.dao.MerchantSortDao;
//import com.qcloud.component.sellercenter.model.MerchantSort;
//import com.qcloud.component.sellercenter.model.query.MerchantSortQuery;
//import com.qcloud.component.sellercenter.service.MerchantSortService;
//import com.qcloud.pirates.data.Page;
//
//
//@Service
//public class MerchantSortServiceImpl implements MerchantSortService {
//    @Autowired
//    private MerchantSortDao merchantSortDao;
//    @Autowired
//    private AutoIdGenerator autoIdGenerator;
//    @Autowired
//    private FileSDKClient       fileSDKClient;
//    private static final String ID_KEY = "sellercenter_merchant_sort";
//
//    
//    @Override
//    public boolean add(MerchantSort merchantSort) {
//        long id=autoIdGenerator.get(ID_KEY);
//        merchantSort.setId(id);
//        if(StringUtils.isNotEmpty(merchantSort.getLogo())){
//            merchantSort.setLogo(fileSDKClient.uidToUrl(merchantSort.getLogo()));
//        }
//        return merchantSortDao.add(merchantSort);
//    }
//
//    @Override
//    public MerchantSort get(Long id) {
//
//        return merchantSortDao.get(id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        return merchantSortDao.delete(id);
//    }
//
//    @Override
//    public boolean update(MerchantSort merchantSort) {
//
//        if(StringUtils.isNotEmpty(merchantSort.getLogo())){
//            merchantSort.setLogo(fileSDKClient.uidToUrl(merchantSort.getLogo()));
//        }
//        return merchantSortDao.update(merchantSort);
//    }
//
//    @Override
//    public List<MerchantSort> list(List<Long> idList) {
//
//        return merchantSortDao.list(idList);
//    }
//
//    @Override
//    public Map<Long, MerchantSort> map(Set<Long> idSet) {
//
//        return merchantSortDao.map(idSet);
//    }
//
//    @Override
//    public Page<MerchantSort> page(MerchantSortQuery query, int start, int count) {
//
//        return merchantSortDao.page(query,start, count);
//    }
//
//    @Override
//    public List<MerchantSort> listAll() {
//
//        return merchantSortDao.listAll();
//    }
//
//    @Override
//    public List<MerchantSort> list(int start, int count) {
//
//        return merchantSortDao.list(start, count);
//    }
//
//}
