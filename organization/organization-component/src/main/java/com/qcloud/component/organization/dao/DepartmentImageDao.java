package com.qcloud.component.organization.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.organization.model.DepartmentImage;
import com.qcloud.component.organization.model.query.DepartmentImageQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface DepartmentImageDao extends ISimpleDao<DepartmentImage, Long> {

    public boolean add(DepartmentImage departmentImage);

    public DepartmentImage get(Long id);

    public boolean delete(Long id);

    public boolean update(DepartmentImage departmentImage);

    public List<DepartmentImage> list(List<Long> idList);

    public Map<Long, DepartmentImage> map(Set<Long> idSet);

    public Page<DepartmentImage> page(DepartmentImageQuery query, int start, int size);

    public List<DepartmentImage> listAll();

    public List<DepartmentImage> listByDepartmentId(Long departmentId);
}
