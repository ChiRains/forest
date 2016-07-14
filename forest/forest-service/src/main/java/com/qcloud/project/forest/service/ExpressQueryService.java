package com.qcloud.project.forest.service;

import java.util.List;
import java.util.Map;
import com.qcloud.project.forest.model.ExpressQueryVO;

public interface ExpressQueryService {

    public static final String apiUrl = "http://api.kuaidi100.com/api";

    Map<String, String> getApiConfig();

    List<ExpressQueryVO> getList(Long userId, String code, String expressNumber);
}
