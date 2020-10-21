package com.company.coronavirusReliableInfos_API.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "journals")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String link;

    @OneToMany(mappedBy = "journal")
    @JsonIgnore
    private List<Article> articles;

    public Journal(String name, String link, List<Article> articles) {
        this.name = name;
        this.link = link;
        this.articles = articles;
    }
}
