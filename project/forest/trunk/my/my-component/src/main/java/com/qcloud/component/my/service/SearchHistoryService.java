package com.qcloud.component.my.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.my.model.SearchHistory;
import com.qcloud.component.my.model.query.SearchHistoryQuery;

public interface SearchHistoryService {

    public boolean add(SearchHistory searchHistory);

    public SearchHistory get(Long id);

    public boolean delete(Long id);

    public boolean clear(long userId, int type);

    public boolean update(SearchHistory searchHistory);

    public Page<SearchHistory> page(SearchHistoryQuery query, int start, int count);

    public List<SearchHistory> listAll();

    public List<String> list(long userId, int type, int number);
}
