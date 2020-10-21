package com.company.coronavirusReliableInfos_API.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "scientist", cascade = CascadeType.REMOVE)
    private List<Article> articles;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "scientists_specialties",
            joinColumns = @JoinColumn(name = "scientist_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<Specialty> specialties;

    public Scientist(String firstName, String lastName, String link, String country, int rating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.link = link;
        this.country = country;
        this.rating = rating;
        this.articles = new ArrayList<>();
        this.specialties = new HashSet<>();
    }

    public Scientist() {
    }

//    public Scientist(long id, String firstName, String lastName, String link, String country, int rating, List<Article> articles) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.link = link;
//        this.country = country;
//        this.rating = rating;
//        this.articles = articles;
//    }

    public long getId() {
        return id;
    }

    public Scientist setId(long id) {
        this.id = id;
        return this;
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public Scientist setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
        return this;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public Scientist setArticles(List<Article> articles) {
        this.articles = articles;
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

    public void addSpecialty(Specialty specialty) {
        this.specialties.add(specialty);
        specialty.getScientists().add(this);
    }

    public void removeSpecialty(Specialty specialty) {
        this.specialties.remove(specialty);
        specialty.getScientists().remove(this);
    }
}
