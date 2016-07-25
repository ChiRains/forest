package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.GradeMerchandise;
import com.qcloud.project.forest.web.vo.GradeMerchandiseVO;
import com.qcloud.project.forest.web.vo.admin.AdminGradeMerchandiseVO;

public interface GradeMerchandiseHandler {

	List<GradeMerchandiseVO> toVOList(List<GradeMerchandise> list);

	GradeMerchandiseVO toVO(GradeMerchandise gradeMerchandise);

	List<AdminGradeMerchandiseVO> toVOList4Admin(List<GradeMerchandise> list);

	AdminGradeMerchandiseVO toVO4Admin(GradeMerchandise gradeMerchandise);
}
