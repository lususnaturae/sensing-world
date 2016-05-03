package com.ylitormatech.sensingworld.controller;

import com.ylitormatech.sensingworld.domain.service.SensorService;
import com.ylitormatech.sensingworld.web.SensorForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
@Controller
public class SensorController {

    //Log.Logger logger = Logger.getLogger(this.getClass().getName());


    @Autowired
    SensorService sensorService;

    private static final String USAGE_CHOICES = "usageChoices";

    @ModelAttribute
    public void initValues(Model model) {
        model.addAttribute(USAGE_CHOICES, Arrays.asList(
                "usage.temperature", "usage.location", "usage.speed",
                "usage.direction", "usage.alert", "usage.flag", "usage.multifunction"));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createSensor(Model model) {
        // TODO: Add debug logger
        //logger.debug("Create SensorEntity Controller - GET");
        return "/thyme/sensorcreate";

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createSensor(@ModelAttribute("sensorForm") SensorForm sensorForm, Model model) {

        // TODO: add validator
        BeanUtils.copyProperties(sensorService.create(sensorForm.getName(),sensorForm.getUsage()), sensorForm);
        model.addAttribute("userForm", sensorForm);
        return "/thyme/sensorshow";
    }

}

