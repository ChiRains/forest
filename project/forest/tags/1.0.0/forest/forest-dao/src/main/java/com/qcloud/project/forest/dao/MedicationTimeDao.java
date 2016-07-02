package com.qcloud.project.forest.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.MedicationTime;
import com.qcloud.project.forest.model.query.MedicationTimeQuery;

public interface MedicationTimeDao extends ISimpleDao<MedicationTime, Long> {

    public boolean add(MedicationTime medicationTime);

    public MedicationTime get(Long id);

    public boolean delete(Long id);

    public boolean update(MedicationTime medicationTime);

    public List<MedicationTime> list(List<Long> idList);

    public Map<Long, MedicationTime> map(Set<Long> idSet);

    public Page<MedicationTime> page(MedicationTimeQuery query, int start, int size);

    public List<MedicationTime> listAll();

    public List<MedicationTime> listByUserId(Long userId);

    public List<MedicationTime> listByExcuteTime(Date excuteTime);

    public List<MedicationTime> listByMedicationId(Long medicationId);
}
