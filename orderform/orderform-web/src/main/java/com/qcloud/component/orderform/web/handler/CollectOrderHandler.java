package com.qcloud.component.orderform.web.handler;

import java.util.List;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.web.vo.admin.AdminCollectOrderVO;

public interface CollectOrderHandler {

//	List<CollectOrderVO> toVOList(List<CollectOrder> list);
//
//	CollectOrderVO toVO(CollectOrder collectOrder);

	List<AdminCollectOrderVO> toVOList4Admin(List<CollectOrder> list);

	AdminCollectOrderVO toVO4Admin(CollectOrder collectOrder);
}
