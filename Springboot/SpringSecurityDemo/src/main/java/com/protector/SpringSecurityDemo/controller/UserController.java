package com.protector.SpringSecurityDemo.controller;

import com.protector.SpringSecurityDemo.model.ApiResponse;
import com.protector.SpringSecurityDemo.model.User;
import com.protector.SpringSecurityDemo.service.JWTService;
import com.protector.SpringSecurityDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @PostMapping("register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody User user) {
        ApiResponse<User> response = new ApiResponse.Builder<User>()
                .status(HttpStatus.OK)
                .description("User created successfully")
                .data(userService.saveUser(user))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()) {
            response.putIfAbsent("description", "Login Successful");
            response.putIfAbsent("token", jwtService.generateToken(user.getUsername()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.putIfAbsent("description", "User not found");
            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
}
