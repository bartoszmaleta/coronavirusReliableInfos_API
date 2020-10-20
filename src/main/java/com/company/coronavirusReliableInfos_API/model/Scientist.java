package com.company.coronavirusReliableInfos_API.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scientists")
public class Scientist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String lastName;

    private String link;

    private String country;

    private int rating;

    public Scientist(String firstName, String lastName, String link, String country, int rating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.link = link;
        this.country = country;
        this.rating = rating;
    }

}
