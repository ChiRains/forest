package com.qcloud.component.organization.web.handler;

import java.util.List;

import com.qcloud.component.organization.model.DepartmentImage;
import com.qcloud.component.organization.web.vo.DepartmentImageVO;
import com.qcloud.component.organization.web.vo.admin.AdminDepartmentImageVO;

public interface DepartmentImageHandler {

	List<DepartmentImageVO> toVOList(List<DepartmentImage> list);

	DepartmentImageVO toVO(DepartmentImage departmentImage);

	List<AdminDepartmentImageVO> toVOList4Admin(List<DepartmentImage> list);

	AdminDepartmentImageVO toVO4Admin(DepartmentImage departmentImage);
}
