package com.company.coronavirusReliableInfos_API.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "specialties")
public class Specialty {

    // TODO: remove it? I don't need it! Cause sth?
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToMany(mappedBy = "specialties")
    @JsonIgnore
    private Set<Scientist> scientists;

    public Specialty(String name) {
        this.name = name;
        this.scientists = new HashSet<>();
    }

}
