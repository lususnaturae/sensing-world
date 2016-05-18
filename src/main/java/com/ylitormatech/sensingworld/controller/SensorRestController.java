package com.ylitormatech.sensingworld.controller;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;
import com.ylitormatech.sensingworld.domain.service.SensorService;
import com.ylitormatech.sensingworld.domain.service.UserService;
import com.ylitormatech.sensingworld.web.SensorForm;
import com.ylitormatech.sensingworld.web.WwwUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
@RestController
@RequestMapping("/api/sensors")
public class SensorRestController {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    SensorService sensorService;

    @Autowired
    UserService userService;


    private static final String USAGE_CHOICES = "usageChoices";

    @ModelAttribute
    public void initValues(Model model) {
        model.addAttribute(USAGE_CHOICES, Arrays.asList(
                "usagetoken.temperature", "usagetoken.location", "usagetoken.speed",
                "usagetoken.direction", "usagetoken.alert", "usagetoken.flag", "usagetoken.multifunction"));
    }

    @CrossOrigin(origins = "https://jsfiddle.net")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<SensorEntity>> listSensors() {
        logger.debug("REST List SensorEntity Controller - GET");
        List<SensorEntity> sensors = sensorService.findAll();
        if(sensors.isEmpty()) {
            return new ResponseEntity<List<SensorEntity>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<SensorEntity>>(sensors,HttpStatus.OK);
    }

    @CrossOrigin(origins = "https://jsfiddle.net")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<SensorEntity> showSensor(@PathVariable("id") Integer id) {
        logger.debug("Show SensorEntity Controller - GET");
        SensorEntity sensor = sensorService.find(id);
        if (sensor==null){
            return new ResponseEntity<SensorEntity>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SensorEntity>(sensor, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Void> createSensor(@RequestBody SensorEntity sensor,    UriComponentsBuilder ucBuilder) {
        logger.debug("Create SensorEntity Controller - POST");
        // TODO: add validator
        SensorEntity ss = sensorService.find(sensor.getName());
        if (sensorService.find(sensor.getName()) != null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        sensorService.add(sensor);
        HttpHeaders headers = new HttpHeaders();
         headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(sensor.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}

