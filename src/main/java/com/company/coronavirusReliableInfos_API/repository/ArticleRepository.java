package com.company.coronavirusReliableInfos_API.repository;

import com.company.coronavirusReliableInfos_API.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository  extends JpaRepository<Article, Long> {
}
