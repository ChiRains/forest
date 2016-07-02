package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;
import com.qcloud.component.commoditycenter.model.MerchandiseMarketing;
import com.qcloud.component.commoditycenter.web.vo.ExchangeMerchandiseListVO;

public interface ExchangeMerchandiseHandler {

    public List<ExchangeMerchandiseListVO> toVOList(List<MerchandiseMarketing> list);
}
