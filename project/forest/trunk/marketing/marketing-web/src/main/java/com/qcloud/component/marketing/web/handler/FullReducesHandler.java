package com.qcloud.component.marketing.web.handler;

import java.util.List;

import com.qcloud.component.marketing.model.FullReduces;
import com.qcloud.component.marketing.web.vo.FullReducesVO;
import com.qcloud.component.marketing.web.vo.admin.AdminFullReducesVO;

public interface FullReducesHandler {

	List<FullReducesVO> toVOList(List<FullReduces> list);

	FullReducesVO toVO(FullReduces fullReduces);

	List<AdminFullReducesVO> toVOList4Admin(List<FullReduces> list);

	AdminFullReducesVO toVO4Admin(FullReduces fullReduces);
}
