package com.company.coronavirusReliableInfos_API.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int scientist_id;

    private Scientist scientist;

    private String title;

    private String link;

    private int rating;

    private int category_id;

    private int journal_id;

    // TODO:
}
