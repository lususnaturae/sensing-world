package com.ylitormatech.sensingworld.controller;

import com.ylitormatech.sensingworld.domain.entity.UserEntity;
import com.ylitormatech.sensingworld.domain.service.UserService;
import com.ylitormatech.sensingworld.web.UserRegisterForm;
import com.ylitormatech.sensingworld.web.WwwUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by marco on 6.5.2016.
 */
@Controller
public class AccountController {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    UserService userService;

    @RequestMapping(value = "/account/register", method = RequestMethod.GET)
    public String register(Model model) {
        logger.debug("AccountController /account/register - Get");
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "/thyme/userregisterform";
    }

    @RequestMapping(value = "/account/register", method = RequestMethod.POST)
    public String register2(@Valid @ModelAttribute("user") UserRegisterForm user, BindingResult bindingResult, Model model) {
        logger.debug("AccountController /account/register - Post");
        if(bindingResult.hasErrors()) {
            logger.debug("AccountController /account/register - Post - Validation failure");
            return "/thyme/userregisterform";
        }
        /*
        * Allowed only unique username
        **/

        if(userService.getUserSanityCheck(user.getUsername())) {
            logger.debug("AccountController /account/register - Post - Register new User");
            userService.register(new WwwUser(null, user.getUsername(), user.getPassword(), user.getEmail(), user.getRole()));
            WwwUser u2 = userService.getUser(user.getUsername());
            model.addAttribute("user", u2);
            return "/thyme/userregistered";
        }
        bindingResult.rejectValue("username","Username is exist","Username is in use");
        logger.debug("AccountController /account/register - Post - Username exist " + user.getUsername());
        return "/thyme/userregisterform";
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public String register2(@PathVariable("id") Long id, Model model) {
        logger.debug("AccountController account/{"+ id + "} - Get");
        WwwUser user = userService.getUser(id);
        model.addAttribute("user", user);
        return "/thyme/userregistered";
    }

    @RequestMapping(value = "/account/list", method = RequestMethod.GET)
    public String list(Model model) {
        logger.debug("AccountController /account/list - Get");
        model.addAttribute("users", userService.getUsers());

        return "/thyme/userlist";
    }

    @RequestMapping(value = "/account/login", method = RequestMethod.GET)
    public String login(Model model) {
        logger.debug("AccountController /account/login - Get");

        return "/thyme/loginform";
    }

    @RequestMapping(value = "/account/loggedin", method = RequestMethod.GET)
    public String loggedin(Principal principal, Model model) {
        logger.debug("AccountController /account/loggedin - Get");
        return "/thyme/homeloggedin";
    }

    @RequestMapping(value = "/account/loggedout", method = RequestMethod.GET)
    public String loggedout(Principal principal, Model model) {
        logger.debug("AccountController /account/loggedout - Get");
        return "redirect:/";
    }



}
