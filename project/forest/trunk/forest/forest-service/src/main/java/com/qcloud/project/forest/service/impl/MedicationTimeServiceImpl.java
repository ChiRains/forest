package com.qcloud.project.forest.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.MedicationTimeDao;
import com.qcloud.project.forest.model.MedicationTime;
import com.qcloud.project.forest.service.MedicationTimeService;
import com.qcloud.project.forest.model.query.MedicationTimeQuery;

@Service
public class MedicationTimeServiceImpl implements MedicationTimeService {

    @Autowired
    private MedicationTimeDao     medicationTimeDao;

    @Autowired
    private AutoIdGenerator       autoIdGenerator;

    private static final String   ID_KEY = "forest_medication_time";

    @Override
    public boolean add(MedicationTime medicationTime) {

        long id = autoIdGenerator.get(ID_KEY);
        medicationTime.setId(id);
        return medicationTimeDao.add(medicationTime);
    }

    @Override
    public MedicationTime get(Long id) {

        return medicationTimeDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return medicationTimeDao.delete(id);
    }

    @Override
    public boolean update(MedicationTime medicationTime) {

        return medicationTimeDao.update(medicationTime);
    }

    public List<MedicationTime> listByUserId(Long userId) {

        return medicationTimeDao.listByUserId(userId);
    }

    public List<MedicationTime> listByExcuteTime(Date excuteTime) {

        return medicationTimeDao.listByExcuteTime(excuteTime);
    }

    @Override
    public Page<MedicationTime> page(MedicationTimeQuery query, int start, int count) {

        return medicationTimeDao.page(query, start, count);
    }

    public List<MedicationTime> listAll() {

        return medicationTimeDao.listAll();
    }

    @Override
    public List<MedicationTime> listByMedicationId(Long medicationId) {

        return medicationTimeDao.listByMedicationId(medicationId);
    }
}
