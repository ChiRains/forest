package com.qcloud.component.goods.web.handler;

import java.util.List;
import com.qcloud.component.goods.model.MerchandiseMarketing;
import com.qcloud.component.goods.web.vo.ExchangeMerchandiseListVO;

public interface ExchangeMerchandiseHandler {

    public List<ExchangeMerchandiseListVO> toVOList(List<MerchandiseMarketing> list);
}
