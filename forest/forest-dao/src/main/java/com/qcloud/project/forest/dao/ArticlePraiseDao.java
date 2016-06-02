package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.ArticlePraise;
import com.qcloud.project.forest.model.query.ArticlePraiseQuery;

public interface ArticlePraiseDao extends ISimpleDao<ArticlePraise, Long> {

    public boolean add(ArticlePraise articlePraise);

    public ArticlePraise get(Long id);

    public boolean delete(Long id);

    public boolean update(ArticlePraise articlePraise);

    public List<ArticlePraise> list(List<Long> idList);

    public Map<Long, ArticlePraise> map(Set<Long> idSet);

    public Page<ArticlePraise> page(ArticlePraiseQuery query, int start, int size);

    public List<ArticlePraise> listAll();

    public ArticlePraise getByUser(Long userId, Long articleId);
}
