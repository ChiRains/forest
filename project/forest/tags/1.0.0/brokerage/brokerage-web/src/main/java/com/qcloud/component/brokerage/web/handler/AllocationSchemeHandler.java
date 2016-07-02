package com.qcloud.component.brokerage.web.handler;

import java.util.List;

import com.qcloud.component.brokerage.model.AllocationScheme;
import com.qcloud.component.brokerage.web.vo.AllocationSchemeVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminAllocationSchemeVO;

public interface AllocationSchemeHandler {

	List<AllocationSchemeVO> toVOList(List<AllocationScheme> list);

	AllocationSchemeVO toVO(AllocationScheme allocationScheme);

	List<AdminAllocationSchemeVO> toVOList4Admin(List<AllocationScheme> list);

	AdminAllocationSchemeVO toVO4Admin(AllocationScheme allocationScheme);
}
