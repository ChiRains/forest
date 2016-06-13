package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.web.handler.MedicationRemindersHandler;
import com.qcloud.project.forest.model.MedicationReminders;
import com.qcloud.project.forest.web.vo.MedicationRemindersVO;
import com.qcloud.project.forest.web.vo.admin.AdminMedicationRemindersVO;

@Component
public class MedicationRemindersHandlerImpl implements MedicationRemindersHandler {

	@Override
	public List<MedicationRemindersVO> toVOList(List<MedicationReminders> list){
		List<MedicationRemindersVO> voList = new ArrayList<MedicationRemindersVO>();
		for (MedicationReminders medicationReminders : list) {
			voList.add(toVO(medicationReminders));
		}
		return voList;
	}

	@Override
	public MedicationRemindersVO toVO(MedicationReminders medicationReminders){
		String json = Json.toJson(medicationReminders);
		return Json.toObject(json, MedicationRemindersVO.class, true);

	}

	@Override
	public List<AdminMedicationRemindersVO> toVOList4Admin(List<MedicationReminders> list){
		List<AdminMedicationRemindersVO> voList = new ArrayList<AdminMedicationRemindersVO>();
		for (MedicationReminders adminMedicationReminders : list) {
			voList.add(toVO4Admin(adminMedicationReminders));
		}
		return voList;
	}

	@Override
	public AdminMedicationRemindersVO toVO4Admin(MedicationReminders medicationReminders){
		String json = Json.toJson(medicationReminders);
		return Json.toObject(json, AdminMedicationRemindersVO.class, true);
	}
}
