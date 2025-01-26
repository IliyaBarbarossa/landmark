package com.github.IliyaBarbarossa.dtos;

import com.github.IliyaBarbarossa.model.City;
import com.github.IliyaBarbarossa.model.Excursion;
import com.github.IliyaBarbarossa.model.PlaceType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"excursions", "datedo"})
@ToString(exclude = "excursions")
public class PlaceDto {
    private Integer id;
    private String name;
    private Date datedo;
    private String text;
    private PlaceType type;
    private CityDto city;
    private List<ExcursionDto> excursions;
}
