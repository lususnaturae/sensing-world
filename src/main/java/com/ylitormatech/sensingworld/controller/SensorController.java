package com.ylitormatech.sensingworld.controller;

import com.ylitormatech.sensingworld.domain.entity.SensorEntity;
import com.ylitormatech.sensingworld.domain.service.SensorService;
import com.ylitormatech.sensingworld.domain.service.UserService;
import com.ylitormatech.sensingworld.web.SensorForm;
import com.ylitormatech.sensingworld.web.WwwUser;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
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

    @Autowired
    UserService userService;

    private static final String USAGE_CHOICES = "usageChoices";

    @ModelAttribute
    public void initValues(Model model) {
        model.addAttribute(USAGE_CHOICES, Arrays.asList(
                "usagetoken.temperature", "usagetoken.location", "usagetoken.speed",
                "usagetoken.direction", "usagetoken.alert", "usagetoken.flag", "usagetoken.multifunction"));
    }

    @RequestMapping(value = "/sensors/new", method = RequestMethod.GET)
    public String createSensor(Locale locale, @AuthenticationPrincipal WwwUser user, Model model) {
        logger.debug("Create SensorEntity Controller - GET");
        SensorEntity sensor = new SensorEntity();
        sensor.setUser(userService.getUserEntity(user.getId()));
        model.addAttribute("sensorForm", sensor);
        return "/thyme/sensorform";
    }

    @RequestMapping(value = "/sensors/new", method = RequestMethod.POST)
    public String createSensor(@ModelAttribute("sensorForm") SensorForm sensorForm, @AuthenticationPrincipal WwwUser user, Model model) {
        logger.debug("Create SensorEntity Controller - POST");
        // TODO: add validator
        SensorEntity sensorEntity = sensorService.add(sensorForm.getName(), sensorForm.getUsagetoken(), user);
        return "redirect:/sensors/" + sensorEntity.getId() + "/show";
    }

    @RequestMapping(value = "/sensors/list", method = RequestMethod.GET)
    public String listSensors(Locale locale, @AuthenticationPrincipal WwwUser user, Model model) {
        logger.debug("List SensorEntity Controller - GET");
        // TODO: add validator
        model.addAttribute("sensorForms", sensorService.findMySensors(user.getId()));
        return "/thyme/sensorlist";
    }

    @RequestMapping(value = "/sensors/{id}/show", method = RequestMethod.GET)
    public String showSensor(@PathVariable("id") Integer id, @AuthenticationPrincipal WwwUser user, Model model) {
        logger.debug("Show SensorEntity Controller - GET");
        SensorEntity se = sensorService.findMySensor(id, user.getId());
        if (se.getApikey() == null) {
            se.setApikey("NO KEY");
        }
        model.addAttribute("sensorForm", se);
        return "/thyme/sensorshow";
    }

    @RequestMapping(value = "/sensors/{id}/update", method = RequestMethod.GET)
    public String updateSensor(@PathVariable("id") Integer id, @AuthenticationPrincipal WwwUser user, Model model) {
        logger.debug("Update SensorEntity Controller - GET");

        model.addAttribute("sensorForm", sensorService.findMySensor(id, user.getId()));
        return "/thyme/sensorform";
    }

    @RequestMapping(value = "/sensors/{id}/update", method = RequestMethod.POST)
    public String updateSensor(@ModelAttribute("sensorForm") SensorForm sensorForm, @AuthenticationPrincipal WwwUser user, @PathVariable("id") Integer id, Model model) {
        logger.debug("Update SensorEntity Controller - POST");
        // TODO: add validator
        SensorEntity sensor = new SensorEntity();
        BeanUtils.copyProperties(sensorForm, sensor);
        sensorService.update(sensor);
        return "redirect:/sensors/{id}/show";
    }


    @RequestMapping(value = "/sensors/{id}/deleteconfirmation", method = RequestMethod.GET)
    public String deleteonfirmationSensor( @PathVariable("id") Integer id, @AuthenticationPrincipal WwwUser user, Model model) {
        logger.debug("Update SensorEntity Controller - GET");

        model.addAttribute("sensorForm", sensorService.findMySensor(id, user.getId()));
        return "/thyme/sensordeleteconfirmation";
    }

    @RequestMapping(value = "/sensors/{id}/delete", method = RequestMethod.GET)
    public String deleteSensor( @PathVariable("id") Integer id, @AuthenticationPrincipal WwwUser user, Model model) {
        logger.debug("Update SensorEntity Controller - GET");

        sensorService.removeMySensor(id, user.getId());
        return "redirect:/sensors/list";
    }


}

