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
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * Created by Perttu Vanharanta on 26.5.2016.
 */

@Component
public class ControllerFacade {

    private static final String ACCOUNT_READ_PATH ="read";
    private static final String ACCOUNT_UPDATE_PATH = "update";
    private static final String ACCOUNT_DElETE_PATH = "delete";

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    UserService userService;

    public String AccountRegisterGet(Model model){
        String className = "AccountRegisterGet";
        logger.debug(className);
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "/thyme/userregisterform";
    }

    public String AccountRegisterPost(UserRegisterForm user, BindingResult bindingResult,Model model){
        String className = "AccountRegisterPost";
        logger.debug(className);

        if(bindingResult.hasErrors()) {
            logger.debug(className + " - Validation failure");
            return "/thyme/userregisterform";
        }

        /* Allowed only unique username*/

        if(userService.getUserSanityCheck(user.getUsername())) {
            logger.debug(className + " - Register new User");
            userService.register(new WwwUser(null, user.getUsername(), user.getPassword(), user.getEmail(), user.getRole()));
            WwwUser viewUser = userService.getUser(user.getUsername());
            model.addAttribute("user", viewUser);
            return "/thyme/userregistered";
        }
        bindingResult.rejectValue("username","Username is exist","Username is in use");
        logger.debug(className + " - Username exist " + user.getUsername());
        return "/thyme/userregisterform";
    }

    public String AccountProfileGet(WwwUser user, Model model){
        String className = "AccountProfileGet";
        logger.debug(className);

        if(user != null) {
            logger.debug(className + " User is logged in");
            WwwUser viewUser = userService.getUser(user.getId());
            model.addAttribute("user", viewUser);
            return "/thyme/userprofile";
        }
        logger.debug(className + " User is null");
        model.addAttribute("errorMessage", "");
        return "/thyme/error";
    }

    public String AccountReadIdGet(Long id, WwwUser user, Authentication authentication, Model model) {
        String className = "AccountReadIdGet";
        String path = ACCOUNT_READ_PATH;
        String returnValue = "redirect:/";
        logger.debug(className);

        if(user != null){
            logger.debug(className + " id = {"+ id + "} user != null");
            returnValue = userRightCheck(id, user, path, model);
        } else if(authentication != null) {
            returnValue = inMemoryUserRightCheck(id, authentication, path, model);
        }
        return returnValue;
    }

    public String AccountUpdateGet(Long id, WwwUser user, Authentication authentication, Model model){
        String className = "AccountUpdateGet";
        String path = ACCOUNT_UPDATE_PATH;
        String returnValue = "redirect:/";
        logger.debug(className);

        if(user != null){
            logger.debug(className + " id = {"+ id + "} user != null");
            returnValue = userRightCheck(id, user, path, model);
        } else if(authentication != null) {
            returnValue = inMemoryUserRightCheck(id, authentication, path, model);
        }

        return returnValue;
    }

    public String AccountUpdatePost(Long id, UserUpdateForm user, BindingResult bindingResult, Model model){
        String className = "AccountUpdatePost";
        String returnValue = "redirect:/";
        logger.debug(className);

        Long userId = Long.parseLong(user.getId());

        if(bindingResult.hasErrors()) {
            logger.debug(className + " Validation failure");
            return "/thyme/userupdate";
        }
        /* Allowed only unique username */
        WwwUser viewUser = userService.getUser(userId);
        if(viewUser.getUsername().equals(user.getUsername())){
            logger.debug(className + " Username not changed");
            userService.updateUser(new WwwUser(userId, user.getUsername(), user.getPassword(), user.getEmail(), user.getRole()));

            viewUser = userService.getUser(userId);
            model.addAttribute("user", viewUser);
            return "/thyme/userprofile";

        } else if(userService.getUserSanityCheck(user.getUsername())) {
            logger.debug(className + " New username is unique");
            userService.updateUser(new WwwUser(userId, user.getUsername(), user.getPassword(), user.getEmail(), user.getRole()));

            viewUser = userService.getUser(userId);
            model.addAttribute("user", viewUser);
            return "/thyme/userprofile";

        }
        logger.debug(className + " Username exist " + user.getUsername());

        bindingResult.rejectValue("username","Username is exist","Username is in use");
        return "/thyme/userupdate";
    }

