package com.qcloud.component.commoditycenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.commoditycenter.model.Enumeration;
import com.qcloud.component.commoditycenter.model.query.EnumerationQuery;

/**
 * 枚举
 * 
 * @author Zoro
 *
 */
public interface EnumerationDao extends ISimpleDao<Enumeration, Long> {

    public boolean add(Enumeration enumeration);

    public Enumeration get(Long id);

    public boolean delete(Long id);

    public boolean deleteByName(String name);

    public boolean update(Enumeration enumeration);

    public List<Enumeration> list(List<Long> idList);

    public Map<Long, Enumeration> map(Set<Long> idSet);

    public Page<Enumeration> page(EnumerationQuery query, int start, int size);

    public List<Enumeration> listAll();

    List<Enumeration> listByName(String name);

    boolean existByName(String name);

    List<String> listNames();
}
