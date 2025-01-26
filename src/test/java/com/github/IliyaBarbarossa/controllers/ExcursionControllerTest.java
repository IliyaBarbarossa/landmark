package com.github.IliyaBarbarossa.controllers;

import com.github.IliyaBarbarossa.config.AppConfig;
import com.github.IliyaBarbarossa.config.TestDatabaseConnectionConfig;
import com.github.IliyaBarbarossa.config.WebConfig;
import com.github.IliyaBarbarossa.dtos.ExcursionDto;
import com.github.IliyaBarbarossa.dtos.PlaceDto;
import com.github.IliyaBarbarossa.model.PlaceType;
import com.github.IliyaBarbarossa.servise.ExcursionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class, TestDatabaseConnectionConfig.class, WebConfig.class})
@WebAppConfiguration
public class ExcursionControllerTest {
    @Autowired
    private ExcursionController excursionController;
    @Autowired
    private PlaceController placeController;

    @Test
    public void get(){
        ExcursionDto excursionDto = excursionController.putExcursion("TEST");
        ExcursionDto excursion = excursionController.getExcursion(excursionDto.getId());
        assertEquals(excursionDto,excursion);
    }


    @Test
    public void put(){
        ExcursionDto excursionDto = excursionController.putExcursion("Test2");
        assertEquals(excursionDto.getName(),"Test2");
        assertNotNull(excursionDto.getId());
    }


    @Test
    public void addP(){
        ExcursionDto excursionDto = excursionController.putExcursion("Test#");
        Date date = new Date();
        PlaceDto placeDto = placeController.putPlace("Test", date, " ", PlaceType.BAR, 1);
        ExcursionDto excursionDto1 = excursionController.addP(excursionDto.getId(), placeDto.getId());
        ExcursionDto excursion = excursionController.getExcursion(excursionDto.getId());
        assertEquals(excursionDto1.getPlaces(),excursion.getPlaces());

    }
}
