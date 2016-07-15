package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.PartsMerchandise;
import com.qcloud.project.forest.web.vo.PartsMerchandiseVO;
import com.qcloud.project.forest.web.vo.admin.AdminPartsMerchandiseVO;

public interface PartsMerchandiseHandler {

	List<PartsMerchandiseVO> toVOList(List<PartsMerchandise> list);

	PartsMerchandiseVO toVO(PartsMerchandise partsMerchandise);

	List<AdminPartsMerchandiseVO> toVOList4Admin(List<PartsMerchandise> list);

	AdminPartsMerchandiseVO toVO4Admin(PartsMerchandise partsMerchandise);
}
