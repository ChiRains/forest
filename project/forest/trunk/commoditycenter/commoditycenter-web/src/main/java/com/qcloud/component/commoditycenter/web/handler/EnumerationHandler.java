package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;

import com.qcloud.component.commoditycenter.model.Enumeration;
import com.qcloud.component.commoditycenter.web.vo.EnumerationVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminEnumerationVO;

public interface EnumerationHandler {

	List<EnumerationVO> toVOList(List<Enumeration> list);

	EnumerationVO toVO(Enumeration enumeration);

	List<AdminEnumerationVO> toVOList4Admin(List<Enumeration> list);

	AdminEnumerationVO toVO4Admin(Enumeration enumeration);
	
	AdminEnumerationVO toVO4Admin(String name);
	
	List<AdminEnumerationVO> toVOList4AdminWithoutSpec(List<Enumeration> list);
	
	AdminEnumerationVO toVO4AdminWithoutSpec(Enumeration enumeration);
}