    public String AccountDeleteGet(Long id, WwwUser user, Authentication authentication, Model model) {
        String className = "AccountDeleteGet";
        String path = ACCOUNT_DElETE_PATH;
        String returnValue = "redirect:/";
        logger.debug(className);

        if (user != null) {
            logger.debug(className + " id = {" + id + "} user != null");
            returnValue = userRightCheck(id, user, path, model);
        } else if (authentication != null) {
            returnValue = inMemoryUserRightCheck(id, authentication, path, model);
        }
        return returnValue;
    }

    public String AccountListGet(Model model){
        String className = "AccountListGet";
        logger.debug(className);

        model.addAttribute("users", userService.getUsers());
        return "/thyme/userlist";
    }

    private String userRightCheck(Long id, WwwUser user, String path, Model model){
        String className = "userRightCheck";
        logger.debug(className + "  path variable:" + path);

        boolean isAdmin = false;
        String role = "ROLE_ADMIN";

        if (user.getId().equals(id) && !path.equals(ACCOUNT_DElETE_PATH)) {
            logger.debug(className + " id = {"+ id + "} id equals user." + user.getId());

            model.addAttribute("user", user);
            if(path.equals(ACCOUNT_READ_PATH)) {
                logger.debug(className + "  path variable:" + path + " return");
                return "/thyme/userprofile";
            } else if(path.equals(ACCOUNT_UPDATE_PATH)){
                logger.debug(className + "  path variable:" + path + " return");
                return "/thyme/userupdate";
            }
            logger.debug(className + "  path variable:" + path + " invalid path value");
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
        logger.debug(className + " id = {"+ id + "} id not equals user." + user.getId());

        if(role.equals(user.getRole())){
            logger.debug(className + " id = {"+ id + "}  user." + user.getId() + " have admin role");

            if(!userService.getUserSanityCheck(id)) {
                logger.debug(className + " id = {"+ id + "} SanityCheck found current id");

                WwwUser viewUser = userService.getUser(id);
                model.addAttribute("user", viewUser);

                if(path.equals(ACCOUNT_READ_PATH)) {
                    logger.debug(className + " " + path + " return");
                    return "/thyme/userprofile";
                } else if(path.equals(ACCOUNT_UPDATE_PATH)){
                    logger.debug(className + " " + path + " return");
                    return "/thyme/userupdate";
                }else if(path.equals(ACCOUNT_DElETE_PATH)){
                    userService.removeUser(id);
                    model.addAttribute("users", userService.getUsers());
                    return "/thyme/userlist";
                }
                logger.debug(className + "  path variable:" + path + " invalid path value");
            }

            logger.debug(className + "id = {"+ id + "} SanityCheck not found current id");
            model.addAttribute("errorMessage", "Id not found");
            return "/thyme/error";
        }

        logger.debug(className + " id = {"+ id + "} User." + user.getId()+" not match id and user not admin");
        model.addAttribute("errorMessage", "Not Id owner");
        return "/thyme/error";
    }

    private String inMemoryUserRightCheck(Long id, Authentication authentication, String path, Model model){
        String className = "inMemoryUserRightCheck";
        logger.debug(className + " path variable:" + path);

        boolean isAdmin = false;
        String role = "ROLE_ADMIN";

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (role.equals(auth.getAuthority()))
                isAdmin = true;
        }

        if(isAdmin){
            logger.debug(className + " id = {"+ id + "} Authentication user is admin");
            if(!userService.getUserSanityCheck(id)) {
                logger.debug(className + "id = {"+ id + "} Authentication SanityCheck found current id");
                WwwUser viewUser = userService.getUser(id);
                model.addAttribute("user", viewUser);

                if(path.equals(ACCOUNT_READ_PATH)) {
                    logger.debug(className + "  path variable:" + path + "return");
                    return "/thyme/userprofile";
                }else if(path.equals(ACCOUNT_UPDATE_PATH)){
                    logger.debug(className + "  path variable:" + path + "return");
                    return "/thyme/userupdate";
                }else if(path.equals(ACCOUNT_DElETE_PATH)){
                    userService.removeUser(id);
                    model.addAttribute("users", userService.getUsers());
                    return "/thyme/userlist";
                }
                logger.debug(className + " " + path + "invalid path value");            }

            logger.debug(className + " id = {"+ id + "} Authentication SanityCheck not found current id");
            model.addAttribute("errorMessage", "Id not found");
            return "/thyme/error";
        }

        logger.debug(className + " id ={"+ id + "} Authentication not null and not admin");
        model.addAttribute("errorMessage", "Not Id owner");
        return "/thyme/error";
    }

}
