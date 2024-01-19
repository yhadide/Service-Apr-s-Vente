package com.dev.sav.controller;

import com.dev.sav.model.Article;
import com.dev.sav.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String getAllArticles(Model model) {
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "articles/article";
    }

    @GetMapping("/{reference}")
    public String getArticleByReference(@PathVariable String reference, Model model) {
        Article article = articleService.getArticleByReference(reference);
        if (article != null) {
            model.addAttribute("article", article);
            return "articles/article";
        } else {
            return "articles/error";
        }
    }

    @GetMapping("/add")
    public String showArticleForm(Model model) {
        model.addAttribute("article", new Article());
        return "articles/article";
    }

    @PostMapping("/add")
    public String saveArticle(@ModelAttribute Article article) {
        articleService.saveArticle(article);
        return "redirect:/articles";
    }

    @GetMapping("/edit/{reference}")
    public String showEditArticleForm(@PathVariable String reference, Model model) {
        Article article = articleService.getArticleByReference(reference);
        if (article != null) {
            model.addAttribute("article", article);
            return "articles/article";
        } else {
            return "articles/error";
        }
    }

    @PostMapping("/edit/{reference}")
    public String updateArticle(@PathVariable String reference, @ModelAttribute Article updatedArticle) {
        articleService.updateArticle(reference, updatedArticle);
        return "redirect:/articles";
    }

    @PostMapping("/{reference}")
    public String deleteArticle(@PathVariable String reference) {
        articleService.deleteArticle(reference);
        return "redirect:/articles";
    }
}
