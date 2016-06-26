package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.CombinationMerchandise;
import com.qcloud.component.goods.web.vo.CombinationMerchandiseVO;
import com.qcloud.component.goods.web.vo.admin.AdminCombinationMerchandiseVO;

public interface CombinationMerchandiseHandler {

	List<CombinationMerchandiseVO> toVOList(List<CombinationMerchandise> list);

	CombinationMerchandiseVO toVO(CombinationMerchandise combinationMerchandise);

	List<AdminCombinationMerchandiseVO> toVOList4Admin(List<CombinationMerchandise> list);

	AdminCombinationMerchandiseVO toVO4Admin(CombinationMerchandise combinationMerchandise);
}
