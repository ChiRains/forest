package com.qcloud.component.organization.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.organization.model.Department;
import com.qcloud.component.organization.model.query.DepartmentQuery;

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
    
    public List<Department> listChildrenByParent(DepartmentQuery query,String bsid,int start, int count);
    
    public int countChildrenByParent(DepartmentQuery query,String bsid);
}
