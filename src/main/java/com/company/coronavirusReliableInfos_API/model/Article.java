package com.company.coronavirusReliableInfos_API.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "scientist_id")
    @JsonIgnore
//    @JsonManagedReference
    private Scientist scientist;

    private String title;

    private String link;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "category_id")
//    @JsonManagedReference
    private Category category;

    @ManyToOne
    @JoinColumn(name = "journal_id")
//    @JsonManagedReference
    private Journal journal;

    public Article(Scientist scientist, String title, String link, int rating, Category category, Journal journal) {
        this.scientist = scientist;
        this.title = title;
        this.link = link;
        this.rating = rating;
        this.category = category;
        this.journal = journal;
    }

//    public Article(String title, String link, int rating) {
//        this.scientist = null;
//        this.title = title;
//        this.link = link;
//        this.rating = rating;
//        this.category = null;
//        this.journal = null;
//    }

}
