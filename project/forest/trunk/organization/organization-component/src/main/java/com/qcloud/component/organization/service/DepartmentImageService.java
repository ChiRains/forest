package com.qcloud.component.organization.service;

import java.util.List;
import com.qcloud.component.organization.model.DepartmentImage;
import com.qcloud.component.organization.model.query.DepartmentImageQuery;
import com.qcloud.pirates.data.Page;

public interface DepartmentImageService {

    public boolean add(DepartmentImage departmentImage);

    public DepartmentImage get(Long id);

    public boolean delete(Long id);

    public boolean update(DepartmentImage departmentImage);

    public Page<DepartmentImage> page(DepartmentImageQuery query, int start, int count);

    public List<DepartmentImage> listAll();

    public List<DepartmentImage> listByDepartmentId(Long departmentId);
}
