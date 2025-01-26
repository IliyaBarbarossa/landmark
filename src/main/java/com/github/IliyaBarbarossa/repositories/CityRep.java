package com.github.IliyaBarbarossa.repositories;

import com.github.IliyaBarbarossa.model.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRep extends CrudRepository<City, Integer> {
    @Override
    List<City> findAll();

}
