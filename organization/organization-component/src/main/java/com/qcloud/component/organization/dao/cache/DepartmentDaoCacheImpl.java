package com.qcloud.component.organization.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.organization.dao.DepartmentDao;
import com.qcloud.component.organization.model.Department;
import com.qcloud.component.organization.model.query.DepartmentQuery;

@Repository
public class DepartmentDaoCacheImpl implements DepartmentDao {

    @Autowired
    private DepartmentDao departmentDaoMysqlImpl;

//    @Autowired
//    private DepartmentDao departmentDaoRedisImpl;

    @Override
    public boolean add(Department department) {

        return departmentDaoMysqlImpl.add(department);
    }

    @Override
    public Department get(Long id) {

        return departmentDaoMysqlImpl.get(id);
        // return CacheLoader.get(departmentDaoRedisImpl, departmentDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return departmentDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(Department department) {

        return departmentDaoMysqlImpl.update(department);
    }

    @Override
    public List<Department> list(List<Long> idList) {

        return CacheLoader.list(departmentDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, Department> map(Set<Long> idSet) {

        return CacheLoader.map(departmentDaoMysqlImpl, idSet);
    }

    @Override
    public Page<Department> page(int start, int count) {

        return departmentDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<Department> page(DepartmentQuery query, int start, int count) {

        return departmentDaoMysqlImpl.page(query, start, count);
    }

    public List<Department> listAll() {

        return departmentDaoMysqlImpl.listAll();
    }

    @Override
    public Department getByCode(String code) {

        return departmentDaoMysqlImpl.getByCode(code);
    }

    @Override
    public List<Department> listByParent(long parentId, String bsid) {

        return departmentDaoMysqlImpl.listByParent(parentId, bsid);
    }

    @Override
    public Department getByManager(long manager) {

        return departmentDaoMysqlImpl.getByManager(manager);
    }

    @Override
    public List<Department> listChildrenByParent(DepartmentQuery query,String bsid,int start, int count) {

        return departmentDaoMysqlImpl.listChildrenByParent(query,bsid,start,count);
    }

    @Override
    public int countChildrenByParent(DepartmentQuery query,String bsid) {

        return departmentDaoMysqlImpl.countChildrenByParent(query,bsid);
    }
}
