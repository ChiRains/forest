package com.qcloud.component.marketing.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.marketing.model.FullReduces;
import com.qcloud.component.marketing.model.query.FullReducesQuery;

public interface FullReducesDao extends ISimpleDao<FullReduces, Long> {

    public boolean add(FullReduces fullReduces);

    public FullReduces get(Long id);

    public boolean delete(Long id);

    public boolean update(FullReduces fullReduces);

    public List<FullReduces> list(List<Long> idList);

    public Map<Long, FullReduces> map(Set<Long> idSet);

    public Page<FullReduces> page(FullReducesQuery query, int start, int size);

    public List<FullReduces> listAll();

    public List<FullReduces> listCurrentReduces();
}
