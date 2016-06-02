package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.commoditycenter.model.Merchandise;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseVO;
import com.qcloud.component.commoditycenter.web.vo.SimpleMerchandiseVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminMerchandiseVO;

public interface MerchandiseHandler {

    List<MerchandiseVO> toVOList(List<Merchandise> list);

    MerchandiseVO toVO(Merchandise merchandise);

    List<SimpleMerchandiseVO> toSimpleVOList(List<QUnifiedMerchandise> list);

    SimpleMerchandiseVO toSimpleVO(QUnifiedMerchandise merchandise);

    List<AdminMerchandiseVO> toVOList4Admin(List<Merchandise> list);

    AdminMerchandiseVO toVO4Admin(Merchandise merchandise);
}
