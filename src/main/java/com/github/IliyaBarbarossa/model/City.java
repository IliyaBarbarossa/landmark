package com.github.IliyaBarbarossa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer people;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityid")
    private List<Place> places;
    private Boolean metro;

//    public List<Place> addP(Place place){
//         places.add(place);
//         return places;
//    }
}

