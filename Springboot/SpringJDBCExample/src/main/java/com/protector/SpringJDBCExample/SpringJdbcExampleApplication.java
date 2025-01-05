package com.protector.SpringJDBCExample;

import com.protector.SpringJDBCExample.model.Student;
import com.protector.SpringJDBCExample.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJdbcExampleApplication {

	public static void main(String[] args) {

		final ApplicationContext context =  SpringApplication.run(SpringJdbcExampleApplication.class, args);

		final StudentService studentService = context.getBean(StudentService.class);

		final List<Student> studentList = studentService.getAllStudents();
		studentList.forEach((stud) -> System.out.println(stud.toString()));

		final Student student = context.getBean(Student.class);
		student.setRowNumber(studentList.getLast().getRowNumber() + 1);
		student.setName("Oluwatobi");
		student.setMarks(100);

		studentService.addStudent(student);


 	}

}
