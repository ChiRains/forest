package com.qcloud.project.forest.web.handler;

import java.util.List;
import com.qcloud.project.forest.model.ExpressQueryHistory;
import com.qcloud.project.forest.web.vo.ExpressQueryHistoryVO;

public interface ExpressQueryHistoryHandler {

    List<ExpressQueryHistoryVO> toVOList(List<ExpressQueryHistory> list);

    ExpressQueryHistoryVO toVO(ExpressQueryHistory expressQueryHistory);
}
