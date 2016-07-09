package com.qcloud.component.organization.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.organization.dao.DepartmentDao;
import com.qcloud.component.organization.model.Department;
import com.qcloud.component.organization.model.query.DepartmentQuery;

@Repository
public class DepartmentDaoMysqlImpl implements DepartmentDao {

    @Resource(name = "sqlOperator-organization")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(Department department) {

        return sqlOperator.insert("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.insert", department) == 1;
    }

    @Override
    public Department get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.delete", id) > 0;
    }

    @Override
    public boolean update(Department department) {

        return sqlOperator.update("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.update", department) > 0;
    }

    @Override
    public List<Department> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Department> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Department> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Department> list = sqlOperator.selectList("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.count4page", param);
        Page<Department> page = new Page<Department>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<Department> page(DepartmentQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("displayName", query.getDisplayName());
        List<Department> list = sqlOperator.selectList("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.count4query", param);
        Page<Department> page = new Page<Department>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<Department> listAll() {

        List<Department> list = sqlOperator.selectList("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.listAll");
        return list;
    }

    @Override
    public Department getByCode(String code) {

        return sqlOperator.selectOne("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.getByCode", code);
    }

    @Override
    public List<Department> listByParent(long parentId, String bsid) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("parentId", parentId);
        param.put("bsid", bsid);
        List<Department> list = sqlOperator.selectList("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.listByParent", param);
        return list;
    }

    @Override
    public Department getByManager(long manager) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("manager", manager);
        return sqlOperator.selectOne("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.getByManager", param);
    }

    @Override
    public List<Department> listChildrenByParent(DepartmentQuery query, String bsid, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("bsid", bsid);
        param.put("displayName", query.getDisplayName());
        List<Department> list = sqlOperator.selectList("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.listChildrenByParent", param);
        return list;
    }

    @Override
    public int countChildrenByParent(DepartmentQuery query, String bsid) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("bsid", bsid);
        param.put("displayName", query.getDisplayName());
        int total = sqlOperator.selectOne("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.countChildrenByParent", param);
        return total;
    }

    @Override
    public List<Department> listByAddress(DepartmentQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("province", query.getProvince());
        param.put("city", query.getCity());
        param.put("district", query.getDistrict());
        param.put("address", query.getAddress());
        List<Department> list = sqlOperator.selectList("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.listByAddress", param);
        return list;
    }

    @Override
    public int countByAddress(DepartmentQuery query) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("province", query.getProvince());
        param.put("city", query.getCity());
        param.put("district", query.getDistrict());
        param.put("address", query.getAddress());
        int total = sqlOperator.selectOne("com.qcloud.component.organization.dao.mysql.mapper.DepartmentMapper.countByAddress", param);
        return total;
    }
}
