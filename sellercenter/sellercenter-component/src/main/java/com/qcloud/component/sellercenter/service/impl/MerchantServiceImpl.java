//package com.qcloud.component.sellercenter.service.impl;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.qcloud.component.autoid.AutoIdGenerator;
//import com.qcloud.component.autoid.UniqueCodeGenerator;
//import com.qcloud.component.filesdk.FileSDKClient;
//import com.qcloud.component.sellercenter.dao.MerchantDao;
//import com.qcloud.component.sellercenter.model.Merchant;
//import com.qcloud.component.sellercenter.model.query.MerchantQuery;
//import com.qcloud.component.sellercenter.service.MerchantService;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.util.AssertUtil;
//
//@Service
//public class MerchantServiceImpl implements MerchantService {
//
//    @Autowired
//    private MerchantDao         merchantDao;
//
//    @Autowired
//    private AutoIdGenerator     autoIdGenerator;
//
//    @Autowired
//    private FileSDKClient       fileSDKClient;
//
//    @Autowired
//    private UniqueCodeGenerator uniqueCodeGenerator;
//
//    private static final String ID_KEY = "sellercenter_merchant";
//
//    @Override
//    public boolean add(Merchant merchant) {
//
//        int number = merchantDao.countByName(merchant.getName());
//        AssertUtil.assertTrue(number == 0, "商家已经存在." + merchant.getName());
//        merchant.setCode(uniqueCodeGenerator.generate("sellercenter-merchant-code", new HashMap<String, String>()));
//        merchant.setMerchantType(1);
//        merchant.setState(1); // 1启用，2禁用
//        merchant.setUserId(merchant.getUserId() == null || merchant.getUserId() <= 0 ? -1 : merchant.getUserId());
//        long id = autoIdGenerator.get(ID_KEY);
//        merchant.setId(id);
//        if (StringUtils.isNotEmpty(merchant.getLogo())) {
//            merchant.setLogo(fileSDKClient.uidToUrl(merchant.getLogo()));
//        }
//        if (StringUtils.isNotEmpty(merchant.getImage())) {
//            merchant.setImage(fileSDKClient.uidToUrl(merchant.getImage()));
//        }
//        // merchant.setIsCertified(2);
//        // merchant.setIsExternalUrl(2);
//        // merchant.setIsNoReason(2);
//        // merchant.setIsSpecialService(2);
//        merchant.setRegistTime(new Date());
//        return merchantDao.add(merchant);
//    }
//
//    @Override
//    public Merchant get(Long id) {
//
//        return merchantDao.get(id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        return merchantDao.delete(id);
//    }
//
//    @Override
//    public boolean updateByAdmin(Merchant merchant) {
//
//        Merchant m = get(merchant.getId());
//        AssertUtil.assertNotNull(m, "商家不存在." + merchant.getName());
//        if (m.getName().equals(merchant.getName())) {
//            m.setName(merchant.getName());
//        } else {
//            int number = merchantDao.countByName(merchant.getName());
//            AssertUtil.assertTrue(number == 0, "商家已经存在." + merchant.getName());
//            m.setName(merchant.getName());
//        }
//        //
//        m.setClassifyId(merchant.getClassifyId());
//        m.setProvince(merchant.getProvince());
//        m.setCity(merchant.getCity());
//        m.setDistrict(merchant.getDistrict());
//        m.setAddress(merchant.getAddress());
//        m.setCommodityAuditing(merchant.getCommodityAuditing());
//        m.setIntroduction(merchant.getIntroduction());
//        m.setUserId(merchant.getUserId());
//        m.setFlagship(merchant.getFlagship());
//        m.setMerchantType(merchant.getMerchantType());
//        m.setValidDate(merchant.getValidDate());
//        m.setBuckle(merchant.getBuckle());
//        // m.setDistribution(merchant.getDistribution());
//        m.setUserId(merchant.getUserId());
//        if (StringUtils.isNotEmpty(merchant.getImage())) {
//            m.setImage(fileSDKClient.uidToUrl(merchant.getImage()));
//        }
//        m.setIsCertified(merchant.getIsCertified());
//        m.setIsExternalUrl(merchant.getIsExternalUrl());
//        m.setIsNoReason(merchant.getIsNoReason());
//        m.setIsSpecialService(merchant.getIsSpecialService());
//        m.setIsIncludePost(merchant.getIsIncludePost());
//        return update(m);
//    }
//
//    @Override
//    public boolean updateByMerchant(Merchant merchant) {
//
//        if (StringUtils.isNotEmpty(merchant.getLogo())) {
//            merchant.setLogo(fileSDKClient.uidToUrl(merchant.getLogo()));
//        }
//        Merchant m = get(merchant.getId());
//        AssertUtil.assertNotNull(m, "商家不存在." + merchant.getName());
//        m.setProvince(merchant.getProvince());
//        m.setCity(merchant.getCity());
//        m.setDistrict(merchant.getDistrict());
//        m.setAddress(merchant.getAddress());
//        m.setLatitude(merchant.getLatitude());
//        m.setLongitude(merchant.getLongitude());
//        m.setLogo(merchant.getLogo());
//        m.setIntroduction(merchant.getIntroduction());
//        m.setLinkman(merchant.getLinkman());
//        m.setPhone(merchant.getPhone());
//        m.setDetailIntroduction(merchant.getDetailIntroduction());
//        m.setUserId(merchant.getUserId());
//        m.setReceiveMobile(merchant.getReceiveMobile());
//        m.setIsIncludePost(merchant.getIsIncludePost());
//        return update(m);
//    }
//
//    private boolean update(Merchant merchant) {
//
//        return merchantDao.update(merchant);
//    }
//
//    @Override
//    public Page<Merchant> page(MerchantQuery query, int start, int count) {
//
//        return merchantDao.page(query, start, count);
//    }
//
//    public List<Merchant> listAll() {
//
//        return merchantDao.listAll();
//    }
//
//    @Override
//    public List<Merchant> list(List<Long> idList) {
//
//        return merchantDao.list(idList);
//    }
//
//    @Override
//    public List<Merchant> list(MerchantQuery query, int start, int count) {
//
//        List<Merchant> list = null;
//        switch (query.getType()) {
//        case 1:
//            list = merchantDao.listNew(query, start, count);
//            break;
//        case 2:
//            list = merchantDao.listHot(query, start, count);
//            break;
//        case 3:
//            list = merchantDao.listRecently(query, start, count);
//            break;
//        case 4:
//            list = merchantDao.listFavourable(query, start, count);
//            break;
//        default:
//            list = merchantDao.list(query, start, count);
//            break;
//        }
//        return list;
//    }
//
//    @Override
//    public int count(MerchantQuery query) {
//
//        int count = -1;
//        switch (query.getType()) {
//        case 1:
//            count = merchantDao.countNew(query);
//            break;
//        case 2:
//            count = merchantDao.countHot(query);
//            break;
//        case 3:
//            count = merchantDao.countRecently(query);
//            break;
//        case 4:
//            count = merchantDao.countFavourable(query);
//            break;
//        default:
//            count = merchantDao.count(query);
//            break;
//        }
//        return count;
//    }
//
//    @Override
//    public Page<Merchant> listNeedAudit(String keyword, int start, int size) {
//
//        return merchantDao.listNeedAudit(keyword, start, size);
//    }
//
//    @Override
//    public Page<Merchant> pageMerchant(MerchantQuery query, int start, int size) {
//
//        return merchantDao.pageMerchant(query, start, size);
//    }
//
//    @Override
//    public boolean disableMerchant(long id) {
//
//        return merchantDao.disableMerchant(id);
//    }
//
//    @Override
//    public boolean enableMerchant(long id) {
//
//        return merchantDao.enableMerchant(id);
//    }
//
//    @Override
//    public int count4DeleteByClassifyId(Long classifyId) {
//
//        return merchantDao.count4DeleteByClassifyId(classifyId);
//    }
//
//    @Override
//    public boolean updateMerchantNotify(long id, int notify) {
//
//        return merchantDao.updateMerchantNotify(id, notify);
//    }
//
//    @Override
//    public Merchant getByCode(String code) {
//
//        return merchantDao.getByCode(code);
//    }
//
//    @Override
//    public int countByName(String name) {
//
//        return merchantDao.countByName(name);
//    }
//}
