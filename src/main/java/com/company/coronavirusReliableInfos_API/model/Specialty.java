package com.company.coronavirusReliableInfos_API.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "specialties")
public class Specialty {

    // TODO: remove it? I don't need it! Cause sth?
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToMany(mappedBy = "specialties")
    @JsonIgnore
//    @JsonBackReference
    private Set<Scientist> scientists;

    public Specialty(String name) {
        this.name = name;
        this.scientists = new HashSet<>();
    }

}
