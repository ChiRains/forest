package com.qcloud.component.commoditycenter.dao.mysql;

import com.qcloud.component.commoditycenter.dao.ClassifySpecificationsDao;
import com.qcloud.component.commoditycenter.model.ClassifySpecifications;
import com.qcloud.component.commoditycenter.model.query.ClassifySpecificationsQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class ClassifySpecificationsDaoMysqlImpl implements ClassifySpecificationsDao {

    @Resource(name = "sqlOperator-commoditycenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(ClassifySpecifications classifySpecifications) {
        return sqlOperator.insert("com.qcloud.component.commoditycenter.dao.mysql.mapper.ClassifySpecificationsMapper.insert", classifySpecifications) == 1;
    }

    @Override
    public ClassifySpecifications get(Long id) {
        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.ClassifySpecificationsMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {
        return sqlOperator.delete("com.qcloud.component.commoditycenter.dao.mysql.mapper.ClassifySpecificationsMapper.delete", id) > 0;
    }

    @Override
    public boolean update(ClassifySpecifications classifySpecifications) {
        return sqlOperator.update("com.qcloud.component.commoditycenter.dao.mysql.mapper.ClassifySpecificationsMapper.update", classifySpecifications) > 0;
    }

    @Override
    public List<ClassifySpecifications> list(List<Long> idList) {
        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ClassifySpecifications> map(Set<Long> idSet) {
        throw new NotImplementedException();
    }

    @Override
    public Page<ClassifySpecifications> page(int start, int count) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);

        List<ClassifySpecifications> list = sqlOperator.selectList(
                "com.qcloud.component.commoditycenter.dao.mysql.mapper.ClassifySpecificationsMapper.list4page",
                param);
        int total = sqlOperator.selectOne(
                "com.qcloud.component.commoditycenter.dao.mysql.mapper.ClassifySpecificationsMapper.count4page",
                param);
        Page<ClassifySpecifications> page = new Page<ClassifySpecifications>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<ClassifySpecifications> page(ClassifySpecificationsQuery query, int start, int count) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);

        List<ClassifySpecifications> list = sqlOperator.selectList(
                "com.qcloud.component.commoditycenter.dao.mysql.mapper.ClassifySpecificationsMapper.list4query",
                param);
        int total = sqlOperator.selectOne(
                "com.qcloud.component.commoditycenter.dao.mysql.mapper.ClassifySpecificationsMapper.count4query",
                param);
        Page<ClassifySpecifications> page = new Page<ClassifySpecifications>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<ClassifySpecifications> listAll() {
        List<ClassifySpecifications> list = sqlOperator.selectList(
                "com.qcloud.component.commoditycenter.dao.mysql.mapper.ClassifySpecificationsMapper.listAll");
        return list;
    }

    @Override
    public List<ClassifySpecifications> listByClassify(Long classifyId) {
        List<ClassifySpecifications> list = sqlOperator.selectList(
                "com.qcloud.component.commoditycenter.dao.mysql.mapper.ClassifySpecificationsMapper.listByClassify", classifyId);
        return list;

    }

    @Override
    public List<ClassifySpecifications> list(HashMap where) {
        return sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.ClassifySpecificationsMapper.list", where);
    }

    @Override
    public ClassifySpecifications get(HashMap where) {
        where.put("start", 0);
        where.put("count", 1);
        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.ClassifySpecificationsMapper.list", where);
    }

    @Override
    public Page<ClassifySpecifications> page(HashMap where, int start, int size) {
        where.put("start", start);
        where.put("count", size);

        List<ClassifySpecifications> list = sqlOperator.selectList(
                "com.qcloud.component.commoditycenter.dao.mysql.mapper.ClassifySpecificationsMapper.list",
                where);
        int total = sqlOperator.selectOne(
                "com.qcloud.component.commoditycenter.dao.mysql.mapper.ClassifySpecificationsMapper.count",
                where);
        Page<ClassifySpecifications> classifySpecificationsPage = new Page<ClassifySpecifications>();
        classifySpecificationsPage.setCount(total);
        classifySpecificationsPage.setData(list);
        return classifySpecificationsPage;
    }


}

