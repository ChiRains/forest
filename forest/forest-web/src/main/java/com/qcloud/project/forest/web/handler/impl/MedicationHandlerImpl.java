package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.project.forest.service.MedicationTimeService;
import com.qcloud.project.forest.web.handler.MedicationHandler;
import com.qcloud.project.forest.model.Medication;
import com.qcloud.project.forest.model.MedicationTime;
import com.qcloud.project.forest.web.vo.MedicationVO;
import com.qcloud.project.forest.web.vo.admin.AdminMedicationVO;

@Component
public class MedicationHandlerImpl implements MedicationHandler {

    @Autowired
    private FileSDKClient         fileSDKClient;

    @Autowired
    private MedicationTimeService medicationTimeService;

    @Override
    public List<MedicationVO> toVOList(List<Medication> list) {

        List<MedicationVO> voList = new ArrayList<MedicationVO>();
        for (Medication medication : list) {
            voList.add(toVO(medication));
        }
        return voList;
    }

    @Override
    public MedicationVO toVO(Medication medication) {

        String json = Json.toJson(medication);
        MedicationVO vo = Json.toObject(json, MedicationVO.class, true);
        if (!StringUtils.isEmpty(vo.getImage())) {
            vo.setImage(fileSDKClient.getFileServerUrl() + vo.getImage());
        } else {
            vo.setImage("");
        }
        vo.setImageUid(fileSDKClient.urlToUid(vo.getImage()));
        vo.setRecordTime(DateUtil.date2String(medication.getRecordTime(), DateUtil.FORMAT_STRING));
        // List<MedicationTime> medicationTimeList = medicationTimeService.listByMedicationId(medication.getId());
        List<String> takeTimesList = new ArrayList<String>();
        String[] takeTimes = vo.getUseTimes().split(",");
        for (String take : takeTimes) {
            takeTimesList.add(take);
        }
        vo.setTakeTimes(takeTimesList);
        //
        List<Integer> periodTimesList = new ArrayList<Integer>();
        List<String> periodNameList = new ArrayList<String>();
        String[] periodTime = vo.getPeriodTimes().split(",");
        for (String period : periodTime) {
            periodTimesList.add(Integer.parseInt(period));
            periodNameList.add(weekDay[Integer.parseInt(period) - 1]);
        }
        vo.setPeriodNameList(periodNameList);
        vo.setPeriodTimeList(periodTimesList);
        return vo;
    }

    @Override
    public List<AdminMedicationVO> toVOList4Admin(List<Medication> list) {

        List<AdminMedicationVO> voList = new ArrayList<AdminMedicationVO>();
        for (Medication adminMedication : list) {
            voList.add(toVO4Admin(adminMedication));
        }
        return voList;
    }

    @Override
    public AdminMedicationVO toVO4Admin(Medication medication) {

        String json = Json.toJson(medication);
        return Json.toObject(json, AdminMedicationVO.class, true);
    }

    private static final String weekDay[] = { "一", "二", "三", "四", "五", "六", "日"};
}
