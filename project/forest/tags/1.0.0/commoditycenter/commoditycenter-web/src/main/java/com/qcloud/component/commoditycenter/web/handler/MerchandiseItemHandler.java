package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;

import com.qcloud.component.commoditycenter.model.MerchandiseItem;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseItemVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminMerchandiseItemVO;

public interface MerchandiseItemHandler {

	List<MerchandiseItemVO> toVOList(List<MerchandiseItem> list);

	MerchandiseItemVO toVO(MerchandiseItem merchandiseItem);

	List<AdminMerchandiseItemVO> toVOList4Admin(List<MerchandiseItem> list);

	AdminMerchandiseItemVO toVO4Admin(MerchandiseItem merchandiseItem);
}
