package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.PartsMerchandise;
import com.qcloud.project.forest.model.query.PartsMerchandiseQuery;

public interface PartsMerchandiseService {

    public boolean add(PartsMerchandise partsMerchandise);

    public PartsMerchandise get(Long id);

    public boolean delete(Long id);

    public boolean update(PartsMerchandise partsMerchandise);

    public List<PartsMerchandise> listByClassifyId(Long classifyId);

    public Page<PartsMerchandise> page(PartsMerchandiseQuery query, int start, int count);

    public List<PartsMerchandise> listAll();

    public boolean deleteByClassify(long classifyId);
}
