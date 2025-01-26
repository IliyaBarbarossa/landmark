package com.github.IliyaBarbarossa.mappers;

import com.github.IliyaBarbarossa.dtos.CityDto;
import com.github.IliyaBarbarossa.dtos.PlaceDto;
import com.github.IliyaBarbarossa.model.City;
import com.github.IliyaBarbarossa.model.Place;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CityMapper {
//     default CityDto cityToCityDto(City city){
//          CityDto cityDto = new CityDto();
//          cityDto.setId(city.getId());
//          cityDto.setName(city.getName());
//          cityDto.setPeople(city.getPeople());
//          cityDto.setMetro(city.getMetro());
//          if(city.getPlaces()!=null) {
//
//               List<String> listPlace = new ArrayList<>();
//               List<Place> lp = city.getPlaces();
//               for (int i = 0; i < lp.size(); i++) {
//                    listPlace.add(lp.get(i).getName());
//               }
//               cityDto.setPlaces(listPlace);
//          }else {
//               cityDto.setPlaces(null);
//          }
//          return cityDto;
//     }

     @Mapping(target = "places", ignore = true)
     CityDto cityToDto(City city);
     List<CityDto> cityToDto(List<City> list);
     @Mapping(target = "city",ignore = true )
     @Mapping(target = "excursions",ignore = true )
     PlaceDto placeToDto(Place place);
}
