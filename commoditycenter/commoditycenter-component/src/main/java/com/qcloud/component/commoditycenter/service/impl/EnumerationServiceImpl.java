package com.qcloud.component.commoditycenter.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.component.commoditycenter.dao.EnumerationDao;
import com.qcloud.component.commoditycenter.exception.CommoditycenterException;
import com.qcloud.component.commoditycenter.model.Enumeration;
import com.qcloud.component.commoditycenter.service.EnumerationService;
import com.qcloud.component.commoditycenter.model.query.EnumerationQuery;
@Service
public class EnumerationServiceImpl implements EnumerationService {
    @Autowired
    private EnumerationDao      enumerationDao;
    @Autowired
    private AutoIdGenerator     autoIdGenerator;
    private static final String ID_KEY = "commoditycenter_enumeration";

    @Transactional
    @Override
    public boolean add(Enumeration enumeration) {
        boolean exist = enumerationDao.existByName(enumeration.getName());
        if (exist) {
            throw new CommoditycenterException("枚举名称已经存在:" + enumeration.getName());
        }
        AssertUtil.assertNotEmpty(enumeration.getValue(), "枚举值不能为空.");
        String value = enumeration.getValue();
        String[] values = value.split(",");
        for (String str : values) {
            Enumeration e = new Enumeration();
            e.setName(enumeration.getName());
            e.setValue(str);
            long id = autoIdGenerator.get(ID_KEY);
            e.setId(id);
            enumerationDao.add(e);
        }
        return true;
    }

    @Override
    public Enumeration get(Long id) {
        return enumerationDao.get(id);
    }

    @Override
    public boolean delete(Long id) {
        return enumerationDao.delete(id);
    }

    @Transactional
    @Override
    public boolean update(Enumeration enumeration) {
        boolean exist = enumerationDao.existByName(enumeration.getName());
        if (!exist) {
            throw new CommoditycenterException("枚举名称不存在:" + enumeration.getName());
        }
        enumerationDao.deleteByName(enumeration.getName());
        add(enumeration);        
        return true;
    }

    @Override
    public Page<Enumeration> page(EnumerationQuery query, int start, int count) {
        return enumerationDao.page(query, start, count);
    }

    public List<Enumeration> listAll() {
        return enumerationDao.listAll();
    }

    @Override
    public List<Enumeration> listByName(String name) {
        return enumerationDao.listByName(name);
    }

    @Override
    public boolean existByName(String name) {     
        return enumerationDao.existByName(name);
    }

    @Override
    public List<String> listNames() {
        return enumerationDao.listNames();
    }
}
