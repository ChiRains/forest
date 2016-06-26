package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.Enumeration;
import com.qcloud.component.goods.web.vo.EnumerationVO;
import com.qcloud.component.goods.web.vo.admin.AdminEnumerationVO;

public interface EnumerationHandler {

	List<EnumerationVO> toVOList(List<Enumeration> list);

	EnumerationVO toVO(Enumeration enumeration);

	List<AdminEnumerationVO> toVOList4Admin(List<Enumeration> list);

	AdminEnumerationVO toVO4Admin(Enumeration enumeration);
}
