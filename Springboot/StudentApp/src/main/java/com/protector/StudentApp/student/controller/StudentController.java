package com.protector.StudentApp.student.controller;

import com.protector.StudentApp.model.ApiResponse;
import com.protector.StudentApp.student.model.Student;
import com.protector.StudentApp.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("students")
    private ResponseEntity<ApiResponse<List<Student>>> getAllStudents() {
        return ResponseEntity.ok(ApiResponse.of("successful", studentService.getAllStudents()));
    }

    @PostMapping("student")
    private ResponseEntity<ApiResponse<Student>> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(ApiResponse.of("successful", studentService.addOrUpdateStudent(student)));
    }

    @GetMapping("student/{studentId}")
    private ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable int studentId) {
        final Student student = studentService.getStudentById(studentId);
        if(student.getId() == 0) {
            return  new ResponseEntity<>( ApiResponse.of("Student with id " + studentId + " not found", null) ,HttpStatus.NOT_FOUND);
        } else {
            return  ResponseEntity.ok(ApiResponse.of("Student fetched successfully", student));
        }
    }

    @PatchMapping("student/{studentId}")
    private ResponseEntity<ApiResponse<Student>> updateStudent(@RequestBody Student student, @PathVariable int studentId) {
        final Student oldStudent = studentService.getStudentById(studentId);
        if(oldStudent.getId() == 0) {
            return new ResponseEntity<>(ApiResponse.of("Student with id " + studentId + " not found", null) ,HttpStatus.NOT_FOUND);
        } else {
            student.setId(studentId);
            if(student.getAge() == 0)
                student.setAge(oldStudent.getAge());
            if(student.getName().isBlank() || student.getName().isEmpty())
                student.setName(oldStudent.getName());
            if(student.getMarks() == 0)
                student.setMarks(oldStudent.getMarks());
            return  ResponseEntity.ok(ApiResponse.of("Student updated successfully", studentService.addOrUpdateStudent(student)));
        }
    }

    @DeleteMapping("student/{studentId}")
    private ResponseEntity<ApiResponse<Student>> deleteStudent( @PathVariable int studentId) {
        final Student student = studentService.getStudentById(studentId);
        if(student.getId() == 0) {
            return  new ResponseEntity<>( ApiResponse.of("Student with id " + studentId + " not found", null) ,HttpStatus.NOT_FOUND);
        } else {
            studentService.deleteStudentById(studentId);
            return  ResponseEntity.ok(ApiResponse.of("Student deleted successfully", null));
        }
    }
}
