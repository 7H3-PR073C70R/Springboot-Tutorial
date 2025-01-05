package com.protector.SpringSecurityDemo.controller;

import com.protector.SpringSecurityDemo.model.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public ResponseEntity<ApiResponse<String>> hello() {
        ApiResponse<String> response = new ApiResponse.Builder<String>()
                .status(HttpStatus.OK)
                .description("Successful")
                .data("Hello World")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
