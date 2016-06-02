package com.qcloud.project.forest.service;

import java.util.Date;
import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.MedicationTime;
import com.qcloud.project.forest.model.query.MedicationTimeQuery;

public interface MedicationTimeService {

    public boolean add(MedicationTime medicationTime);

    public MedicationTime get(Long id);

    public boolean delete(Long id);

    public boolean update(MedicationTime medicationTime);

    public List<MedicationTime> listByUserId(Long userId);

    public List<MedicationTime> listByExcuteTime(Date excuteTime);

    public Page<MedicationTime> page(MedicationTimeQuery query, int start, int count);

    public List<MedicationTime> listAll();

    public List<MedicationTime> listByMedicationId(Long medicationId);
}
