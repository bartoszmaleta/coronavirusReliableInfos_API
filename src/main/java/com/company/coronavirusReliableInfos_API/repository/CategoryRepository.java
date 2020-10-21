package com.company.coronavirusReliableInfos_API.repository;

import com.company.coronavirusReliableInfos_API.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
