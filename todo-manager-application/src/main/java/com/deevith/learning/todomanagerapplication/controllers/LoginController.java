package com.deevith.learning.todomanagerapplication.controllers;

import com.deevith.learning.todomanagerapplication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    LoginService service;
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String loginPage(ModelMap modelMap) {
        return "login";
    }
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String showWelcome(ModelMap modelMap, @RequestParam String name, @RequestParam String password) {
        boolean result = service.validateLogin(name,password);
        modelMap.put("name",name);
        modelMap.put("errorMessage","Invalid Username or password");
        if(!result) {
            return "login";
        }
        return "welcome";
    }
}
