package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;
import com.qcloud.component.commoditycenter.model.MonthHotSale;
import com.qcloud.component.commoditycenter.web.vo.MonthHotSaleVO;

public interface MonthHotSaleHandler {

    List<MonthHotSaleVO> toVOList(List<MonthHotSale> list);

    MonthHotSaleVO toVO(MonthHotSale monthHotSale);
}
