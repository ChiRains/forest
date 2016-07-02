//package com.qcloud.component.goods.service.impl;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.qcloud.component.autoid.AutoIdGenerator;
//import com.qcloud.component.goods.dao.CombinationMerchandiseDao;
//import com.qcloud.component.goods.model.CombinationMerchandise;
//import com.qcloud.component.goods.model.query.CombinationMerchandiseQuery;
//import com.qcloud.component.goods.service.CombinationMerchandiseService;
//import com.qcloud.component.filesdk.FileSDKClient;
//import com.qcloud.pirates.data.Page;
//
//@Service
//public class CombinationMerchandiseServiceImpl implements CombinationMerchandiseService {
//
//    @Autowired
//    private CombinationMerchandiseDao combinationMerchandiseDao;
//
//    @Autowired
//    private AutoIdGenerator           autoIdGenerator;
//    
//    @Autowired
//    private FileSDKClient fileSDKClient;
//
//    private static final String       ID_KEY = "goods_combination_merchandise";
//
//    @Override
//    public boolean add(CombinationMerchandise combinationMerchandise) {
//        if(StringUtils.isNotEmpty(combinationMerchandise.getImage())){
//            combinationMerchandise.setImage(fileSDKClient.uidToUrl(combinationMerchandise.getImage()));
//        }
//        long id = autoIdGenerator.get(ID_KEY);
//        combinationMerchandise.setId(id);
//        combinationMerchandise.setUpdateTime(new Date());
//        return combinationMerchandiseDao.add(combinationMerchandise);
//    }
//
//    @Override
//    public CombinationMerchandise get(Long id) {
//
//        return combinationMerchandiseDao.get(id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        return combinationMerchandiseDao.delete(id);
//    }
//
//    @Override
//    public boolean update(CombinationMerchandise combinationMerchandise) {
//
//        
//        CombinationMerchandise c = get(combinationMerchandise.getId());
//        Date d = c.getUpdateTime();
//        combinationMerchandise.setUpdateTime(new Date());
//        if(StringUtils.isNotEmpty(combinationMerchandise.getImage())){
//            combinationMerchandise.setImage(fileSDKClient.uidToUrl(combinationMerchandise.getImage()));
//        }
//        return combinationMerchandiseDao.update(combinationMerchandise, d);
//    }
//
//    @Override
//    public Page<CombinationMerchandise> page(CombinationMerchandiseQuery query, int start, int count) {
//
//        return combinationMerchandiseDao.page(query, start, count);
//    }
//
//    public List<CombinationMerchandise> listAll() {
//
//        return combinationMerchandiseDao.listAll();
//    }
//
//    @Override
//    public CombinationMerchandise getByUnifiedMerchandise(Long unifiedMerchandiseId) {
//
//        return combinationMerchandiseDao.getByUnifiedMerchandise(unifiedMerchandiseId);
//    }
//
//    @Override
//    public Page<CombinationMerchandise> page(Map where, int start, int size) {
//
//        return combinationMerchandiseDao.page(where, start, size);
//    }
//
//    @Override
//    public List<CombinationMerchandise> list(Map where) {
//
//        return combinationMerchandiseDao.list(where);
//    }
//
//    @Override
//    public CombinationMerchandise get(Map where) {
//
//        return combinationMerchandiseDao.get(where);
//    }
//
//    @Override
//    public boolean lockStock(long unifiedMerchandiseId, int stock) {
//
//        CombinationMerchandise c = getByUnifiedMerchandise(unifiedMerchandiseId);
//        Date d = c.getUpdateTime();
//        c.setUpdateTime(new Date());
//        c.setStock(c.getStock() + stock);
//        return combinationMerchandiseDao.update(c, d);
//    }
//}
