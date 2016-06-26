package com.qcloud.component.goods.web.handler;

import java.util.List;
import com.qcloud.component.goods.model.MonthHotSale;
import com.qcloud.component.goods.web.vo.MonthHotSaleVO;

public interface MonthHotSaleHandler {

    List<MonthHotSaleVO> toVOList(List<MonthHotSale> list);

    MonthHotSaleVO toVO(MonthHotSale monthHotSale);
}
