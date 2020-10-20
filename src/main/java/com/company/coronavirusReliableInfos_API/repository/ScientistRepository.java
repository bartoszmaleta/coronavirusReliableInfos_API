package com.company.coronavirusReliableInfos_API.repository;

import com.company.coronavirusReliableInfos_API.model.Scientist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScientistRepository extends JpaRepository<Scientist, Long> {

}
