package com.company.coronavirusReliableInfos_API.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "journals")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String link;

    public Journal() {
    }

    public Journal(long id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
    }

    public Journal(String name, String link) {
        this.name = name;
        this.link = link;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
