package com.qcloud.project.forest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ArticlePraiseDao;
import com.qcloud.project.forest.model.ArticlePraise;
import com.qcloud.project.forest.service.ArticlePraiseService;
import com.qcloud.project.forest.model.query.ArticlePraiseQuery;

@Service
public class ArticlePraiseServiceImpl implements ArticlePraiseService {

    @Autowired
    private ArticlePraiseDao    articlePraiseDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "forest_article_praise";

    @Override
    public boolean add(ArticlePraise articlePraise) {

        long id = autoIdGenerator.get(ID_KEY);
        articlePraise.setId(id);
        return articlePraiseDao.add(articlePraise);
    }

    @Override
    public ArticlePraise get(Long id) {

        return articlePraiseDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return articlePraiseDao.delete(id);
    }

    @Override
    public boolean update(ArticlePraise articlePraise) {

        return articlePraiseDao.update(articlePraise);
    }

    @Override
    public Page<ArticlePraise> page(ArticlePraiseQuery query, int start, int count) {

        return articlePraiseDao.page(query, start, count);
    }

    public List<ArticlePraise> listAll() {

        return articlePraiseDao.listAll();
    }

    @Override
    public ArticlePraise getByUser(Long userId, Long articleId) {

        return articlePraiseDao.getByUser(userId, articleId);
    }
}
