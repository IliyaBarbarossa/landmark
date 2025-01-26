package com.github.IliyaBarbarossa.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@EqualsAndHashCode(exclude = {"places"})
public class ExcursionDto {
    private Integer id;
    private String name;
    private List<PlaceDto> places;
}
