package com.qcloud.component.orderform.web.handler;

import java.util.List;

import com.qcloud.component.orderform.model.RefundOrder;
import com.qcloud.component.orderform.web.vo.RefundOrderVO;
import com.qcloud.component.orderform.web.vo.admin.AdminRefundOrderVO;

public interface RefundOrderHandler {

	List<RefundOrderVO> toVOList(List<RefundOrder> list);

	RefundOrderVO toVO(RefundOrder refundOrder);

	List<AdminRefundOrderVO> toVOList4Admin(List<RefundOrder> list);

	AdminRefundOrderVO toVO4Admin(RefundOrder refundOrder);
}
