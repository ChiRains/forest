package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.goods.web.vo.MerchandiseSpecificationsRelationVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseSpecificationsRelationVO;

public interface MerchandiseSpecificationsRelationHandler {

	List<MerchandiseSpecificationsRelationVO> toVOList(List<MerchandiseSpecificationsRelation> list);

	MerchandiseSpecificationsRelationVO toVO(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation);

	List<AdminMerchandiseSpecificationsRelationVO> toVOList4Admin(List<MerchandiseSpecificationsRelation> list);

	AdminMerchandiseSpecificationsRelationVO toVO4Admin(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation);
}
