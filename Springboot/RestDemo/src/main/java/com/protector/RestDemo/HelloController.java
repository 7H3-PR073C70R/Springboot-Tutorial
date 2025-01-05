package com.protector.RestDemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    private ResponseEntity<String> greet() {
        return ResponseEntity.ok("Hello World");
    }
}
