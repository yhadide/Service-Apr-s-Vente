package com.dev.sav.service;

import com.dev.sav.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles();

    Article getArticleByReference(String reference);

    void saveArticle(Article article);

    void updateArticle(String reference, Article updatedArticle);

    void deleteArticle(String reference);
}
