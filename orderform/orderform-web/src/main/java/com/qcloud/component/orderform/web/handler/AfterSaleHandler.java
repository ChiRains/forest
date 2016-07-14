package com.qcloud.component.orderform.web.handler;

import java.util.List;
import com.qcloud.component.orderform.QAfterSaleOrder;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.web.vo.AfterSaleInfoDetailsVO;
import com.qcloud.component.orderform.web.vo.AfterSaleOrderItemVO;
import com.qcloud.component.orderform.web.vo.personal.AfterSaleVO;

public interface AfterSaleHandler {

    List<AfterSaleOrderItemVO> toVOList(MerchantOrderEntity merchantOrderEntity);

    List<AfterSaleVO> toVOList(List<QAfterSaleOrder> list);

    AfterSaleVO toVO(QAfterSaleOrder afterSaleOrder);

    AfterSaleInfoDetailsVO toDetailsVO(QAfterSaleOrder afterSaleOrder);
}
