package com.qcloud.component.goods.web.handler;

import java.util.List;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.web.vo.CombinationMerchandiseVO;
import com.qcloud.component.goods.web.vo.admin.AdminCombinationMerchandiseVO;

public interface CombinationMerchandiseHandler {

    List<CombinationMerchandiseVO> toVOList(List<UnifiedMerchandise> list);

    CombinationMerchandiseVO toVO(UnifiedMerchandise combinationMerchandise);

    List<AdminCombinationMerchandiseVO> toVOList4Admin(List<UnifiedMerchandise> list);

    AdminCombinationMerchandiseVO toVO4Admin(UnifiedMerchandise combinationMerchandise);
}
