package com.ylitormatech.sensingworld.controller;

import com.ylitormatech.sensingworld.web.UserRegisterForm;
import com.ylitormatech.sensingworld.web.UserUpdateForm;
import com.ylitormatech.sensingworld.web.WwwUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    ControllerFacade controllerFacade;

    @RequestMapping(value = "/account/register", method = RequestMethod.GET)
    public String register(Model model) {
        logger.debug("AccountController /account/register - Get");
        return controllerFacade.AccountRegisterGet(model);
    }

    @RequestMapping(value = "/account/register", method = RequestMethod.POST)
    public String register2(@Valid @ModelAttribute("user") UserRegisterForm user, BindingResult bindingResult, Model model) {
        logger.debug("AccountController /account/register - Post");
        return controllerFacade.AccountRegisterPost(user,bindingResult,model);
    }

    @RequestMapping(value = "/account/profile", method = RequestMethod.GET)
    public String profile(@AuthenticationPrincipal WwwUser user, Model model) {
        logger.debug("AccountController account/profile - Get");
        return controllerFacade.AccountProfileGet(user, model);
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public String register2(@PathVariable("id") Integer id, @AuthenticationPrincipal WwwUser user, Authentication authentication, Model model) {
        logger.debug("AccountController account/{"+ id + "} - Get");
        return controllerFacade.AccountReadIdGet(id, user, authentication, model);
    }

    @RequestMapping(value = "/account/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer id, @AuthenticationPrincipal WwwUser user, Authentication authentication, Model model) {
        logger.debug("AccountController account/update/{"+ id + "} - Get");
        return controllerFacade.AccountUpdateGet(id, user, authentication, model);
    }

    @RequestMapping(value = "/account/update/{id}", method = RequestMethod.POST)
    public String updatePost(@Valid @ModelAttribute("user") UserUpdateForm user, BindingResult bindingResult, @PathVariable("id") Integer id, Model model) {
        logger.debug("AccountController account/update/{"+ id + "} - Post");
        return controllerFacade.AccountUpdatePost(id, user, bindingResult, model);
    }

    @RequestMapping(value = "/account/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id,  @AuthenticationPrincipal WwwUser user, Authentication authentication, Model model){
        logger.debug("AccountController account/delete/{"+ id + "} - Get");
        return controllerFacade.AccountDeleteGet(id, user, authentication, model);
    }

    @RequestMapping(value = "/account/list", method = RequestMethod.GET)
    public String list(Model model) {
        logger.debug("AccountController /account/list - Get");
        return controllerFacade.AccountListGet(model);
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
