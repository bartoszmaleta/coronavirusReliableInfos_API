package com.company.coronavirusReliableInfos_API.controller;

import com.company.coronavirusReliableInfos_API.LoggerConfiguration;
import com.company.coronavirusReliableInfos_API.exceptions.ResourceNotFoundException;
import com.company.coronavirusReliableInfos_API.model.Article;
import com.company.coronavirusReliableInfos_API.repository.ArticleRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {

    private static final Logger log = Logger.getLogger(ArticleController.class.getName());

    @Autowired
    private ArticleRepository articleRepository;

    // GET all articles
    @GetMapping("/articles")
    public List<Article> getAllArticles() {
        String messageToLog = String.format("%s, GET all articles invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return this.articleRepository.findAll();
    }

    // GET article by id
    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable(value = "id") Long articleId) throws ResourceNotFoundException {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ResourceNotFoundException("article not found for this id = " + articleId));

        String messageToLog = String.format("%s, GET article by id invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return ResponseEntity.ok().body(article);
    }

    // SAVE article
    @PostMapping(path = "/articles", consumes = "application/json")
    public Article createArticle(@Valid @RequestBody Article article) {
        String messageToLog = String.format("%s, SAVE article invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return this.articleRepository.save(article);
    }

    // UPDATE article
    @PutMapping("/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable(value = "id") Long articleId, @Valid @RequestBody Article articleDetails) throws ResourceNotFoundException {
        Article article = articleRepository.findById(articleId).orElseThrow( () ->
                new ResourceNotFoundException("article not found for this id = " + articleId));

        article.setTitle(articleDetails.getTitle());
        article.setLink(articleDetails.getLink());
        article.setCategory(articleDetails.getCategory());
        article.setJournal(articleDetails.getJournal());
        article.setRating(articleDetails.getRating());
        article.setScientist(articleDetails.getScientist());

        String messageToLog = String.format("%s, UPDATE article invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        return ResponseEntity.ok(this.articleRepository.save(article));
    }

    // DELETE article
    @DeleteMapping("/articles/{id}")
    public Map<String, Boolean> deleteArticle(@PathVariable (value = "id") Long articleId) throws ResourceNotFoundException {
        Article article = articleRepository.findById(articleId).orElseThrow( () ->
                new ResourceNotFoundException("article not found for this id = " + articleId));

        this.articleRepository.delete(article);

        String messageToLog = String.format("%s, DELETE article invoked", LoggerConfiguration.dtf.format(LocalDateTime.now()));
        log.info(messageToLog);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
