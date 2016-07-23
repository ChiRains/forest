package com.qcloud.component.organization.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.organization.dao.DepartmentImageDao;
import com.qcloud.component.organization.model.DepartmentImage;
import com.qcloud.component.organization.model.query.DepartmentImageQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class DepartmentImageDaoMysqlImpl implements DepartmentImageDao {

    @Resource(name = "sqlOperator-organization")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(DepartmentImage departmentImage) {

        return sqlOperator.insert("com.qcloud.component.organization.dao.mysql.mapper.DepartmentImageMapper.insert", departmentImage) == 1;
    }

    @Override
    public DepartmentImage get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.organization.dao.mysql.mapper.DepartmentImageMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.organization.dao.mysql.mapper.DepartmentImageMapper.delete", id) > 0;
    }

    @Override
    public boolean update(DepartmentImage departmentImage) {

        return sqlOperator.update("com.qcloud.component.organization.dao.mysql.mapper.DepartmentImageMapper.update", departmentImage) > 0;
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

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<DepartmentImage> list = sqlOperator.selectList("com.qcloud.component.organization.dao.mysql.mapper.DepartmentImageMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.organization.dao.mysql.mapper.DepartmentImageMapper.count4page", param);
        Page<DepartmentImage> page = new Page<DepartmentImage>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<DepartmentImage> page(DepartmentImageQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<DepartmentImage> list = sqlOperator.selectList("com.qcloud.component.organization.dao.mysql.mapper.DepartmentImageMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.organization.dao.mysql.mapper.DepartmentImageMapper.count4query", param);
        Page<DepartmentImage> page = new Page<DepartmentImage>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<DepartmentImage> listAll() {

        List<DepartmentImage> list = sqlOperator.selectList("com.qcloud.component.organization.dao.mysql.mapper.DepartmentImageMapper.listAll");
        return list;
    }

    @Override
    public List<DepartmentImage> listByDepartmentId(Long departmentId) {

        return sqlOperator.selectList("com.qcloud.component.organization.dao.mysql.mapper.DepartmentImageMapper.listByDepartmentId", departmentId);
    }
}
