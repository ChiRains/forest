package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;

import com.qcloud.component.commoditycenter.model.CombinationMerchandiseItem;
import com.qcloud.component.commoditycenter.web.vo.CombinationMerchandiseItemVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminCombinationMerchandiseItemVO;

public interface CombinationMerchandiseItemHandler {

	List<CombinationMerchandiseItemVO> toVOList(List<CombinationMerchandiseItem> list);

	CombinationMerchandiseItemVO toVO(CombinationMerchandiseItem combinationMerchandiseItem);

	List<AdminCombinationMerchandiseItemVO> toVOList4Admin(List<CombinationMerchandiseItem> list);

	AdminCombinationMerchandiseItemVO toVO4Admin(CombinationMerchandiseItem combinationMerchandiseItem);
}
