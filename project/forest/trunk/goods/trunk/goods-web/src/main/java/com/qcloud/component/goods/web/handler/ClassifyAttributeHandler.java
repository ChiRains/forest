package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.ClassifyAttribute;
import com.qcloud.component.goods.web.vo.ClassifyAttributeVO;
import com.qcloud.component.goods.web.vo.admin.AdminClassifyAttributeVO;

public interface ClassifyAttributeHandler {

	List<ClassifyAttributeVO> toVOList(List<ClassifyAttribute> list);

	ClassifyAttributeVO toVO(ClassifyAttribute classifyAttribute);

	List<AdminClassifyAttributeVO> toVOList4Admin(List<ClassifyAttribute> list);

	AdminClassifyAttributeVO toVO4Admin(ClassifyAttribute classifyAttribute);
}
