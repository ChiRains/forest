package com.qcloud.component.organization.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.organization.dao.DepartmentImageDao;
import com.qcloud.component.organization.model.DepartmentImage;
import com.qcloud.component.organization.model.query.DepartmentImageQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class DepartmentImageDaoCacheImpl implements DepartmentImageDao {

    @Autowired
    private DepartmentImageDao departmentImageDaoMysqlImpl;

    @Autowired
    private DepartmentImageDao departmentImageDaoRedisImpl;

    @Override
    public boolean add(DepartmentImage departmentImage) {

        return departmentImageDaoMysqlImpl.add(departmentImage);
    }

    @Override
    public DepartmentImage get(Long id) {

        return departmentImageDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return departmentImageDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(DepartmentImage departmentImage) {

        return departmentImageDaoMysqlImpl.update(departmentImage);
    }

    @Override
    public List<DepartmentImage> list(List<Long> idList) {

        return CacheLoader.list(departmentImageDaoRedisImpl, departmentImageDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, DepartmentImage> map(Set<Long> idSet) {

        return CacheLoader.map(departmentImageDaoRedisImpl, departmentImageDaoMysqlImpl, idSet);
    }

    @Override
    public Page<DepartmentImage> page(int start, int count) {

        return departmentImageDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<DepartmentImage> page(DepartmentImageQuery query, int start, int count) {

        return departmentImageDaoMysqlImpl.page(query, start, count);
    }

    public List<DepartmentImage> listAll() {

        return departmentImageDaoMysqlImpl.listAll();
    }

    @Override
    public List<DepartmentImage> listByDepartmentId(Long departmentId) {

        return departmentImageDaoMysqlImpl.listByDepartmentId(departmentId);
    }
}
