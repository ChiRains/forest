package com.qcloud.component.organization.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.organization.dao.DepartmentImageDao;
import com.qcloud.component.organization.model.DepartmentImage;
import com.qcloud.component.organization.model.query.DepartmentImageQuery;
import com.qcloud.component.organization.service.DepartmentImageService;
import com.qcloud.pirates.data.Page;

@Service
public class DepartmentImageServiceImpl implements DepartmentImageService {

    @Autowired
    private DepartmentImageDao  departmentImageDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "organization_department_image";

    @Override
    public boolean add(DepartmentImage departmentImage) {

        long id = autoIdGenerator.get(ID_KEY);
        departmentImage.setId(id);
        return departmentImageDao.add(departmentImage);
    }

    @Override
    public DepartmentImage get(Long id) {

        return departmentImageDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return departmentImageDao.delete(id);
    }

    @Override
    public boolean update(DepartmentImage departmentImage) {

        return departmentImageDao.update(departmentImage);
    }

    @Override
    public Page<DepartmentImage> page(DepartmentImageQuery query, int start, int count) {

        return departmentImageDao.page(query, start, count);
    }

    public List<DepartmentImage> listAll() {

        return departmentImageDao.listAll();
    }

    @Override
    public List<DepartmentImage> listByDepartmentId(Long departmentId) {

        return departmentImageDao.listByDepartmentId(departmentId);
    }
}
