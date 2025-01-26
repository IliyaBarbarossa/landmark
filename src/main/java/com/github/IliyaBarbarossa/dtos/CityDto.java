package com.github.IliyaBarbarossa.dtos;

import com.github.IliyaBarbarossa.model.Place;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@EqualsAndHashCode(exclude = {"places"})
public class CityDto {
    private Integer id;
    private String name;
    private Integer people;
    private List<PlaceDto> places;
    private Boolean metro;
}
