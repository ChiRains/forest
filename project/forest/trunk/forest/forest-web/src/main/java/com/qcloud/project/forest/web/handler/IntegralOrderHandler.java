package com.qcloud.project.forest.web.handler;

import java.util.List;
import com.qcloud.project.forest.model.IntegralOrder;
import com.qcloud.project.forest.web.vo.IntegralOrderListVO;
import com.qcloud.project.forest.web.vo.IntegralOrderVO;
import com.qcloud.project.forest.web.vo.admin.AdminIntegralOrderVO;

public interface IntegralOrderHandler {

    List<IntegralOrderVO> toVOList(List<IntegralOrder> list);

    IntegralOrderVO toVO(IntegralOrder integralOrder);

    List<IntegralOrderListVO> toListVOList(List<IntegralOrder> list);

    List<AdminIntegralOrderVO> toVOList4Admin(List<IntegralOrder> list);

    AdminIntegralOrderVO toVO4Admin(IntegralOrder integralOrder);
}
