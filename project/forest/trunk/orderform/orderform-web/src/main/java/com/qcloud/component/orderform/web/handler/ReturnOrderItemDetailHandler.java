package com.qcloud.component.orderform.web.handler;

import java.util.List;

import com.qcloud.component.orderform.model.ReturnOrderItemDetail;
import com.qcloud.component.orderform.web.vo.ReturnOrderItemDetailVO;
import com.qcloud.component.orderform.web.vo.admin.AdminReturnOrderItemDetailVO;

public interface ReturnOrderItemDetailHandler {

	List<ReturnOrderItemDetailVO> toVOList(List<ReturnOrderItemDetail> list);

	ReturnOrderItemDetailVO toVO(ReturnOrderItemDetail returnOrderItemDetail);

	List<AdminReturnOrderItemDetailVO> toVOList4Admin(List<ReturnOrderItemDetail> list);

	AdminReturnOrderItemDetailVO toVO4Admin(ReturnOrderItemDetail returnOrderItemDetail);
}
