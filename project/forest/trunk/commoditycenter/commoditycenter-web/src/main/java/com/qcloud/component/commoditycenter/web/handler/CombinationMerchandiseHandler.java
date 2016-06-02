package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;

import com.qcloud.component.commoditycenter.model.CombinationMerchandise;
import com.qcloud.component.commoditycenter.web.vo.CombinationMerchandiseVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminCombinationMerchandiseVO;

public interface CombinationMerchandiseHandler {

	List<CombinationMerchandiseVO> toVOList(List<CombinationMerchandise> list);

	CombinationMerchandiseVO toVO(CombinationMerchandise combinationMerchandise);

	List<AdminCombinationMerchandiseVO> toVOList4Admin(List<CombinationMerchandise> list);

	AdminCombinationMerchandiseVO toVO4Admin(CombinationMerchandise combinationMerchandise);
}
