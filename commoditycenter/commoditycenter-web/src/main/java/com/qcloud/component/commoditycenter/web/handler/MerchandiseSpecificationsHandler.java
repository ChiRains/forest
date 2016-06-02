package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;

import com.qcloud.component.commoditycenter.model.MerchandiseSpecifications;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseSpecificationsVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminMerchandiseSpecificationsVO;

public interface MerchandiseSpecificationsHandler {

	List<MerchandiseSpecificationsVO> toVOList(List<MerchandiseSpecifications> list);

	MerchandiseSpecificationsVO toVO(MerchandiseSpecifications merchandiseSpecifications);

	List<AdminMerchandiseSpecificationsVO> toVOList4Admin(List<MerchandiseSpecifications> list);

	AdminMerchandiseSpecificationsVO toVO4Admin(MerchandiseSpecifications merchandiseSpecifications);
}
