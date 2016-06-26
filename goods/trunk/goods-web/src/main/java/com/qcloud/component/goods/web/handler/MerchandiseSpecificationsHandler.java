package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.web.vo.MerchandiseSpecificationsVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseSpecificationsVO;

public interface MerchandiseSpecificationsHandler {

	List<MerchandiseSpecificationsVO> toVOList(List<MerchandiseSpecifications> list);

	MerchandiseSpecificationsVO toVO(MerchandiseSpecifications merchandiseSpecifications);

	List<AdminMerchandiseSpecificationsVO> toVOList4Admin(List<MerchandiseSpecifications> list);

	AdminMerchandiseSpecificationsVO toVO4Admin(MerchandiseSpecifications merchandiseSpecifications);
}
