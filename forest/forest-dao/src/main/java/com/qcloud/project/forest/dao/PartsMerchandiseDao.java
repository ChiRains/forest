package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.PartsMerchandise;
import com.qcloud.project.forest.model.query.PartsMerchandiseQuery;

public interface PartsMerchandiseDao extends ISimpleDao<PartsMerchandise, Long> {

    public boolean add(PartsMerchandise partsMerchandise);

    public PartsMerchandise get(Long id);

    public boolean delete(Long id);

    public boolean update(PartsMerchandise partsMerchandise);

    public List<PartsMerchandise> list(List<Long> idList);

    public Map<Long, PartsMerchandise> map(Set<Long> idSet);

    public Page<PartsMerchandise> page(PartsMerchandiseQuery query, int start, int size);

    public List<PartsMerchandise> listAll();

    public List<PartsMerchandise> listByClassifyId(Long classifyId);

    public boolean deleteByClassify(long classifyId);
}
