package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.RangeGrade;
import com.qcloud.project.forest.model.query.RangeGradeQuery;

public interface RangeGradeDao extends ISimpleDao<RangeGrade, Long> {

    public boolean add(RangeGrade rangeGrade);

    public RangeGrade get(Long id);

    public boolean delete(Long id);

    public boolean update(RangeGrade rangeGrade);

    public List<RangeGrade> list(List<Long> idList);

    public Map<Long, RangeGrade> map(Set<Long> idSet);

    public Page<RangeGrade> page(RangeGradeQuery query, int start, int size);

    public List<RangeGrade> listByRange(long rangeId);

    public List<RangeGrade> listAll();
}
