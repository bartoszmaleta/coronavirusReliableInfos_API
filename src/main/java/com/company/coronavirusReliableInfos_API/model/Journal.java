package com.company.coronavirusReliableInfos_API.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "journals")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String link;

    @OneToMany(mappedBy = "journal")
    @JsonIgnore
//    @JsonBackReference
    private List<Article> articles;

    public Journal(String name, String link) {
        this.name = name;
        this.link = link;
        this.articles = new ArrayList<>();
    }
}
