package com.qcloud.component.goods.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseAttributeDao;
import com.qcloud.component.goods.model.MerchandiseAttribute;
import com.qcloud.component.goods.service.MerchandiseAttributeService;
import com.qcloud.component.goods.model.query.MerchandiseAttributeQuery;
@Service
public class MerchandiseAttributeServiceImpl implements MerchandiseAttributeService {
    @Autowired
    private MerchandiseAttributeDao merchandiseAttributeDao;
    @Autowired
    private AutoIdGenerator         autoIdGenerator;
    private static final String     ID_KEY = "goods_merchandise_attribute";

    @Override
    public boolean add(MerchandiseAttribute merchandiseAttribute) {
        long id = autoIdGenerator.get(ID_KEY);
        merchandiseAttribute.setId(id);
        return merchandiseAttributeDao.add(merchandiseAttribute);
    }

    @Override
    public MerchandiseAttribute get(Long id) {
        return merchandiseAttributeDao.get(id);
    }

    @Override
    public boolean delete(Long id) {
        return merchandiseAttributeDao.delete(id);
    }

    @Override
    public boolean update(MerchandiseAttribute merchandiseAttribute) {
        return merchandiseAttributeDao.update(merchandiseAttribute);
    }

    @Override
    public Page<MerchandiseAttribute> page(MerchandiseAttributeQuery query, int start, int count) {
        return merchandiseAttributeDao.page(query, start, count);
    }

    public List<MerchandiseAttribute> listAll() {
        return merchandiseAttributeDao.listAll();
    }

    @Override
    public List<MerchandiseAttribute> listByMerchandise(long merchandiseId) {
        return merchandiseAttributeDao.listByMerchandise(merchandiseId);
    }

    @Transactional
    @Override
    public void set(Long merchandiseId, List<MerchandiseAttribute> list) {
        for (MerchandiseAttribute merchandiseAttribute : list) {
            merchandiseAttribute.setMerchandiseId(merchandiseId);
        }
        List<MerchandiseAttribute> maList = listByMerchandise(merchandiseId);
        for (MerchandiseAttribute newMerchandiseAttribute : list) {
            MerchandiseAttribute ma = getInList(maList, newMerchandiseAttribute);
            if (ma == null) {
                add(newMerchandiseAttribute);
            } else {
                ma.setValue(newMerchandiseAttribute.getValue());
                update(ma);
            }
        }
    }

    private MerchandiseAttribute getInList(List<MerchandiseAttribute> list, MerchandiseAttribute merchandiseAttribute) {
        for (MerchandiseAttribute existMerchandiseAttribute : list) {
            if (existMerchandiseAttribute.getAttributeId() == merchandiseAttribute.getAttributeId() && existMerchandiseAttribute.getMerchandiseId() == existMerchandiseAttribute.getMerchandiseId()) {
                return existMerchandiseAttribute;
            }
        }
        return null;
    }
}
