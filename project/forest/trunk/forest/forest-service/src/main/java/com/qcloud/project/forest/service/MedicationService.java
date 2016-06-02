package com.qcloud.project.forest.service;

import java.util.Date;
import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.Medication;
import com.qcloud.project.forest.model.query.MedicationQuery;
import com.qcloud.project.forest.model.key.TypeEnum.PeriodType;

public interface MedicationService {

    public boolean add(Medication medication);

    public Medication get(Long id);

    public boolean delete(Long id);

    public boolean update(Medication medication);

    public Page<Medication> page(MedicationQuery query, int start, int count);

    public List<Medication> listAll();

    /**
     * 获取第一次执行时间
     * @param periodType
     * @param takeTime      格式-08:00
     * @return
     */
    public Date getExcuteTime(PeriodType periodType, String takeTime);
}
