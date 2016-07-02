package com.qcloud.component.sellercenter.web.handler;

import java.util.List;

import com.qcloud.component.sellercenter.model.DistributeMembershipCard;
import com.qcloud.component.sellercenter.web.vo.DistributeMembershipCardVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminDistributeMembershipCardVO;

public interface DistributeMembershipCardHandler {

	List<DistributeMembershipCardVO> toVOList(List<DistributeMembershipCard> list);

	DistributeMembershipCardVO toVO(DistributeMembershipCard distributeMembershipCard);

	List<AdminDistributeMembershipCardVO> toVOList4Admin(List<DistributeMembershipCard> list);

	AdminDistributeMembershipCardVO toVO4Admin(DistributeMembershipCard distributeMembershipCard);
}
