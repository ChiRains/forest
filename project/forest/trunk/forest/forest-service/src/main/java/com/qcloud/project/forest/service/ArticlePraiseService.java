package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.ArticlePraise;
import com.qcloud.project.forest.model.query.ArticlePraiseQuery;

public interface ArticlePraiseService {

    public boolean add(ArticlePraise articlePraise);

    public ArticlePraise get(Long id);

    public boolean delete(Long id);

    public boolean update(ArticlePraise articlePraise);

    public Page<ArticlePraise> page(ArticlePraiseQuery query, int start, int count);

    public List<ArticlePraise> listAll();

    public ArticlePraise getByUser(Long userId, Long articleId);
}
