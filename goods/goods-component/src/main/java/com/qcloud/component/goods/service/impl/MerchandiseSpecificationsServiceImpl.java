package com.qcloud.component.goods.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.goods.dao.MerchandiseSpecificationsDao;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsQuery;
import com.qcloud.component.goods.service.MerchandiseSpecificationsService;
import com.qcloud.component.publicdata.IntKeyValue;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.StringUtil;

@Service
public class MerchandiseSpecificationsServiceImpl implements MerchandiseSpecificationsService {

    @Autowired
    private MerchandiseSpecificationsDao merchandiseSpecificationsDao;

    @Autowired
    private AutoIdGenerator              autoIdGenerator;

    private static final String          ID_KEY = "goods_merchandise_specifications";

    @Override
    public boolean add(MerchandiseSpecifications merchandiseSpecifications) {

        long id = autoIdGenerator.get(ID_KEY);
        merchandiseSpecifications.setId(id);
        return merchandiseSpecificationsDao.add(merchandiseSpecifications);
    }

    @Override
    public MerchandiseSpecifications get(Long id) {

        return merchandiseSpecificationsDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseSpecificationsDao.delete(id);
    }

    @Override
    public boolean update(MerchandiseSpecifications merchandiseSpecifications) {

        return merchandiseSpecificationsDao.update(merchandiseSpecifications);
    }

    @Override
    public Page<MerchandiseSpecifications> page(MerchandiseSpecificationsQuery query, int start, int count) {

        return merchandiseSpecificationsDao.page(query, start, count);
    }

    public List<MerchandiseSpecifications> listAll() {

        return merchandiseSpecificationsDao.listAll();
    }

    @Transactional
    @Override
    public boolean setMerchandiseSpecifications(Long merchandiseId, List<MerchandiseSpecifications> list) {

        List<MerchandiseSpecifications> existList = listByMerchandise(merchandiseId);
        for (MerchandiseSpecifications merchandiseSpecifications : existList) {
            delete(merchandiseSpecifications.getId());
        }
        for (MerchandiseSpecifications merchandiseSpecifications : list) {
            merchandiseSpecifications.setMerchandiseId(merchandiseId);
            add(merchandiseSpecifications);
        }
        return false;
    }

    @Override
    public List<MerchandiseSpecifications> listByMerchandise(Long merchandiseId) {

        return merchandiseSpecificationsDao.listByMerchandise(merchandiseId);
    }

    @Override
    public MerchandiseSpecifications get(HashMap where) {

        return merchandiseSpecificationsDao.get(where);
    }

    @Override
    public List<MerchandiseSpecifications> list(HashMap where) {

        return merchandiseSpecificationsDao.list(where);
    }

    @Override
    public Page<MerchandiseSpecifications> page(HashMap where, int start, int size) {

        return merchandiseSpecificationsDao.page(where, start, size);
    }
    
    public List<MerchandiseSpecifications> getSpecifications(List<MerchandiseSpecifications> list, IntKeyValue[] intKeyValues) {

        int dimensionNumber = intKeyValues.length;
        Map<Long, List<MerchandiseSpecifications>> lm = new HashMap<Long, List<MerchandiseSpecifications>>();
        for (MerchandiseSpecifications merchandiseSpecifications : list) {
            if (merchandiseSpecifications.getDimensionNumber() == dimensionNumber) {
                List<MerchandiseSpecifications> ls = lm.get(merchandiseSpecifications.getUnifiedMerchandiseId());
                if (ls == null) {
                    ls = new ArrayList<MerchandiseSpecifications>();
                    lm.put(merchandiseSpecifications.getUnifiedMerchandiseId(), ls);
                }
                ls.add(merchandiseSpecifications);
            }
        }
        List<MerchandiseSpecifications> rs = null;
        for (List<MerchandiseSpecifications> ls : lm.values()) {
            int identical = 0;
            for (MerchandiseSpecifications merchandiseSpecifications : ls) {
                for (IntKeyValue intKeyValue : intKeyValues) {
                    if (intKeyValue.getKey() == merchandiseSpecifications.getAttributeId() && intKeyValue.getValue().equals(merchandiseSpecifications.getValue())) {
                        identical++;
                        break;
                    }
                }
            }
            if (identical == intKeyValues.length) {
                rs = ls;
                break;
            }
        }
        return rs;
    }

