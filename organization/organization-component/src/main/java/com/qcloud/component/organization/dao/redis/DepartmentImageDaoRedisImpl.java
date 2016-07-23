package com.qcloud.component.organization.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.organization.dao.DepartmentImageDao;
import com.qcloud.component.organization.model.DepartmentImage;
import com.qcloud.component.organization.model.query.DepartmentImageQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class DepartmentImageDaoRedisImpl implements DepartmentImageDao {

    // @Resource(name = "redis-organization")
    // private Redis redis;
    @Override
    public boolean add(DepartmentImage departmentImage) {

        throw new NotImplementedException();
    }

    @Override
    public DepartmentImage get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(DepartmentImage departmentImage) {

        throw new NotImplementedException();
    }

    @Override
    public List<DepartmentImage> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, DepartmentImage> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<DepartmentImage> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<DepartmentImage> page(DepartmentImageQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<DepartmentImage> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<DepartmentImage> listByDepartmentId(Long departmentId) {

        throw new NotImplementedException();
    }
}
