package com.company.coronavirusReliableInfos_API.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JoinColumn(name = "scientist_id")
    @JsonIgnore
    private Scientist scientist;

    private String title;

    private String link;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "journal_id")
    private Journal journal;

    public Article(Scientist scientist, String title, String link, int rating, Category category, Journal journal) {
        this.scientist = scientist;
        this.title = title;
        this.link = link;
        this.rating = rating;
        this.category = category;
        this.journal = journal;
    }

    //    // TODO: how to have entire Category object here?
//    @Column(name = "category_id")
//    private int category_id;
//
//    // TODO: how to have entire Journal object here?
//    @Column(name = "journal_id")
//    private int journal_id;

//    public Article(int scientistId, String title, String link, int rating, int category_id, int journal_id) {
//        this.scientistId = scientistId;
//        this.title = title;
//        this.link = link;
//        this.rating = rating;
//        this.category_id = category_id;
//        this.journal_id = journal_id;
//    }

}