    @Override
    public List<MerchandiseSpecifications> listByUnifiedMerchandise(Long merchandiseId) {
        return merchandiseSpecificationsDao.listByUnifiedMerchandise(merchandiseId);
    }

//    @Override
//    public MerchandiseSpecifications get(long merchandiseId, long attributeId0, String value0, long attributeId1, String value1, long attributeId2, String value2) {
//
//        MerchandiseSpecifications merchandiseSpecifications = null;
//        // 缓存
//        List<MerchandiseSpecifications> list = listByMerchandise(merchandiseId);
//        for (MerchandiseSpecifications ms : list) {
//            if (isSpecifications(ms, attributeId0, value0, attributeId1, value1, attributeId2, value2)) {
//                merchandiseSpecifications = ms;
//                break;
//            }
//        }
//        return merchandiseSpecifications;
//    }
//
//    private boolean isSpecifications(MerchandiseSpecifications ms, long attributeId0, String value0, long attributeId1, String value1, long attributeId2, String value2) {
//
//        ms.setValue0(StringUtil.nullToEmpty(ms.getValue0()));
//        ms.setValue1(StringUtil.nullToEmpty(ms.getValue1()));
//        ms.setValue2(StringUtil.nullToEmpty(ms.getValue2()));
//        value0 = StringUtil.nullToEmpty(value0);
//        value1 = StringUtil.nullToEmpty(value1);
//        value2 = StringUtil.nullToEmpty(value2);
////        System.out.println(ms.getAttributeId0() == attributeId0);
////        System.out.println(ms.getValue0().equals(value0));
////        System.out.println(ms.getAttributeId1() == attributeId1);
////        System.out.println(ms.getValue1().equals(value1));
////        
//        boolean r0 = ms.getAttributeId0() == attributeId0 && ms.getValue0().equals(value0) && ms.getAttributeId1() == attributeId1 && ms.getValue1().equals(value1) && ms.getAttributeId2() == attributeId2 && ms.getValue2().equals(value2);
//        boolean r1 = ms.getAttributeId0() == attributeId0 && ms.getValue0().equals(value0) && ms.getAttributeId1() == attributeId2 && ms.getValue1().equals(value2) && ms.getAttributeId2() == attributeId1 && ms.getValue2().equals(value1);
//        boolean r2 = ms.getAttributeId0() == attributeId1 && ms.getValue0().equals(value1) && ms.getAttributeId1() == attributeId0 && ms.getValue1().equals(value0) && ms.getAttributeId2() == attributeId2 && ms.getValue2().equals(value2);
//        boolean r3 = ms.getAttributeId0() == attributeId1 && ms.getValue0().equals(value1) && ms.getAttributeId1() == attributeId2 && ms.getValue1().equals(value2) && ms.getAttributeId2() == attributeId0 && ms.getValue2().equals(value0);
//        boolean r4 = ms.getAttributeId0() == attributeId2 && ms.getValue0().equals(value2) && ms.getAttributeId1() == attributeId0 && ms.getValue1().equals(value0) && ms.getAttributeId2() == attributeId1 && ms.getValue2().equals(value1);
//        boolean r5 = ms.getAttributeId0() == attributeId2 && ms.getValue0().equals(value2) && ms.getAttributeId1() == attributeId1 && ms.getValue1().equals(value1) && ms.getAttributeId2() == attributeId0 && ms.getValue2().equals(value0);
//        return r0 || r1 || r2 || r3 || r4 || r5;
//    }
}
