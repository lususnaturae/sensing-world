package com.ylitormatech.sensingworld.controller;

import com.ylitormatech.sensingworld.domain.entity.UserEntity;
import com.ylitormatech.sensingworld.domain.service.UserService;
import com.ylitormatech.sensingworld.web.UserRegisterForm;
import com.ylitormatech.sensingworld.web.WwwUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by marco on 6.5.2016.
 */
@Controller
public class AccountController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/account/register", method = RequestMethod.GET)
    public String register(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "/thyme/userregisterform";
    }

    @RequestMapping(value = "/account/register", method = RequestMethod.POST)
    public String register2(@ModelAttribute("user") UserRegisterForm user, Model model) {
        userService.register(new WwwUser(null, user.getUsername(), user.getPassword(), user.getEmail(), user.getRole()));

        WwwUser u2 = userService.getUser(user.getUsername());
        model.addAttribute("user", u2);
        return "/thyme/userregistered";
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public String register2(@PathVariable("id") Long id, Model model) {
        WwwUser user = userService.getUser(id);
        model.addAttribute("user", user);
        return "/thyme/userregistered";
    }

    @RequestMapping(value = "/account/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userService.getUsers());

        return "/thyme/userlist";
    }

}
