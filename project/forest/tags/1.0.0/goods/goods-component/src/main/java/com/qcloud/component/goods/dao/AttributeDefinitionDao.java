package com.qcloud.component.goods.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.goods.model.AttributeDefinition;
import com.qcloud.component.goods.model.query.AttributeDefinitionQuery;

/**
 * 商品属性定义,商品属性可以指定一个枚举供选择
 * 
 * @author Zoro
 *
 */
public interface AttributeDefinitionDao extends ISimpleDao<AttributeDefinition, Long> {

    public boolean add(AttributeDefinition attributeDefinition);

    public AttributeDefinition get(Long id);

    public AttributeDefinition getByCode(String code);

    public boolean delete(Long id);

    public boolean update(AttributeDefinition attributeDefinition);

    public List<AttributeDefinition> list(List<Long> idList);

    public Map<Long, AttributeDefinition> map(Set<Long> idSet);

    public Page<AttributeDefinition> page(AttributeDefinitionQuery query, int start, int size);

    public List<AttributeDefinition> listAll();
    
    public Page<AttributeDefinition> list4Select(AttributeDefinitionQuery query, int start, int size);
}
