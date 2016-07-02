package com.qcloud.component.commoditycenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.commoditycenter.dao.AttributeDefinitionDao;
import com.qcloud.component.commoditycenter.model.AttributeDefinition;
import com.qcloud.component.commoditycenter.model.query.AttributeDefinitionQuery;

@Repository
public class AttributeDefinitionDaoMysqlImpl implements AttributeDefinitionDao {

    @Resource(name = "sqlOperator-commoditycenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(AttributeDefinition attributeDefinition) {

        return sqlOperator.insert("com.qcloud.component.commoditycenter.dao.mysql.mapper.AttributeDefinitionMapper.insert", attributeDefinition) == 1;
    }

    @Override
    public AttributeDefinition get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.AttributeDefinitionMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.commoditycenter.dao.mysql.mapper.AttributeDefinitionMapper.delete", id) > 0;
    }

    @Override
    public boolean update(AttributeDefinition attributeDefinition) {

        return sqlOperator.update("com.qcloud.component.commoditycenter.dao.mysql.mapper.AttributeDefinitionMapper.update", attributeDefinition) > 0;
    }

    @Override
    public List<AttributeDefinition> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, AttributeDefinition> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<AttributeDefinition> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<AttributeDefinition> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.AttributeDefinitionMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.AttributeDefinitionMapper.count4page", param);
        Page<AttributeDefinition> page = new Page<AttributeDefinition>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<AttributeDefinition> page(AttributeDefinitionQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("type", query.getType());
        List<AttributeDefinition> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.AttributeDefinitionMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.AttributeDefinitionMapper.count4query", param);
        Page<AttributeDefinition> page = new Page<AttributeDefinition>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<AttributeDefinition> listAll() {

        List<AttributeDefinition> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.AttributeDefinitionMapper.listAll");
        return list;
    }

    @Override
    public AttributeDefinition getByCode(String code) {

        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.AttributeDefinitionMapper.getByCode", code);
    }

    @Override
    public Page<AttributeDefinition> list4Select(AttributeDefinitionQuery query, int start, int count) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("name", query.getName());
        param.put("type", query.getType());
        List<AttributeDefinition> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.AttributeDefinitionMapper.list4Select", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.AttributeDefinitionMapper.count4Select", param);
        Page<AttributeDefinition> page = new Page<AttributeDefinition>();
        page.setCount(total);
        page.setData(list);
        return page;
    }
}
