package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.model.MedicationReminders;
import com.qcloud.project.forest.model.MedicationRemindersTheme;
import com.qcloud.project.forest.service.MedicationRemindersService;
import com.qcloud.project.forest.web.handler.MedicationRemindersHandler;
import com.qcloud.project.forest.web.handler.MedicationRemindersThemeHandler;
import com.qcloud.project.forest.web.vo.MedicationRemindersThemeVO;
import com.qcloud.project.forest.web.vo.MedicationRemindersVO;
import com.qcloud.project.forest.web.vo.admin.AdminMedicationRemindersThemeVO;

@Component
public class MedicationRemindersThemeHandlerImpl implements MedicationRemindersThemeHandler {

    @Autowired
    private FileSDKClient              fileSDKClient;

    @Autowired
    private MedicationRemindersService medicationRemindersService;

    @Autowired
    private MedicationRemindersHandler medicationRemindersHandler;

    @Override
    public List<MedicationRemindersThemeVO> toVOList(List<MedicationRemindersTheme> list) {

        List<MedicationRemindersThemeVO> voList = new ArrayList<MedicationRemindersThemeVO>();
        for (MedicationRemindersTheme medicationRemindersTheme : list) {
            voList.add(toVO(medicationRemindersTheme));
        }
        return voList;
    }

    @Override
    public MedicationRemindersThemeVO toVO(MedicationRemindersTheme medicationRemindersTheme) {

        String json = Json.toJson(medicationRemindersTheme);
        MedicationRemindersThemeVO medicationRemindersThemeVO = Json.toObject(json, MedicationRemindersThemeVO.class, true);
        if (!StringUtils.isEmpty(medicationRemindersThemeVO.getImage())) {
            medicationRemindersThemeVO.setImage(fileSDKClient.getFileServerUrl() + medicationRemindersThemeVO.getImage());
        } else {
            medicationRemindersThemeVO.setImage("");
        }
        List<MedicationReminders> medicationReminders = medicationRemindersService.listByThemeId(medicationRemindersTheme.getId());
        List<MedicationRemindersVO> medicationRemindersVOs = medicationRemindersHandler.toVOList(medicationReminders);
        medicationRemindersThemeVO.setMedicationRemindersVOs(medicationRemindersVOs);
        medicationRemindersThemeVO.setUid(medicationRemindersTheme.getImage());
        return medicationRemindersThemeVO;
    }

    @Override
    public List<AdminMedicationRemindersThemeVO> toVOList4Admin(List<MedicationRemindersTheme> list) {

        List<AdminMedicationRemindersThemeVO> voList = new ArrayList<AdminMedicationRemindersThemeVO>();
        for (MedicationRemindersTheme adminMedicationRemindersTheme : list) {
            voList.add(toVO4Admin(adminMedicationRemindersTheme));
        }
        return voList;
    }

    @Override
    public AdminMedicationRemindersThemeVO toVO4Admin(MedicationRemindersTheme medicationRemindersTheme) {

        String json = Json.toJson(medicationRemindersTheme);
        return Json.toObject(json, AdminMedicationRemindersThemeVO.class, true);
    }
}
