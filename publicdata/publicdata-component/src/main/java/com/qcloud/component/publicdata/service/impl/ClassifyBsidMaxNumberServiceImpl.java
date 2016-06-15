package com.qcloud.component.publicdata.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.publicdata.dao.ClassifyBsidMaxNumberDao;
import com.qcloud.component.publicdata.model.ClassifyBsidMaxNumber;
import com.qcloud.component.publicdata.service.ClassifyBsidMaxNumberService;
import com.qcloud.component.publicdata.model.query.ClassifyBsidMaxNumberQuery;

@Service
public class ClassifyBsidMaxNumberServiceImpl implements ClassifyBsidMaxNumberService {

    @Autowired
    private ClassifyBsidMaxNumberDao classifyBsidMaxNumberDao;

    @Autowired
    private AutoIdGenerator          autoIdGenerator;

    private static final String      ID_KEY = "publicdata_classify_bsid_max_number";

    @Override
    public boolean add(ClassifyBsidMaxNumber classifyBsidMaxNumber) {

        long id = autoIdGenerator.get(ID_KEY);
        classifyBsidMaxNumber.setId(id);
        return classifyBsidMaxNumberDao.add(classifyBsidMaxNumber);
    }

    @Override
    public ClassifyBsidMaxNumber get(Long id) {

        return classifyBsidMaxNumberDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return classifyBsidMaxNumberDao.delete(id);
    }

    @Override
    public boolean update(ClassifyBsidMaxNumber classifyBsidMaxNumber) {

        return classifyBsidMaxNumberDao.update(classifyBsidMaxNumber);
    }

    @Override
    public Page<ClassifyBsidMaxNumber> page(ClassifyBsidMaxNumberQuery query, int start, int count) {

        return classifyBsidMaxNumberDao.page(query, start, count);
    }

    public List<ClassifyBsidMaxNumber> listAll() {

        return classifyBsidMaxNumberDao.listAll();
    }

    @Override
    public int calculateNextBsid(long parentClassifyId, long type) {

        ClassifyBsidMaxNumber classifyBsidMaxNumber = classifyBsidMaxNumberDao.getByParentClassify(parentClassifyId, type);
        if (classifyBsidMaxNumber == null) {
            classifyBsidMaxNumber = new ClassifyBsidMaxNumber();
            classifyBsidMaxNumber.setMaxNumber(1);
            classifyBsidMaxNumber.setType(type);
            classifyBsidMaxNumber.setParentClassifyId(parentClassifyId);
            long id = autoIdGenerator.get(ID_KEY);
            classifyBsidMaxNumber.setId(id);
        }
        while (true) {
            classifyBsidMaxNumberDao.lockNextBsid(classifyBsidMaxNumber);
            ClassifyBsidMaxNumber lock = classifyBsidMaxNumberDao.getByParentClassify(parentClassifyId, type);
            if (classifyBsidMaxNumber.getMaxNumber() + 1 == lock.getMaxNumber()) {
                return lock.getMaxNumber();
            } else {
                classifyBsidMaxNumber = lock;
            }
        }
    }
}
