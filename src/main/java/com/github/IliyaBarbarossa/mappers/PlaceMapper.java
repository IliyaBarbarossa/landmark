package com.github.IliyaBarbarossa.mappers;

import com.github.IliyaBarbarossa.dtos.CityDto;
import com.github.IliyaBarbarossa.dtos.ExcursionDto;
import com.github.IliyaBarbarossa.dtos.PlaceDto;
import com.github.IliyaBarbarossa.model.City;
import com.github.IliyaBarbarossa.model.Excursion;
import com.github.IliyaBarbarossa.model.Place;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlaceMapper {
    @Mapping(target = "excursions", ignore = true)
    PlaceDto placeToDto(Place place);

    List<PlaceDto> placeToDto(List<Place> list);

    @Mapping(target = "places", ignore = true)
    CityDto cityToDto(City city);

    @Mapping(target = "places", ignore = true)
    ExcursionDto excursionToDto(Excursion excursion);
}
