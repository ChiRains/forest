package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.ClassifySpecifications;
import com.qcloud.component.goods.web.vo.ClassifySpecificationsVO;
import com.qcloud.component.goods.web.vo.admin.AdminClassifySpecificationsVO;

public interface ClassifySpecificationsHandler {

	List<ClassifySpecificationsVO> toVOList(List<ClassifySpecifications> list);

	ClassifySpecificationsVO toVO(ClassifySpecifications classifySpecifications);

	List<AdminClassifySpecificationsVO> toVOList4Admin(List<ClassifySpecifications> list);

	AdminClassifySpecificationsVO toVO4Admin(ClassifySpecifications classifySpecifications);
}
