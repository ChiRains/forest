package com.qcloud.component.brokerage.web.handler;

import java.util.List;

import com.qcloud.component.brokerage.model.DistributionBrokerage;
import com.qcloud.component.brokerage.web.vo.DistributionBrokerageVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminDistributionBrokerageVO;

public interface DistributionBrokerageHandler {

	List<DistributionBrokerageVO> toVOList(List<DistributionBrokerage> list);

	DistributionBrokerageVO toVO(DistributionBrokerage distributionBrokerage);

	List<AdminDistributionBrokerageVO> toVOList4Admin(List<DistributionBrokerage> list);

	AdminDistributionBrokerageVO toVO4Admin(DistributionBrokerage distributionBrokerage);
}
