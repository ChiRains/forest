package com.qcloud.component.orderform.web.handler;

import java.util.List;

import com.qcloud.component.orderform.model.ReturnOrder;
import com.qcloud.component.orderform.web.vo.ReturnOrderVO;
import com.qcloud.component.orderform.web.vo.admin.AdminReturnOrderVO;

public interface ReturnOrderHandler {

	List<ReturnOrderVO> toVOList(List<ReturnOrder> list);

	ReturnOrderVO toVO(ReturnOrder returnOrder);

	List<AdminReturnOrderVO> toVOList4Admin(List<ReturnOrder> list);

	AdminReturnOrderVO toVO4Admin(ReturnOrder returnOrder);
}
