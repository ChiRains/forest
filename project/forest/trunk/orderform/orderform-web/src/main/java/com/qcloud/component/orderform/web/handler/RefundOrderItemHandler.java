package com.qcloud.component.orderform.web.handler;

import java.util.List;
import com.qcloud.component.orderform.model.RefundOrderItem;
import com.qcloud.component.orderform.web.vo.RefundOrderItemVO;
import com.qcloud.component.orderform.web.vo.admin.AdminRefundOrderItemVO;

public interface RefundOrderItemHandler {

    List<RefundOrderItemVO> toVOList(List<RefundOrderItem> list);

    RefundOrderItemVO toVO(RefundOrderItem refundOrderItem);

    List<AdminRefundOrderItemVO> toVOList4Admin(List<RefundOrderItem> list);

    AdminRefundOrderItemVO toVO4Admin(RefundOrderItem refundOrderItem);
}
