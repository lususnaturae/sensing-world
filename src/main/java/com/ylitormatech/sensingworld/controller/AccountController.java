package com.ylitormatech.sensingworld.controller;

import com.ylitormatech.sensingworld.domain.entity.UserEntity;
import com.ylitormatech.sensingworld.domain.service.UserService;
import com.ylitormatech.sensingworld.web.UserRegisterForm;
import com.ylitormatech.sensingworld.web.UserUpdateForm;
import com.ylitormatech.sensingworld.web.WwwUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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

    private static final String UPDATE_PATH = "/update/";

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

    @RequestMapping(value = "/account/profile", method = RequestMethod.GET)
    public String profile(Model model, @AuthenticationPrincipal WwwUser user) {
        logger.debug("AccountController account/profile - Get");
        if(user != null) {
            WwwUser wwwUser = userService.getUser(user.getId());
            model.addAttribute("user", wwwUser);
            return "/thyme/userprofile";
        }
        model.addAttribute("errorMessage", "");
        return "/thyme/error";
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public String register2(@PathVariable("id") Long id, Model model, Authentication authentication, @AuthenticationPrincipal WwwUser user) {
        logger.debug("AccountController account/{"+ id + "} - Get");
        boolean isAdmin = false;
        String role = "ROLE_ADMIN";

        /*
        * user is null if not authentication or in memory user
        * For in-memory user should use authentication
        * */

        if(user != null) {
            logger.debug("AccountController account/{"+ id + "} - Get - user != null");

            if (user.getId().equals(id)) {
                logger.debug("AccountController account/{"+ id + "} - Get - id equals user." + user.getId());

                model.addAttribute("user", user);
                return "/thyme/userprofile";
            }

           /*
           * If user have multiple authority ROLE_ADMIN, ROLE_USER
           * WwwUser.java need setAuthority method for this
           *
           *    for (GrantedAuthority auth : user.getAuthorities()) {
           *     if (role.equals(auth.getAuthority()))
           *         isAdmin = true;
           * }
           * */
            logger.debug("AccountController account/{"+ id + "} - Get - id not equals user." + user.getId());

            if(role.equals(user.getRole())){
                logger.debug("AccountController account/{"+ id + "} - Get - user." + user.getId() + " have admin role");

                /*
                * Check is id exist
                * Avoid null pointer exception
                * */

                    if(!userService.getUserSanityCheck(id)) {
                        logger.debug("AccountController account/{"+ id + "} - Get - SanityCheck found current id");

                        WwwUser userById = userService.getUser(id);
                        model.addAttribute("user", userById);
                        return "/thyme/userprofile";
                    }
                /*
                * Where return if id not found?
                * Userlist?????
                * */
                logger.debug("AccountController account/{"+ id + "} - Get - SanityCheck not found current id");
                model.addAttribute("errorMessage", "Id not found");
                return "/thyme/error";
            }
            /*
            * Where to return if not user own id or user isn't admin?
            * accessdenied page????
            * */
            logger.debug("AccountController account/{"+ id + "} - Get - User." + user.getId()+" not match id and user not admin");
            model.addAttribute("errorMessage", "Not Id owner");
            return "/thyme/error";
        } else if(authentication != null){
            logger.debug("AccountController account/{"+ id + "} - Get - Authentication not null");

            /*
            * Can remove if inMemory user's not used
            */
            for (GrantedAuthority auth : authentication.getAuthorities()) {
                if (role.equals(auth.getAuthority()))
                    isAdmin = true;
            }

            if(isAdmin){
                logger.debug("AccountController account/{"+ id + "} - Get - Authentication user is admin");
               if(!userService.getUserSanityCheck(id)) {
                   logger.debug("AccountController account/{"+ id + "} - Get - Authentication SanityCheck found current id");
                    WwwUser userById = userService.getUser(id);
                    model.addAttribute("user", userById);
                    return "/thyme/userprofile";
                }
                /*
                * Where return if id not found?
                * Userlist?????
                * */
                logger.debug("AccountController account/{"+ id + "} - Get - Authentication SanityCheck not found current id");
                model.addAttribute("errorMessage", "Id not found");
                return "/thyme/error";
            }
            /*
            * Where to return if not user own id or user isn't admin?
            * accessdenied page????
            * */
            logger.debug("AccountController account/{"+ id + "} - Get - Authentication not null and not admin");
            model.addAttribute("errorMessage", "Not Id owner");
            return "/thyme/error";
        }
        logger.debug("AccountController account/{"+ id + "} - Get - No authorized user");
        /*
        * Where to return if user is null
        * */

        return "redirect:/";

    }

    @RequestMapping(value = "/account/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model, Authentication authentication, @AuthenticationPrincipal WwwUser user) {
        String path= UPDATE_PATH;
        return userIdCheck(id,model,authentication,user,path);
    }

    @RequestMapping(value = "/account/update/{id}", method = RequestMethod.POST)
    public String updatePost(@Valid @ModelAttribute("user") UserUpdateForm user, BindingResult bindingResult, @PathVariable("id") Long id, Model model) {
        String path= UPDATE_PATH;
        Long userid = Long.parseLong(user.getId());
        logger.debug("AccountController account"+path+"{"+ id + "} - Post");

        if(bindingResult.hasErrors()) {
            logger.debug("AccountController /account"+path+" - Post - Validation failure");
            return "/thyme/userupdate";
        }
        /*
        * Allowed only unique username
        **/
        WwwUser viewUser = userService.getUser(userid);
        if(viewUser.getUsername().equals(user.getUsername())){
            logger.debug("AccountController /account"+path+"{"+ id +"} - Post - Username not changed");
            userService.updateUser(new WwwUser(userid, user.getUsername(), user.getPassword(), user.getEmail(), user.getRole()));

            viewUser = userService.getUser(userid);
            model.addAttribute("user", viewUser);
            return "/thyme/userprofile";

        } else if(userService.getUserSanityCheck(user.getUsername())) {
            logger.debug("AccountController /account"+path+"{"+ id +"} - Post - New username unique");
            userService.updateUser(new WwwUser(userid, user.getUsername(), user.getPassword(), user.getEmail(), user.getRole()));

            viewUser = userService.getUser(userid);
            model.addAttribute("user", viewUser);
            return "/thyme/userprofile";

        }
        bindingResult.rejectValue("username","Username is exist","Username is in use");
        logger.debug("AccountController /account"+path+"{"+ id +"} - Post - Username exist " + user.getUsername());
        return "/thyme/userupdate";
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

    public String userIdCheck(Long id, Model model, Authentication authentication, WwwUser user,String path){
        logger.debug("AccountController account"+ path +"{"+ id + "} - Get");
        boolean isAdmin = false;
        String role = "ROLE_ADMIN";

        /*
        * user is null if not authentication or in memory user
        * For in-memory user should use authentication
        * */

        if(user != null) {
            logger.debug("AccountController account"+ path +"{"+ id + "} - Get - user != null");

            if (user.getId().equals(id)) {
                logger.debug("AccountController account"+ path +"{"+ id + "} - Get - id equals user." + user.getId());

                model.addAttribute("user", user);
                return "/thyme/userupdate";
            }

           /*
           * If user have multiple authority ROLE_ADMIN, ROLE_USER
           * WwwUser.java need setAuthority method for this
           *
           *    for (GrantedAuthority auth : user.getAuthorities()) {
           *     if (role.equals(auth.getAuthority()))
           *         isAdmin = true;
           * }
           * */
            logger.debug("AccountController account"+ path +"{"+ id + "} - Get - id not equals user." + user.getId());

            if(role.equals(user.getRole())){
                logger.debug("AccountController account"+ path +"{"+ id + "} - Get - user." + user.getId() + " have admin role");

                /*
                * Check is id exist
                * Avoid null pointer exception
                * */

                if(!userService.getUserSanityCheck(id)) {
                    logger.debug("AccountController account"+ path +"{"+ id + "} - Get - SanityCheck found current id");

                    WwwUser userById = userService.getUser(id);
                    model.addAttribute("user", userById);
                    return "/thyme/userupdate";
                }
                /*
                * Where return if id not found?
                * Userlist?????
                * */
                logger.debug("AccountController account"+ path +"{"+ id + "} - Get - SanityCheck not found current id");
                model.addAttribute("errorMessage", "Id not found");
                return "/thyme/error";
            }
            /*
            * Where to return if not user own id or user isn't admin?
            * accessdenied page????
            * */
            logger.debug("AccountController account"+ path +"{"+ id + "} - Get - User." + user.getId()+" not match id and user not admin");
            model.addAttribute("errorMessage", "Not Id owner");
            return "/thyme/error";
        } else if(authentication != null){
            logger.debug("AccountController account"+ path +"{"+ id + "} - Get - Authentication not null");

            /*
            * Can remove if inMemory user's not used
            */
            for (GrantedAuthority auth : authentication.getAuthorities()) {
                if (role.equals(auth.getAuthority()))
                    isAdmin = true;
            }

            if(isAdmin){
                logger.debug("AccountController account"+ path +"{"+ id + "} - Get - Authentication user is admin");
                if(!userService.getUserSanityCheck(id)) {
                    logger.debug("AccountController account"+ path +"{"+ id + "} - Get - Authentication SanityCheck found current id");
                    WwwUser userById = userService.getUser(id);
                    model.addAttribute("user", userById);
                    return "/thyme/userupdate";
                }
                logger.debug("AccountController account"+ path +"{"+ id + "} - Get - Authentication SanityCheck not found current id");
                model.addAttribute("errorMessage", "Id not found");
                return "/thyme/error";
            }

            logger.debug("AccountController account"+ path +"{"+ id + "} - Get - Authentication not null and not admin");
            model.addAttribute("errorMessage", "Not Id owner");
            return "/thyme/error";
        }
        logger.debug("AccountController account"+ path +"{"+ id + "} - Get - No authorized user");
        if(path.equals(UPDATE_PATH)){
            model.addAttribute("errorMessage", "Just a TEst");
            return "/thyme/error";
        }

        return "redirect:/";
    }

}
