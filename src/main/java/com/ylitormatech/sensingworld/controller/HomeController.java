package com.ylitormatech.sensingworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
@Controller
public class HomeController {

    @RequestMapping(value = {"/", ""})
    public String homejsp() {
        return "/jsp/home";
    }
}
