package com.qcloud.component.my.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.my.model.SearchHistory;
import com.qcloud.component.my.model.query.SearchHistoryQuery;

public interface SearchHistoryDao extends ISimpleDao<SearchHistory, Long> {

    public boolean add(SearchHistory searchHistory);

    public SearchHistory get(Long id);

    public SearchHistory get(long userId, int type, String keywords);

    public boolean delete(Long id);

    public boolean update(SearchHistory searchHistory);

    public List<SearchHistory> list(List<Long> idList);

    public Map<Long, SearchHistory> map(Set<Long> idSet);

    public Page<SearchHistory> page(SearchHistoryQuery query, int start, int size);

    public List<SearchHistory> listAll();

    List<SearchHistory> list(long userId, int type, int number);
    
    boolean clear(long userId, int type);
}
