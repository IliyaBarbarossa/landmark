package com.github.IliyaBarbarossa.servise;

import com.github.IliyaBarbarossa.model.City;
import com.github.IliyaBarbarossa.repositories.CityRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRep cityRep;

    @Transactional
    public City putCity(String name, int people, boolean metro) {
        return cityRep.save(create(name, people, metro));
    }

    @Transactional
    public City get(int id) {
        return cityRep.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional

    public City create(String name, int people, boolean metro) {
        City city = new City();
        city.setName(name);
        city.setPeople(people);
        city.setMetro(metro);
        city.setPlaces(new ArrayList<>());
        return city;
    }

    @Transactional

    public City updateCity(int id, int people, boolean metro) {
        return cityRep.findById(id)
                .map(city -> {
                    city.setPeople(people);
                    city.setMetro(metro);
                    return cityRep.save(city);

                })
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public List<City> getall() {
        return cityRep.findAll();
    }


//    @Transactional
//    public City addPlace(int citiid, int placeid) {
//        Place place = placeRep.findById(placeid).orElseThrow(NoSuchElementException::new);
//        return cityRep.findById(citiid)
//                .map(city -> {
//                    city.setPlaces(city.addP(place));
//                    return cityRep.save(city);
//                }).orElseThrow(NoSuchElementException::new);
//    }


}




