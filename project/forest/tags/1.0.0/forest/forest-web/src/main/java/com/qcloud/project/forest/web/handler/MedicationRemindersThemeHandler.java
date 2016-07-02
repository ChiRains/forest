package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.MedicationRemindersTheme;
import com.qcloud.project.forest.web.vo.MedicationRemindersThemeVO;
import com.qcloud.project.forest.web.vo.admin.AdminMedicationRemindersThemeVO;

public interface MedicationRemindersThemeHandler {

	List<MedicationRemindersThemeVO> toVOList(List<MedicationRemindersTheme> list);

	MedicationRemindersThemeVO toVO(MedicationRemindersTheme medicationRemindersTheme);

	List<AdminMedicationRemindersThemeVO> toVOList4Admin(List<MedicationRemindersTheme> list);

	AdminMedicationRemindersThemeVO toVO4Admin(MedicationRemindersTheme medicationRemindersTheme);
}
