package com.ylitormatech.sensingworld.controller;

import com.ylitormatech.sensingworld.domain.entity.Sensor;
import com.ylitormatech.sensingworld.domain.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
@Controller
public class SensorController {

    //Log.Logger logger = Logger.getLogger(this.getClass().getName());


    @Autowired
    SensorService sensorService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createSensor(Model model) {
        //logger.debug("Create Sensor Controller - GET");
        model.addAttribute("sensor", new Sensor());
        return "/thyme/create";

    }

}

