//package com.qcloud.component.sellercenter.service.impl;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.qcloud.component.autoid.AutoIdGenerator;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.component.sellercenter.dao.MerchantSpecClassifyDao;
//import com.qcloud.component.sellercenter.exception.SellerCenterException;
//import com.qcloud.component.sellercenter.model.MerchantSpecClassify;
//import com.qcloud.component.sellercenter.service.MerchantSpecClassifyService;
//import com.qcloud.component.sellercenter.model.query.MerchantSpecClassifyQuery;
//
//@Service
//public class MerchantSpecClassifyServiceImpl implements MerchantSpecClassifyService {
//
//    @Autowired
//    private MerchantSpecClassifyDao merchantSpecClassifyDao;
//
//    @Autowired
//    private AutoIdGenerator         autoIdGenerator;
//
//    private static final String     ID_KEY = "sellercenter_merchant_spec_classify";
//
//    @Override
//    public boolean add(MerchantSpecClassify merchantSpecClassify) {
//
//        long id = autoIdGenerator.get(ID_KEY);
//        merchantSpecClassify.setId(id);
//        return merchantSpecClassifyDao.add(merchantSpecClassify);
//    }
//
//    @Override
//    public MerchantSpecClassify get(Long id) {
//
//        return merchantSpecClassifyDao.get(id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        return merchantSpecClassifyDao.delete(id);
//    }
//
//    @Override
//    public boolean update(MerchantSpecClassify merchantSpecClassify) {
//
//        return merchantSpecClassifyDao.update(merchantSpecClassify);
//    }
//
//    @Override
//    public Page<MerchantSpecClassify> page(MerchantSpecClassifyQuery query, int start, int count) {
//
//        return merchantSpecClassifyDao.page(query, start, count);
//    }
//
//    public List<MerchantSpecClassify> listAll() {
//
//        return merchantSpecClassifyDao.listAll();
//    }
//
//    @Override
//    public List<MerchantSpecClassify> listByMerchant(long merchantId) {
//
//        return merchantSpecClassifyDao.listByMerchant(merchantId);
//    }
//
//    @Override
//    public boolean deleteByMerchant(long merchantId) {
//
//        List<MerchantSpecClassify> list = listByMerchant(merchantId);
//        for (MerchantSpecClassify merchantSpecClassify : list) {
//            delete(merchantSpecClassify.getId());
//        }
//        return true;
//    }
//}
