package com.qcloud.project.forest.web.handler;

import java.util.List;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.project.forest.model.ForestOrder;
import com.qcloud.project.forest.web.vo.ForestOrderVO;
import com.qcloud.project.forest.web.vo.admin.AdminForestOrderVO;

public interface ForestOrderHandler {

    List<ForestOrderVO> toVOList(List<ForestOrder> list);

    ForestOrderVO toVO(ForestOrder forestOrder);

    ForestOrderVO toOrderVO(ForestOrder forestOrder, QOrder order);

    List<AdminForestOrderVO> toVOList4Admin(List<ForestOrder> list);

    AdminForestOrderVO toVO4Admin(ForestOrder forestOrder);
}
