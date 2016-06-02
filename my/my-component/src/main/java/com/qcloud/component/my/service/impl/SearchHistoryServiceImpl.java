package com.qcloud.component.my.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.dao.SearchHistoryDao;
import com.qcloud.component.my.model.SearchHistory;
import com.qcloud.component.my.model.query.SearchHistoryQuery;
import com.qcloud.component.my.service.SearchHistoryService;
import com.qcloud.pirates.data.Page;

@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {

    @Autowired
    private SearchHistoryDao    searchHistoryDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "my_search_history";

    @Override
    public boolean add(SearchHistory searchHistory) {

        SearchHistory sh = searchHistoryDao.get(searchHistory.getUserId(), searchHistory.getType(), searchHistory.getKeywords());
        if (sh == null) {
            searchHistory.setTime(new Date());
            long id = autoIdGenerator.get(ID_KEY);
            searchHistory.setId(id);
            return searchHistoryDao.add(searchHistory);
        } else {
            sh.setTime(new Date());
            return update(searchHistory);
        }
    }

    @Override
    public SearchHistory get(Long id) {

        return searchHistoryDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return searchHistoryDao.delete(id);
    }

    @Override
    public boolean update(SearchHistory searchHistory) {

        return searchHistoryDao.update(searchHistory);
    }

    @Override
    public Page<SearchHistory> page(SearchHistoryQuery query, int start, int count) {

        return searchHistoryDao.page(query, start, count);
    }

    public List<SearchHistory> listAll() {

        return searchHistoryDao.listAll();
    }

    @Override
    public List<String> list(long userId, int type, int number) {

        List<SearchHistory> list = searchHistoryDao.list(userId, type, number);
        List<String> strList = new ArrayList<String>();
        for (SearchHistory searchHistory : list) {
            strList.add(searchHistory.getKeywords());
        }
        return strList;
    }

    @Override
    public boolean clear(long userId, int type) {

        return searchHistoryDao.clear(userId, type);
    }
}
