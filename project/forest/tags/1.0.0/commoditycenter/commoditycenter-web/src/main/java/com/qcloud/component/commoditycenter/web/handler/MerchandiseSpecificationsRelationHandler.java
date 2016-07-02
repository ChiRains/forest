package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;

import com.qcloud.component.commoditycenter.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseSpecificationsRelationVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminMerchandiseSpecificationsRelationVO;

public interface MerchandiseSpecificationsRelationHandler {

	List<MerchandiseSpecificationsRelationVO> toVOList(List<MerchandiseSpecificationsRelation> list);

	MerchandiseSpecificationsRelationVO toVO(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation);

	List<AdminMerchandiseSpecificationsRelationVO> toVOList4Admin(List<MerchandiseSpecificationsRelation> list);

	AdminMerchandiseSpecificationsRelationVO toVO4Admin(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation);
}
