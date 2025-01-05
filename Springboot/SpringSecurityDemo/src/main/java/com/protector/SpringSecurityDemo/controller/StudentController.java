package com.protector.SpringSecurityDemo.controller;

import com.protector.SpringSecurityDemo.model.ApiResponse;
import com.protector.SpringSecurityDemo.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    List<Student> studentList = new ArrayList<>(List.of(
            new Student(1, "Protector", "Java"),
            new Student(2, "Oluwatobi", "Flutter")
    ));

    @GetMapping("students")
    public ResponseEntity<ApiResponse<List<Student>>> getStudents() {
        ApiResponse<List<Student>> response = new ApiResponse.Builder<List<Student>>()
                .status(HttpStatus.OK)
                .description("Successful")
                .data(studentList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("student")
    public ResponseEntity<ApiResponse<Student>> addStudents(@RequestBody Student student) {
        studentList.add(student);
        ApiResponse<Student> response = new ApiResponse.Builder<Student>()
                .status(HttpStatus.OK)
                .description("Successful")
                .data(student)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
