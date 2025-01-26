package com.github.IliyaBarbarossa.repositories;

import com.github.IliyaBarbarossa.model.City;
import com.github.IliyaBarbarossa.model.Place;
import com.github.IliyaBarbarossa.model.PlaceType;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface PlaceRep extends CrudRepository<Place,Integer>, ListPagingAndSortingRepository<Place,Integer> {

    public List<Place> findPlaceByType(PlaceType placeType, Sort sort);

    public List<Place> findPlaceByName(String name);

    public List<Place> findByCity(City city);


    public List<Place> findAll();


}
