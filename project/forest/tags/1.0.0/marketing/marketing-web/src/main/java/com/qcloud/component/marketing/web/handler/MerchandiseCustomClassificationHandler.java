package com.qcloud.component.marketing.web.handler;

import java.util.List;

import com.qcloud.component.marketing.model.MerchandiseCustomClassification;
import com.qcloud.component.marketing.web.vo.MerchandiseCustomClassificationVO;
import com.qcloud.component.marketing.web.vo.admin.AdminMerchandiseCustomClassificationVO;

public interface MerchandiseCustomClassificationHandler {

	List<MerchandiseCustomClassificationVO> toVOList(List<MerchandiseCustomClassification> list);

	MerchandiseCustomClassificationVO toVO(MerchandiseCustomClassification merchandiseCustomClassification);

	List<AdminMerchandiseCustomClassificationVO> toVOList4Admin(List<MerchandiseCustomClassification> mcList);

	AdminMerchandiseCustomClassificationVO toVO4Admin(MerchandiseCustomClassification merchandiseCustomClassification);
}
