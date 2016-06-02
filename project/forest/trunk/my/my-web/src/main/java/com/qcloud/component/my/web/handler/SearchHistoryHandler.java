package com.qcloud.component.my.web.handler;

import java.util.List;

import com.qcloud.component.my.model.SearchHistory;
import com.qcloud.component.my.web.vo.SearchHistoryVO;
import com.qcloud.component.my.web.vo.admin.AdminSearchHistoryVO;

public interface SearchHistoryHandler {

	List<SearchHistoryVO> toVOList(List<SearchHistory> list);

	SearchHistoryVO toVO(SearchHistory searchHistory);

	List<AdminSearchHistoryVO> toVOList4Admin(List<SearchHistory> list);

	AdminSearchHistoryVO toVO4Admin(SearchHistory searchHistory);
}
