package com.qcloud.project.forest.web.handler;

import java.util.List;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.project.forest.model.PromotionalOffers;
import com.qcloud.project.forest.web.vo.PromotionalOffersVO;
import com.qcloud.project.forest.web.vo.admin.AdminPromotionalOffersVO;

public interface PromotionalOffersHandler {

    List<PromotionalOffersVO> toVOList(List<UnifiedMerchandise> list);

    PromotionalOffersVO toVO(UnifiedMerchandise unifiedMerchandise);

    List<AdminPromotionalOffersVO> toVOList4Admin(List<PromotionalOffers> list);

    AdminPromotionalOffersVO toVO4Admin(PromotionalOffers promotionalOffers);
}
