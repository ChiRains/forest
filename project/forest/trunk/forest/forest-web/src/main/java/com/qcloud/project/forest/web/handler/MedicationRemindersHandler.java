package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.MedicationReminders;
import com.qcloud.project.forest.web.vo.MedicationRemindersVO;
import com.qcloud.project.forest.web.vo.admin.AdminMedicationRemindersVO;

public interface MedicationRemindersHandler {

	List<MedicationRemindersVO> toVOList(List<MedicationReminders> list);

	MedicationRemindersVO toVO(MedicationReminders medicationReminders);

	List<AdminMedicationRemindersVO> toVOList4Admin(List<MedicationReminders> list);

	AdminMedicationRemindersVO toVO4Admin(MedicationReminders medicationReminders);
}
