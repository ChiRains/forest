package com.qcloud.component.my.web.handler;

import java.util.List;
import com.qcloud.component.my.model.Consignee;
import com.qcloud.component.my.web.vo.ConsigneeVO;

public interface ConsigneeHandler {

    List<ConsigneeVO> toVOList(List<Consignee> list);

    ConsigneeVO toVO(Consignee consignee);
}
