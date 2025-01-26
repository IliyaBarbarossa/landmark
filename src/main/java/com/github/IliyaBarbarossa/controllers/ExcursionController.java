package com.github.IliyaBarbarossa.controllers;

import com.github.IliyaBarbarossa.dtos.ExcursionDto;
import com.github.IliyaBarbarossa.mappers.ExcursionMapper;
import com.github.IliyaBarbarossa.model.Place;
import com.github.IliyaBarbarossa.servise.ExcursionService;
import com.github.IliyaBarbarossa.servise.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/excursion")
@RestController
public class ExcursionController {
    private final ExcursionService excursionService;
    private final PlaceService placeService;
    private final ExcursionMapper excursionMapper;

    @GetMapping
    public ExcursionDto getExcursion(int id) {
        return excursionMapper.excursionToDto(excursionService.get(id));
    }

    @PutMapping
    public ExcursionDto putExcursion(String name) {
        return excursionMapper.excursionToDto(excursionService.putEx(name, new ArrayList<>()));
    }

    @RequestMapping("/all")
    @GetMapping
    public List<ExcursionDto> getall() {
        return excursionMapper.excursionToDto(excursionService.getall());
    }



    @RequestMapping("/addP")
    @GetMapping
    public ExcursionDto addP(int exid, int placeid) {
        Place place = placeService.getP(placeid);
        return excursionMapper.excursionToDto(excursionService.addPlace(exid, place));
    }


}
