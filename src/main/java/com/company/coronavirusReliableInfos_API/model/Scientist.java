package com.company.coronavirusReliableInfos_API.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "scientists")
public class Scientist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String link;

    private String country;

    private int rating;

    // INSERT INTO scientists(country, first_name, last_name, link, rating) VALUES('nowhere', 'john', 'smith', 'js.com', '4');

    public Scientist(String firstName, String lastName, String link, String country, int rating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.link = link;
        this.country = country;
        this.rating = rating;
    }

    public Scientist() {
    }

    public Scientist(long id, String firstName, String lastName, String link, String country, int rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.link = link;
        this.country = country;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public Scientist setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Scientist setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Scientist setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getLink() {
        return link;
    }

    public Scientist setLink(String link) {
        this.link = link;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Scientist setCountry(String country) {
        this.country = country;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public Scientist setRating(int rating) {
        this.rating = rating;
        return this;
    }
}
