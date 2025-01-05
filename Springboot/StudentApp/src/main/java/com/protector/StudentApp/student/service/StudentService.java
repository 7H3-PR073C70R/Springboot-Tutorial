package com.protector.StudentApp.student.service;

import com.protector.StudentApp.student.model.Student;
import com.protector.StudentApp.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addOrUpdateStudent(Student student) {
        return  studentRepository.save(student);
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(new Student());
    }

    public void deleteStudentById(int id) {
        studentRepository.delete(getStudentById(id));
    }
}
