package com.github.IliyaBarbarossa.controllers;

import com.github.IliyaBarbarossa.config.AppConfig;
import com.github.IliyaBarbarossa.config.TestDatabaseConnectionConfig;
import com.github.IliyaBarbarossa.config.WebConfig;
import com.github.IliyaBarbarossa.dtos.CityDto;
import lombok.RequiredArgsConstructor;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class, TestDatabaseConnectionConfig.class, WebConfig.class})
@WebAppConfiguration
public class CityControllerTest {

    @Autowired
    private CityController cityController;


    @Test
    public void putCity(){
        CityDto cityDto = cityController.putCity("KND", 35000, Boolean.TRUE);
        assertEquals(cityDto.getName(),"KND");
        assertEquals(cityDto.getPeople(),35000);
        assertEquals(cityDto.getMetro(),Boolean.TRUE);
        assertNotNull(cityDto.getId());
    }

    @Test
    public void getCity(){
        CityDto cityDto = cityController.putCity("KND", 35000, Boolean.TRUE);
        CityDto city = cityController.getCity(cityDto.getId());
        assertEquals(city,cityDto);
    }

    @Test
    public void updateCity(){
        CityDto cityDto = cityController.putCity("KND", 35000, Boolean.TRUE);
        CityDto update = cityController.update(cityDto.getId(), 3000, Boolean.FALSE);
        CityDto get = cityController.getCity(update.getId());
        assertEquals(update, get);
        assertEquals(update.getName(),"KND");
        assertEquals(update.getPeople(),3000);
        assertEquals(update.getMetro(),Boolean.FALSE);
        assertEquals(cityDto.getId(),update.getId());
    }
}
