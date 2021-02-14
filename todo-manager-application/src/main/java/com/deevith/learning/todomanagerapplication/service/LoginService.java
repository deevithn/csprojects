package com.deevith.learning.todomanagerapplication.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
    public boolean validateLogin(String userName, String password) {
        return userName.equals("Deevith") && password.equals("passCode123");
    }
}
