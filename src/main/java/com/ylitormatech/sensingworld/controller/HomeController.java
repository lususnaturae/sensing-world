package com.ylitormatech.sensingworld.controller;

import com.ylitormatech.sensingworld.domain.entity.UserEntity;
import com.ylitormatech.sensingworld.web.WwwUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by Marco Ylitörmä on 02/05/16.
 */
@Controller
public class HomeController {

    @RequestMapping(value = {"/", ""})
    public String homePage(Principal principal, @AuthenticationPrincipal WwwUser user) {
        return principal != null ? "/thyme/homeloggedin" : "/thyme/home";
    }
}
