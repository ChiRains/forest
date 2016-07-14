package com.qcloud.project.forest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.HttpUtils;
import com.qcloud.project.forest.exception.ForestException;
import com.qcloud.project.forest.model.ExpressQueryHistory;
import com.qcloud.project.forest.model.ExpressQueryVO;
import com.qcloud.project.forest.model.query.ExpressQueryHistoryQuery;
import com.qcloud.project.forest.service.ExpressQueryHistoryService;
import com.qcloud.project.forest.service.ExpressQueryService;

@Component
public class ExpressQueryServiceImpl implements ExpressQueryService {

    @Autowired
    private ExpressQueryHistoryService expressQueryHistoryService;

    @Override
    public Map<String, String> getApiConfig() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "a960241fb0d1ec1d");
        map.put("show", "0");
        map.put("order", "desc");
        return map;
    }

    @Override
    public List<ExpressQueryVO> getList(Long userId, String code, String expressNumber) {

        Map<String, String> map = getApiConfig();
        map.put("com", code);
        map.put("nu", expressNumber);
        String result = HttpUtils.doPost(ExpressQueryService.apiUrl, map);
        Map<String, Object> jsonMap = Json.toMap(result);
        if (jsonMap.get("status").equals("1")) {
            ExpressQueryHistoryQuery expressQueryHistoryQuery = new ExpressQueryHistoryQuery();
            expressQueryHistoryQuery.setExpressNum(expressNumber);
            expressQueryHistoryQuery.setUserId(userId);
            ExpressQueryHistory expressQueryHistory1 = expressQueryHistoryService.getByUserIdAndExpressNum(expressQueryHistoryQuery);
            if (expressQueryHistory1 != null) {
                expressQueryHistoryService.delete(expressQueryHistory1.getId());
            }
            ExpressQueryHistory expressQueryHistory = new ExpressQueryHistory();
            expressQueryHistory.setExpressName(expressNumber);
            expressQueryHistory.setExpressNum(expressNumber);
            expressQueryHistory.setUserId(userId);
            expressQueryHistory.setTime(new Date());
            expressQueryHistoryService.add(expressQueryHistory);
            Object[] objects = JSONArray.fromObject(jsonMap.get("data")).toArray();
            List<ExpressQueryVO> list = new ArrayList<ExpressQueryVO>();
            for (int i = 0, j = objects.length; i < j; i++) {
                list.add(Json.toObject(Json.toFormatJson(objects[i]), ExpressQueryVO.class));
            }
            for (ExpressQueryVO expressQueryVO : list) {
                expressQueryVO.setTime(DateUtil.date2String(DateUtil.str2Date(expressQueryVO.getTime()), "MM-dd HH:mm"));
            }
            return list;
        } else if (jsonMap.get("status").equals("2")) {
            // throw new ForestException("暂时没有物流信息.");
            return new ArrayList<ExpressQueryVO>();
        } else if (jsonMap.get("status").equals("0")) {// 代码不正确
            // throw new ForestException("暂时没有物流信息.");
            return new ArrayList<ExpressQueryVO>();
        }
        return new ArrayList<ExpressQueryVO>();
    }
}
