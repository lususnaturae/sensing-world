package com.ylitormatech.sensingworld.controller;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;
import com.ylitormatech.sensingworld.domain.service.SensorService;
import com.ylitormatech.sensingworld.web.SensorForm;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
@Controller
public class SensorController {

    Logger logger = Logger.getLogger(this.getClass().getName());


    @Autowired
    SensorService sensorService;

    private static final String USAGE_CHOICES = "usageChoices";

    @ModelAttribute
    public void initValues(Model model) {
        model.addAttribute(USAGE_CHOICES, Arrays.asList(
                "usagetoken.temperature", "usagetoken.location", "usagetoken.speed",
                "usagetoken.direction", "usagetoken.alert", "usagetoken.flag", "usagetoken.multifunction"));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createSensor(Locale locale, Model model) {
        logger.debug("Create SensorEntity Controller - GET");
        return "/thyme/sensorcreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createSensor(@ModelAttribute("sensorForm") SensorForm sensorForm, Model model) {
        logger.debug("Create SensorEntity Controller - POST");
        // TODO: add validator
        SensorEntity sensorEntity = sensorService.create(sensorForm.getName(), sensorForm.getUsagetoken());
        BeanUtils.copyProperties(sensorEntity, sensorForm);
        return "/thyme/sensorshow";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listSensors(Locale locale, Model model) {
        logger.debug("List SensorEntity Controller - GET");
        // TODO: add validator
        List<SensorEntity> sensors = sensorService.findAll();
        model.addAttribute("sensors", sensors);
        return "/thyme/sensorlist";
    }


    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showSensor( @PathVariable("id") Integer id, Model model) {
        logger.debug("Show SensorEntity Controller - GET");

        SensorEntity sensor = sensorService.find(id);
        model.addAttribute("sensorForm", sensor);
        return "/thyme/sensorshow";
    }

}

