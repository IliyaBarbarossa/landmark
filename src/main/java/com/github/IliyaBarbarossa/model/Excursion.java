package com.github.IliyaBarbarossa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "excursion")
public class Excursion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "svyaz", joinColumns = @JoinColumn(name = "exid"), inverseJoinColumns = @JoinColumn(name = "placeid"))
    private List<Place> places;

}
