package com.qcloud.component.orderform.web.handler;

import java.util.List;
import com.qcloud.component.orderform.model.ReturnOrderItem;
import com.qcloud.component.orderform.web.vo.ReturnOrderItemVO;
import com.qcloud.component.orderform.web.vo.admin.AdminReturnOrderItemVO;

public interface ReturnOrderItemHandler {

	List<ReturnOrderItemVO> toVOList(List<ReturnOrderItem> list);

	ReturnOrderItemVO toVO(ReturnOrderItem returnOrderItem);

	List<AdminReturnOrderItemVO> toVOList4Admin(List<ReturnOrderItem> list);

	AdminReturnOrderItemVO toVO4Admin(ReturnOrderItem returnOrderItem);
}
