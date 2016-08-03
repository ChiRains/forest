package com.qcloud.component.organization.service;

import java.util.List;
import com.qcloud.component.organization.model.Department;
import com.qcloud.component.organization.model.query.DepartmentQuery;
import com.qcloud.pirates.data.Page;

public interface DepartmentService {

    public boolean add(Department department);

    public Department get(Long id);

    Department getByCode(String code);

    public boolean delete(Long id);

    public boolean update(Department department);

    public Page<Department> page(DepartmentQuery query, int start, int count);

    List<Department> listByParent(long parentId);

    public List<Department> listAll();

    public Department getByManager(long manager);

    public List<Department> listChildrenByParent(DepartmentQuery query, String bsid, int start, int count);

    public int countChildrenByParent(DepartmentQuery query, String bsid);

    public List<Department> listByAddress(DepartmentQuery query, int start, int count);

    public int countByAddress(DepartmentQuery query);

    public List<Department> listNearby(double latitude, double longitude);
}
