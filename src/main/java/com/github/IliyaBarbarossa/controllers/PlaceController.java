package com.github.IliyaBarbarossa.controllers;

import com.github.IliyaBarbarossa.dtos.CityDto;
import com.github.IliyaBarbarossa.dtos.PlaceDto;
import com.github.IliyaBarbarossa.mappers.PlaceMapper;
import com.github.IliyaBarbarossa.model.City;
import com.github.IliyaBarbarossa.model.Excursion;
import com.github.IliyaBarbarossa.model.PlaceType;
import com.github.IliyaBarbarossa.servise.CityService;
import com.github.IliyaBarbarossa.servise.ExcursionService;
import com.github.IliyaBarbarossa.servise.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/place")
@RestController
public class PlaceController {
    private final PlaceService placeService;
    private final ExcursionService excursionService;
    private final CityService cityService;
    private final PlaceMapper placeMapper;

    @GetMapping
    public PlaceDto getPlace(int id) {
        return placeMapper.placeToDto(placeService.getP(id));
    }

    @PutMapping
    public PlaceDto putPlace(String name, Date datedo, String text, PlaceType type, Integer cityId) {
        City city = cityService.get(cityId);
        return placeMapper.placeToDto(placeService.putP(name, datedo, text, type, city));
    }

    @RequestMapping("/type")
    @GetMapping
    public List<PlaceDto> getPlaceByType(PlaceType placeType) {
        return placeMapper.placeToDto(placeService.getPlaceByType(placeType));
    }

    @RequestMapping("/sortname")
    @GetMapping
    public List<PlaceDto> getSortByName() {
        return placeMapper.placeToDto(placeService.getPlaceByName());
    }

    @RequestMapping("/bycity")
    @GetMapping
    public List<PlaceDto> getPlaceByCity(int id) {
        City city = cityService.get(id);
        return placeMapper.placeToDto(placeService.getPlaceByCity(city));
    }

    @RequestMapping("/update")
    @GetMapping
    public PlaceDto updatePlace(int id, String text) {
        return placeMapper.placeToDto(placeService.correctText(id, text));
    }

    @DeleteMapping
    public void delitePlace(int id) {
        placeService.dellitePlace(id);
    }

    @RequestMapping("/all")
    @GetMapping
    public List<PlaceDto> getAll() {
        return placeMapper.placeToDto(placeService.getAll());
    }

    @RequestMapping("/addE")
    @GetMapping
    public PlaceDto addE(int placeid, int exid) {
        Excursion excursion = excursionService.get(exid);
        return placeMapper.placeToDto(placeService.addE(placeid, excursion));
    }
}
