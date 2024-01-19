package com.dev.sav.service.implementation;

import com.dev.sav.model.Article;
import com.dev.sav.repository.ArticleRepository;
import com.dev.sav.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleByReference(String reference) {
        return articleRepository.findById(reference).orElse(null);
    }

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void updateArticle(String reference, Article updatedArticle) {
        Article existingArticle = articleRepository.findById(reference).orElse(null);
        if (existingArticle != null) {
            existingArticle.setNom(updatedArticle.getNom());
            existingArticle.setDescription(updatedArticle.getDescription());
            existingArticle.setPrixUnitaire(updatedArticle.getPrixUnitaire());
            articleRepository.save(existingArticle);
        }
    }


    @Override
    public void deleteArticle(String reference) {
        articleRepository.deleteById(reference);
    }
}
