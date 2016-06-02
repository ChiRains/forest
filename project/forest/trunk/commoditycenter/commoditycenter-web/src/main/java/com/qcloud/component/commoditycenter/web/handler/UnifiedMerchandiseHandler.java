package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;

import com.qcloud.component.commoditycenter.model.UnifiedMerchandise;
import com.qcloud.component.commoditycenter.web.vo.UnifiedMerchandiseVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminUnifiedMerchandiseVO;

public interface UnifiedMerchandiseHandler {

	List<UnifiedMerchandiseVO> toVOList(List<UnifiedMerchandise> list);

	UnifiedMerchandiseVO toVO(UnifiedMerchandise unifiedMerchandise);

	List<AdminUnifiedMerchandiseVO> toVOList4Admin(List<UnifiedMerchandise> list);

	AdminUnifiedMerchandiseVO toVO4Admin(UnifiedMerchandise unifiedMerchandise);
}
