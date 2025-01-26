package com.github.IliyaBarbarossa.controllers;

import com.github.IliyaBarbarossa.dtos.CityDto;
import com.github.IliyaBarbarossa.mappers.CityMapper;
import com.github.IliyaBarbarossa.servise.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/city")
@RestController
public class CityController {
    private final CityService cityService;
    private final CityMapper cityMapper;

    @GetMapping
    public CityDto getCity(int id) {
        return cityMapper.cityToDto(cityService.get(id));
    }

    @PutMapping
    public CityDto putCity(String name, int people, boolean metro){
        return cityMapper.cityToDto(cityService.putCity(name, people, metro));
    }

    @RequestMapping("update")
    @GetMapping
    public CityDto update(int id, int people, boolean metro){
        return cityMapper.cityToDto(cityService.updateCity(id, people, metro));
    }

    @RequestMapping("all")
    @GetMapping
    public List<CityDto> getall(){
        return cityMapper.cityToDto(cityService.getall());
    }

//    @RequestMapping("addP")
//    @GetMapping
//    public CityDto addP(int cityid, int placeid){
//        return cityMapper.cityToDto(cityService.addPlace(cityid,placeid));
//    }


}
