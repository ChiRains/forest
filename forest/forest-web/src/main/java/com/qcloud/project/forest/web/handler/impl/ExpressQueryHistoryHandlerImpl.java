package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.project.forest.model.ExpressQueryHistory;
import com.qcloud.project.forest.web.handler.ExpressQueryHistoryHandler;
import com.qcloud.project.forest.web.vo.ExpressQueryHistoryVO;

@Component
public class ExpressQueryHistoryHandlerImpl implements ExpressQueryHistoryHandler {

    @Override
    public List<ExpressQueryHistoryVO> toVOList(List<ExpressQueryHistory> list) {

        List<ExpressQueryHistoryVO> voList = new ArrayList<ExpressQueryHistoryVO>();
        for (ExpressQueryHistory expressQueryHistory : list) {
            voList.add(toVO(expressQueryHistory));
        }
        return voList;
    }

    @Override
    public ExpressQueryHistoryVO toVO(ExpressQueryHistory expressQueryHistory) {

        String json = Json.toJson(expressQueryHistory);
        ExpressQueryHistoryVO expressQueryHistoryVO = Json.toObject(json, ExpressQueryHistoryVO.class, true);
        expressQueryHistoryVO.setTime(DateUtil.date2String(expressQueryHistory.getTime(), "yyyy-MM-dd"));
        return expressQueryHistoryVO;
    }
}
