package com.ylitormatech.sensingworld.controller;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;
import com.ylitormatech.sensingworld.domain.service.SensorService;
import com.ylitormatech.sensingworld.web.SensorForm;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
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
        // TODO: Add debug logger
        logger.debug("Create SensorEntity Controller - GET");

        return "/thyme/sensorcreate";

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createSensor(@ModelAttribute("sensorForm") SensorForm sensorForm, Model model) {
        logger.debug("Create SensorEntity Controller - POST");
        // TODO: add validator
        SensorEntity obj = sensorService.create(sensorForm.getName(), sensorForm.getUsageToken());
        BeanUtils.copyProperties(obj, sensorForm);
        //model.addAttribute("userForm", sensorForm);
        return "/thyme/sensorshow";
    }

}

