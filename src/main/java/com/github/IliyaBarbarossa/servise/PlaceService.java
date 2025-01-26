package com.github.IliyaBarbarossa.servise;

import com.github.IliyaBarbarossa.model.City;
import com.github.IliyaBarbarossa.model.Excursion;
import com.github.IliyaBarbarossa.model.Place;
import com.github.IliyaBarbarossa.model.PlaceType;
import com.github.IliyaBarbarossa.repositories.PlaceRep;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRep placeRep;

    @Transactional
    public Place getP(int id) {
        return placeRep.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Place putP(String name, Date datedo, String text, PlaceType type, City city) {
        return placeRep.save(create(name, datedo, text, type, city));
    }

    @Transactional

    public List<Place> getPlaceByType(PlaceType placeType) {
        return placeRep.findPlaceByType(placeType, Sort.by("name"));
    }

    @Transactional

    public List<Place> getPlaceByName() {
        return placeRep.findAll(Sort.by("name"));
    }

    @Transactional

    public List<Place> getPlaceByCity(City city) {
        return placeRep.findByCity(city);
    }

    @Transactional

    public Place create(String name, Date datedo, String text, PlaceType type, City city) {
        Place place = new Place();
        place.setName(name);
        place.setDatedo(datedo);
        place.setText(text);
        place.setType(type);
        place.setCity(city);
        return place;
    }

    @Transactional

    public Place correctText(int id, String text) {
        return placeRep.findById(id)
                .map(place -> {
                    place.setText(text);
                    return placeRep.save(place);
                })
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional

    public void dellitePlace(int id) {
        placeRep.deleteById(id);
    }

    @Transactional

    public List<Place> getAll() {
        return placeRep.findAll();
    }


    @Transactional
    public Place addE(int placeid, Excursion excursion) {
        return placeRep.findById(placeid)
                .map(place -> {
                    place.setExcursions(place.addE(excursion));
                    return placeRep.save(place);
                }).orElseThrow(NoSuchElementException::new);
    }

}
