package com.github.IliyaBarbarossa.controllers;

import com.github.IliyaBarbarossa.config.AppConfig;
import com.github.IliyaBarbarossa.config.TestDatabaseConnectionConfig;
import com.github.IliyaBarbarossa.config.WebConfig;
import com.github.IliyaBarbarossa.dtos.ExcursionDto;
import com.github.IliyaBarbarossa.dtos.PlaceDto;
import com.github.IliyaBarbarossa.model.PlaceType;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class, TestDatabaseConnectionConfig.class, WebConfig.class})
@WebAppConfiguration
public class PlaceControllerTest {

    @Autowired
    private PlaceController placeController;


    @Test
    public void get() {
        Date date = new Date();
        PlaceDto placeDto = placeController.putPlace("TestP", date, " ", PlaceType.BAR, 1);
        PlaceDto place = placeController.getPlace(placeDto.getId());
        assertEquals(place, placeDto);
        assertEquals(DateUtils.truncate(placeDto.getDatedo(), Calendar.SECOND),
                DateUtils.truncate(place.getDatedo(), Calendar.SECOND));
    }


    @Test
    public void put() {
        Date date = new Date();
        PlaceDto place = placeController.putPlace("TestP2", date, " ", PlaceType.BAR, 1);
        PlaceDto placeDto = placeController.getPlace(place.getId());
        assertEquals(placeDto.getName(), "TestP2");
        assertEquals(DateUtils.truncate(placeDto.getDatedo(), Calendar.SECOND),
                DateUtils.truncate(place.getDatedo(), Calendar.SECOND));

        assertEquals(placeDto.getText(), place.getText());
        assertEquals(placeDto.getType(), place.getType());
        assertNotNull(place.getId());
    }


    @Test
    public void updateText() {
        Date date = new Date(01 / 01 / 2023);
        PlaceDto place = placeController.putPlace("TestP3", date, " ", PlaceType.BAR, 1);
        PlaceDto placeDto = placeController.updatePlace(place.getId(), "Text");
        assertEquals(placeDto.getText(), "Text");
    }
}