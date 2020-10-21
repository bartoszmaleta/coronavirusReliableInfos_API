package com.company.coronavirusReliableInfos_API.repository;

import com.company.coronavirusReliableInfos_API.model.Category;
import com.company.coronavirusReliableInfos_API.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository  extends JpaRepository<Journal, Long> {
}
