package com.company.coronavirusReliableInfos_API.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Article> articles;

    public Category(String name, List<Article> articles) {
        this.name = name;
        this.articles = articles;
    }
}
