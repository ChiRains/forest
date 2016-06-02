package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.MedicationTime;
import com.qcloud.project.forest.web.vo.MedicationTimeVO;
import com.qcloud.project.forest.web.vo.admin.AdminMedicationTimeVO;

public interface MedicationTimeHandler {

	List<MedicationTimeVO> toVOList(List<MedicationTime> list);

	MedicationTimeVO toVO(MedicationTime medicationTime);

	List<AdminMedicationTimeVO> toVOList4Admin(List<MedicationTime> list);

	AdminMedicationTimeVO toVO4Admin(MedicationTime medicationTime);
}
