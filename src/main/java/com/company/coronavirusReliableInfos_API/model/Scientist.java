package com.company.coronavirusReliableInfos_API.model;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Getter;
import lombok.Setter;

//import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Getter
@Setter
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

    public Scientist() {}

    public Scientist(String firstName, String lastName, String link, String country, int rating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.link = link;
        this.country = country;
        this.rating = rating;
    }



    @Override
    public String toString() {
        return "Scientist{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", link='" + link + '\'' +
                ", country='" + country + '\'' +
                ", rating=" + rating +
                '}';
    }
}
