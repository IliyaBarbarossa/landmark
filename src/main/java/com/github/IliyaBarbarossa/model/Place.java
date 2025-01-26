package com.github.IliyaBarbarossa.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date datedo;
    private String text;
    @Column(columnDefinition = "text")
    private PlaceType type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cityid" )
    private City city;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "places")
    private List<Excursion> excursions;

    public List<Excursion> addE(Excursion excursion){
        excursions.add(excursion);
        return excursions;
    }

}
