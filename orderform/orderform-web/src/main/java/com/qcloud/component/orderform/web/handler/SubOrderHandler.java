package com.qcloud.component.orderform.web.handler;

import java.util.List;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.web.vo.admin.AdminSubOrderVO;

public interface SubOrderHandler {

//	List<SubOrderVO> toVOList(List<SubOrder> list);
//
//	SubOrderVO toVO(SubOrder subOrder);

	List<AdminSubOrderVO> toVOList4Admin(List<SubOrder> list);

	AdminSubOrderVO toVO4Admin(SubOrder subOrder);
}
