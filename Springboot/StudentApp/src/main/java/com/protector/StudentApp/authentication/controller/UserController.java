package com.protector.StudentApp.authentication.controller;

import com.protector.StudentApp.authentication.service.UserService;
import com.protector.StudentApp.authentication.model.User;
import com.protector.StudentApp.authentication.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @PostMapping("register")
    private ResponseEntity<Map<String, Object>> register(@RequestBody User user) throws InterruptedException {
        final User newUser = userService.saveUser(user);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        response.put("status", 200);
        response.put("description", "Account created Successfully");
        data.put("user", newUser);
        data.put("token", jwtService.generateToken(user.getUsername()));
        response.put("data", data);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("login")
    private ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        Authentication authentication = authenticate(user);
        if(authentication.isAuthenticated()) {
            response.putIfAbsent("description", "Login Successful");
            response.putIfAbsent("token", jwtService.generateToken(user.getUsername()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.putIfAbsent("description", "User not found");
            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    private Authentication authenticate (User user){
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }
}
