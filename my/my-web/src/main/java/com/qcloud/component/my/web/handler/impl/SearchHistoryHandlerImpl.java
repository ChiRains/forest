package com.qcloud.component.my.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.my.web.handler.SearchHistoryHandler;
import com.qcloud.component.my.model.SearchHistory;
import com.qcloud.component.my.web.vo.SearchHistoryVO;
import com.qcloud.component.my.web.vo.admin.AdminSearchHistoryVO;

@Component
public class SearchHistoryHandlerImpl implements SearchHistoryHandler {

	@Override
	public List<SearchHistoryVO> toVOList(List<SearchHistory> list){
		List<SearchHistoryVO> voList = new ArrayList<SearchHistoryVO>();
		for (SearchHistory searchHistory : list) {
			voList.add(toVO(searchHistory));
		}
		return voList;
	}

	@Override
	public SearchHistoryVO toVO(SearchHistory searchHistory){
		String json = Json.toJson(searchHistory);
		return Json.toObject(json, SearchHistoryVO.class, true);

	}

	@Override
	public List<AdminSearchHistoryVO> toVOList4Admin(List<SearchHistory> list){
		List<AdminSearchHistoryVO> voList = new ArrayList<AdminSearchHistoryVO>();
		for (SearchHistory adminSearchHistory : list) {
			voList.add(toVO4Admin(adminSearchHistory));
		}
		return voList;
	}

	@Override
	public AdminSearchHistoryVO toVO4Admin(SearchHistory searchHistory){
		String json = Json.toJson(searchHistory);
		return Json.toObject(json, AdminSearchHistoryVO.class, true);
	}
}
