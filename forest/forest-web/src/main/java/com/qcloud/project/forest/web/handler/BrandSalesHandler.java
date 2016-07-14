package com.qcloud.project.forest.web.handler;

import java.util.List;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.project.forest.web.vo.BrandSalesVO;
import com.qcloud.project.forest.web.vo.admin.AdminBrandSalesVO;

public interface BrandSalesHandler {

    List<BrandSalesVO> toVOList(List<UnifiedMerchandise> list);

    BrandSalesVO toVO(UnifiedMerchandise unifiedMerchandise);

    List<AdminBrandSalesVO> toVOList4Admin(List<UnifiedMerchandise> list);

    AdminBrandSalesVO toVO4Admin(UnifiedMerchandise unifiedMerchandise);
}
