package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.web.vo.UnifiedMerchandiseVO;
import com.qcloud.component.goods.web.vo.admin.AdminUnifiedMerchandiseVO;

public interface UnifiedMerchandiseHandler {

	List<UnifiedMerchandiseVO> toVOList(List<UnifiedMerchandise> list);

	UnifiedMerchandiseVO toVO(UnifiedMerchandise unifiedMerchandise);

	List<AdminUnifiedMerchandiseVO> toVOList4Admin(List<UnifiedMerchandise> list);

	AdminUnifiedMerchandiseVO toVO4Admin(UnifiedMerchandise unifiedMerchandise);
}
