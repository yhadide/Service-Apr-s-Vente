package com.dev.sav.controller;

import com.dev.sav.model.Article;
import com.dev.sav.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{reference}")
    public Article getArticleByReference(@PathVariable String reference) {
        return articleService.getArticleByReference(reference);
    }

    @PostMapping
    public void saveArticle(@RequestBody Article article) {
        articleService.saveArticle(article);
    }

    @PutMapping("/{reference}")
    public void updateArticle(@PathVariable String reference, @RequestBody Article updatedArticle) {
        articleService.updateArticle(reference, updatedArticle);
    }

    @DeleteMapping("/{reference}")
    public void deleteArticle(@PathVariable String reference) {
        articleService.deleteArticle(reference);
    }
}
