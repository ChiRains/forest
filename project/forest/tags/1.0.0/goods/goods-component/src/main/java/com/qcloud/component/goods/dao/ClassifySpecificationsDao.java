package com.qcloud.component.goods.dao;

import com.qcloud.component.goods.model.ClassifySpecifications;
import com.qcloud.component.goods.model.query.ClassifySpecificationsQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 商城商品分类与商品规格关系
 *
 * @author Zoro
 */
public interface ClassifySpecificationsDao extends ISimpleDao<ClassifySpecifications, Long> {

    public boolean add(ClassifySpecifications classifySpecifications);

    public ClassifySpecifications get(Long id);

    public boolean delete(Long id);

    public boolean update(ClassifySpecifications classifySpecifications);

    public List<ClassifySpecifications> list(List<Long> idList);

    public Map<Long, ClassifySpecifications> map(Set<Long> idSet);

    public Page<ClassifySpecifications> page(ClassifySpecificationsQuery query, int start, int size);

    public List<ClassifySpecifications> listAll();

    List<ClassifySpecifications> listByClassify(Long classifyId);

    public ClassifySpecifications get(HashMap where);

    public List<ClassifySpecifications> list(HashMap where);

    public Page<ClassifySpecifications> page(HashMap where, int start, int size);
}
