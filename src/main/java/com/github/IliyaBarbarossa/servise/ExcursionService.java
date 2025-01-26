package com.github.IliyaBarbarossa.servise;


import com.github.IliyaBarbarossa.model.Excursion;
import com.github.IliyaBarbarossa.model.Place;
import com.github.IliyaBarbarossa.repositories.ExcursRep;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ExcursionService {

    private final ExcursRep excursRep;
    @Transactional

    public Excursion get(int id) {
        return excursRep.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional

    public Excursion putEx(String name, List<Place> places) {
        return excursRep.save(create(name, places));
    }

    @Transactional

    public Excursion create(String name, List<Place> places) {
        Excursion excursion = new Excursion();
        excursion.setName(name);
        excursion.setPlaces(places);
        return excursion;
    }

    @Transactional
    public List<Excursion> getall() {
        return excursRep.findAll();
    }

    @Transactional
    public Excursion addPlace(int exid, Place place) {
        return excursRep.findById(exid)
                .map(excursion -> {
                    List<Place> places = new ArrayList<>(excursion.getPlaces());
                    places.add(place);
                    excursion.setPlaces(places);
                    return excursRep.save(excursion);
                }).orElseThrow(NoSuchElementException::new);
    }
}
