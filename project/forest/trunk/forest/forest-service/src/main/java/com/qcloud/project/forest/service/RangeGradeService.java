package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.RangeGrade;
import com.qcloud.project.forest.model.query.RangeGradeQuery;

public interface RangeGradeService {

    public boolean add(RangeGrade rangeGrade);

    public RangeGrade get(Long id);

    public boolean delete(Long id);

    public boolean update(RangeGrade rangeGrade);

    public Page<RangeGrade> page(RangeGradeQuery query, int start, int count);

    public List<RangeGrade> listAll();

    public List<RangeGrade> listByRange(long rangeId);
}
