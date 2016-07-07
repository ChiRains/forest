package com.qcloud.component.organization.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.organization.model.Department;
import com.qcloud.component.organization.model.query.DepartmentQuery;

public interface DepartmentDao extends ISimpleDao<Department, Long> {

    public boolean add(Department department);

    public Department get(Long id);

    public boolean delete(Long id);

    public boolean update(Department department);

    public List<Department> list(List<Long> idList);

    public Map<Long, Department> map(Set<Long> idSet);

    public Page<Department> page(DepartmentQuery query, int start, int size);

    public List<Department> listAll();

    public Department getByCode(String code);

    public List<Department> listByParent(long parentId, String bsid);
    
    public Department getByManager(long manager);
    
    public List<Department> listChildrenByParent(DepartmentQuery query,String bsid,int start, int count);
    
    public int countChildrenByParent(DepartmentQuery query,String bsid);
}
