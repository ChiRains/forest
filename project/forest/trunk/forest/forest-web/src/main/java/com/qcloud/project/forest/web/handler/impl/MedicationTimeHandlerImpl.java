package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.web.handler.MedicationTimeHandler;
import com.qcloud.project.forest.model.MedicationTime;
import com.qcloud.project.forest.web.vo.MedicationTimeVO;
import com.qcloud.project.forest.web.vo.admin.AdminMedicationTimeVO;

@Component
public class MedicationTimeHandlerImpl implements MedicationTimeHandler {

	@Override
	public List<MedicationTimeVO> toVOList(List<MedicationTime> list){
		List<MedicationTimeVO> voList = new ArrayList<MedicationTimeVO>();
		for (MedicationTime medicationTime : list) {
			voList.add(toVO(medicationTime));
		}
		return voList;
	}

	@Override
	public MedicationTimeVO toVO(MedicationTime medicationTime){
		String json = Json.toJson(medicationTime);
		return Json.toObject(json, MedicationTimeVO.class, true);

	}

	@Override
	public List<AdminMedicationTimeVO> toVOList4Admin(List<MedicationTime> list){
		List<AdminMedicationTimeVO> voList = new ArrayList<AdminMedicationTimeVO>();
		for (MedicationTime adminMedicationTime : list) {
			voList.add(toVO4Admin(adminMedicationTime));
		}
		return voList;
	}

	@Override
	public AdminMedicationTimeVO toVO4Admin(MedicationTime medicationTime){
		String json = Json.toJson(medicationTime);
		return Json.toObject(json, AdminMedicationTimeVO.class, true);
	}
}
