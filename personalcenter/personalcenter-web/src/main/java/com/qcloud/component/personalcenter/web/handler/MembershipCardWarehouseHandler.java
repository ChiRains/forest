package com.qcloud.component.personalcenter.web.handler;

import java.util.List;

import com.qcloud.component.personalcenter.model.MembershipCardWarehouse;
import com.qcloud.component.personalcenter.web.vo.MembershipCardWarehouseVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMembershipCardWarehouseVO;

public interface MembershipCardWarehouseHandler {

	List<MembershipCardWarehouseVO> toVOList(List<MembershipCardWarehouse> list);

	MembershipCardWarehouseVO toVO(MembershipCardWarehouse membershipCardWarehouse);

	List<AdminMembershipCardWarehouseVO> toVOList4Admin(List<MembershipCardWarehouse> list);

	AdminMembershipCardWarehouseVO toVO4Admin(MembershipCardWarehouse membershipCardWarehouse);
}
