package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.Medication;
import com.qcloud.project.forest.web.vo.MedicationVO;
import com.qcloud.project.forest.web.vo.admin.AdminMedicationVO;

public interface MedicationHandler {

	List<MedicationVO> toVOList(List<Medication> list);

	MedicationVO toVO(Medication medication);

	List<AdminMedicationVO> toVOList4Admin(List<Medication> list);

	AdminMedicationVO toVO4Admin(Medication medication);
}
