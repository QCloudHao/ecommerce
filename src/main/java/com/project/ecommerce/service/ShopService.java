package com.project.ecommerce.service;

import com.project.ecommerce.bean.Article;
import com.project.ecommerce.bean.ArticleType;
import com.project.ecommerce.utils.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by qyh on 2019/6/7.
 */
public interface ShopService {
    List<ArticleType> getArticleTypes();

    Map<String,Object> login(String loginName, String passWord);

    List<ArticleType> loadFirstArticleTypes();

    List<ArticleType> loadSecondTypes(String typeCode);

    List<Article> searchArticles(String typeCode, String secondType, String title, Pager pager);

    void deleteById(String id);

    Article getArticleById(String id);

    void updateArticle(Article article);

    void saveArticle(Article article);
}
